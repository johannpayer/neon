package neon.critical;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.image.BufferStrategy;
import java.io.Serial;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import neon.input.InputSystem;
import neon.physics.Vector2D;
import neon.utils.GeneralUtils;
import neon.utils.OsType;

/** The main class for the Neon Game Library. */
public class NeonEngine extends Canvas implements Runnable {
  @Serial private static final long serialVersionUID = 1L;
  private static final NeonEngine instance = new NeonEngine();
  private static GameSettings settings;
  private static boolean doLoop = true;
  private static JFrame frame;
  private Game game;

  /**
   * Creates a window for the game and initializes the engine.
   *
   * @param game a class which implements the IGame interface and contains the main logic of a game
   * @param settings the basic settings used for a game
   */
  public static void init(Game game, GameSettings settings) {
    WindowSettings windowSettings = settings.windowSettings;
    if (windowSettings != null) {
      instance.setPreferredSize(new Dimension(windowSettings.width, windowSettings.height));
      instance.requestFocus();

      frame = new JFrame(windowSettings.title);
      frame.add(instance);
      frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
      GraphicsDevice graphicsDevice =
          GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
      if (windowSettings.windowOption.doFullscreen
          && GeneralUtils.getOsType() == OsType.MAC_OS
          && graphicsDevice.isFullScreenSupported()) {
        graphicsDevice.setFullScreenWindow(frame);
      }
      frame.setUndecorated(windowSettings.windowOption.doFullscreen);
      frame.setResizable(false);
      frame.pack();
      frame.setVisible(true);

      instance.createBufferStrategy(windowSettings.bufferCount);
    }

    Runtime.getRuntime()
        .addShutdownHook(
            new Thread(
                () -> {
                  terminate();
                  game.onExit();
                }));

    instance.game = game;
    NeonEngine.settings = settings;

    instance.addMouseListener(new InputSystem.MouseInput());
    instance.addKeyListener(new InputSystem.KeyInput());

    game.init();

    instance.start();
  }

  /** @return the game settings */
  public static GameSettings getSettings() {
    return settings;
  }

  /**
   * Note: Calling this method when the "createWindow" setting is set to "false" will result in an
   * exception.
   *
   * @return the position of the game window on the screen
   */
  public static Point getPositionOnScreen() {
    return (frame == null ? instance : frame).getLocationOnScreen();
  }

  /** @return the frame being used by the game library */
  public static JFrame getFrame() {
    return frame;
  }

  /**
   * Sets the frame to be used when calculating the window's position on the screen.<br>
   * Requires that the "createWindow" property of the game settings is set to false.
   *
   * @param frame the frame
   */
  public static void setFrame(JFrame frame) {
    if (NeonEngine.frame == null) {
      NeonEngine.frame = frame;
    }
  }

  /** Terminates the game loop. */
  public static void terminate() {
    doLoop = false;
  }

  /** WARNING: Do not call this method. It is only to be called by core library classes. */
  @Override
  public void run() {
    try {
      updateInput();

      // Initialize timing
      double initialTime = System.nanoTime();
      double tickDelta = 0;
      double frameDelta = 0;

      while (doLoop) {
        // Handle timing
        double currentTime = System.nanoTime();
        double secondDifference = (currentTime - initialTime) / 1e9;
        tickDelta += secondDifference * settings.tickRate;
        frameDelta += secondDifference * settings.frameRate;
        initialTime = currentTime;

        if (tickDelta >= 1) {
          game.tick();
          tickDelta--;
        }

        if (frameDelta >= 1) {
          updateFrame();
          frameDelta--;
        }

        if (settings.doSleepThread) {
          try {
            // noinspection BusyWait
            Thread.sleep(
                (long)
                    (Math.min(
                            (1 - tickDelta) * (1e9 / settings.tickRate),
                            (1 - frameDelta) * (1e9 / settings.frameRate))
                        / 1e6));
          } catch (Exception ex) {
            // Ignore
          }
        }
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private void updateFrame() {
    updateInput();

    // Render
    if (settings.windowSettings != null) {
      BufferStrategy bufferStrategy = getBufferStrategy();
      Graphics graphics = bufferStrategy.getDrawGraphics();
      do {
        try {
          WindowSettings windowSettings = settings.windowSettings;
          if (windowSettings.doClearFrames) {
            graphics.clearRect(0, 0, windowSettings.width, windowSettings.height);
          }
          game.render(graphics);
        } finally {
          assert graphics != null;
          graphics.dispose();
        }
        bufferStrategy.show();
      } while (bufferStrategy.contentsLost());
    } else {
      game.render(null);
    }
  }

  private void updateInput() {
    // Inputs
    try {
      Point location = MouseInfo.getPointerInfo().getLocation();
      InputSystem.setRawMousePosition(new Vector2D(location));

      Vector2D vector =
          InputSystem.getRawMousePosition().safeSubtract(new Vector2D(getPositionOnScreen()));
      vector.convertCoordinateSystem();
      InputSystem.setMousePosition(vector);
    } catch (Exception ex) {
      // Ignore
    }
    InputSystem.update();
  }

  private void start() {
    new Thread(this).start();
  }
}
