package flamesdev.neon.critical;

import flamesdev.neon.physics.Vector2D;
import flamesdev.neon.utils.GeneralUtils;
import flamesdev.neon.utils.OSType;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

/**
 * The the main class for the Neon Game Library.
 */
public class NeonEngine extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;
    private final static NeonEngine instance = new NeonEngine();
    private static GameSettings settings;
    private static boolean loop = true;
    private static JFrame frame;
    private IGame game;

    /**
     * Creates a window for the game and initializes the engine.
     *
     * @param game     a class which implements the IGame interface and contains the main
     *                 logic of a game
     * @param settings the basic settings used for a game
     */
    public static void init(IGame game, GameSettings settings) {
        if (settings.createWindow) {
            if (settings.maximize) {
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                settings.setDimensions(screenSize.width, screenSize.height);
            }
            instance.setPreferredSize(new Dimension(settings.getWidth(), settings.getHeight()));
            instance.requestFocus();

            JFrame frame = new JFrame(settings.title);
            frame.add(instance);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
            if (settings.fullscreen && GeneralUtils.getOSType() == OSType.MAC_OS &&
                    graphicsDevice.isFullScreenSupported())
                graphicsDevice.setFullScreenWindow(frame);
            frame.setUndecorated(settings.undecorated);
            frame.setResizable(false);
            frame.pack();
            frame.setVisible(true);

            instance.createBufferStrategy(settings.getBuffers());
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            terminate();
            game.onExit();
        }));

        instance.game = game;
        NeonEngine.settings = settings;

        instance.addMouseListener(new MouseInput());

        game.init();

        instance.start();
    }

    /**
     * @return the game settings
     */
    public static GameSettings getSettings() {
        return settings;
    }

    /**
     * Note: Calling this method when the "createWindow" setting is set to "false" will result in an exception.
     *
     * @return the position of the game window on the screen
     */
    public static Point getPositionOnScreen() {
        return (frame == null ? instance : frame).getLocationOnScreen();
    }

    /**
     * Sets the frame to be used when calculating the window's position on the screen.<br>
     * Requires that the "createWindow" property of the game settings is set to false.
     *
     * @param frame the frame
     */
    public static void setFrame(JFrame frame) {
        if (!settings.createWindow)
            NeonEngine.frame = frame;
    }

    /**
     * Terminates the game loop.
     */
    public static void terminate() {
        loop = false;
    }

    private void start() {
        new Thread(this).start();
    }

    /**
     * WARNING: Do not call this method. It is only to be called by core library classes.
     */
    @Override public void run() {
        try {
            while (loop) {
                // Inputs
                try {
                    Point location = MouseInfo.getPointerInfo().getLocation();
                    InputSystem.rawMousePosition = new Vector2D(location, true);
                    InputSystem.mousePosition = new Vector2D(location, false);
                } catch (Exception ex) {
                    // Ignore
                }
                InputSystem.update();

                // Tick
                game.tick();

                // Render
                if (settings.createWindow) {
                    BufferStrategy bs = getBufferStrategy();
                    Graphics graphics = null;
                    do {
                        try {
                            graphics = bs.getDrawGraphics();
                            graphics.clearRect(0, 0, settings.getWidth(), settings.getHeight());
                            game.render(graphics);
                        } finally {
                            assert graphics != null;
                            graphics.dispose();
                        }
                        bs.show();
                    } while (bs.contentsLost());
                } else
                    game.render(null);

                // Sleep
                Thread.sleep((long) (1000 / settings.tickRate));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}