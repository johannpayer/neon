package flamesdev.neon.critical;

import flamesdev.neon.physics.Vector2D;
import flamesdev.neon.rendering.RenderSystem;
import flamesdev.neon.utils.GeneralUtils;
import flamesdev.neon.utils.OSType;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * This is the main class for the Neon Game Library.
 */
public class NeonEngine extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;
    private final static NeonEngine instance = new NeonEngine();
    private static GameSettings settings;
    private IGame game;

    /**
     * Creates a window for the game and initializes the engine.
     *
     * @param game     a class which implements the IGame interface and contains the
     *                 main logic of your game
     * @param settings the basic settings used for your game
     */
    public static void init(IGame game, GameSettings settings) {
        if (settings.maximize) {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            settings.width = screenSize.width;
            settings.height = screenSize.height;
        }
        instance.setPreferredSize(new Dimension(settings.width, settings.height));
        instance.requestFocus();

        JFrame frame = new JFrame(settings.title);
        frame.add(instance);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        if (settings.fullscreen && GeneralUtils.getOSType() == OSType.MacOS && graphicsDevice.isFullScreenSupported())
            graphicsDevice.setFullScreenWindow(frame);
        frame.setUndecorated(settings.undecorated);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);

        instance.createBufferStrategy(settings.buffers);

        instance.game = game;
        NeonEngine.settings = settings;

        RenderSystem.setSettings(settings);

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

    public static Point getPositionOnScreen() {
        return instance.getLocationOnScreen();
    }

    private void start() {
        new Thread(this).start();
    }

    /**
     * WARNING: Do not call this method. It is only to be called by core library classes.
     */
    @Override
    public void run() {
        try {
            while (true) {
                // Inputs
                try {
                    InputSystem.mousePosition = new Vector2D(MouseInfo.getPointerInfo().getLocation());
                } catch (Exception ex) {
                    // Ignore
                }
                InputSystem.update();

                // Tick
                game.tick();

                // Render
                BufferStrategy bs = getBufferStrategy();
                Graphics graphics = null;
                do {
                    try {
                        graphics = bs.getDrawGraphics();
                        graphics.clearRect(0, 0, settings.width, settings.height);
                        game.render(graphics);
                    } finally {
                        assert graphics != null;
                        graphics.dispose();
                    }
                    bs.show();
                } while (bs.contentsLost());

                // Sleep
                Thread.sleep((long) (1000 / settings.tickRate));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}