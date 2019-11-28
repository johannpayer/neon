package flamesdev.neon.critical;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import flamesdev.neon.physics.Vector2D;
import flamesdev.neon.rendering.RenderSystem;
import flamesdev.neon.utils.GeneralUtils;
import flamesdev.neon.utils.GeneralUtils.OSType;

/**
 * This is the main class for the Neon Game Library.
 */
public class NeonEngine extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	private final static NeonEngine instance = new NeonEngine();
	private Thread thread;
	private IGame game;
	private static GameSettings settings;

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

	private void start() {
		thread = new Thread(this);
		thread.start();
	}

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
				try {
					graphics = bs.getDrawGraphics();
					graphics.clearRect(0, 0, settings.width, settings.height);
					game.render(graphics);
				} finally {
					graphics.dispose();
				}
				bs.show();

				// Sleep
				Thread.sleep((long) (1000 / settings.tickRate));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
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
}