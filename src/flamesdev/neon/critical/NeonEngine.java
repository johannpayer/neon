package flamesdev.neon.critical;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import flamesdev.neon.critical.GeneralUtils.OSType;

public class NeonEngine extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	private final static NeonEngine instance = new NeonEngine();
	private Thread thread;
	private IGame game;
	private GameSettings settings;

	public static void init(IGame game, GameSettings settings) {
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

		instance.createBufferStrategy(2);

		instance.start();

		instance.game = game;
		instance.settings = settings;
	}

	public void start() {
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		try {
			while (true) {
				// Tick
				game.tick();

				// Render
				BufferStrategy bs = getBufferStrategy();
				Graphics graphics = null;
				try {
					graphics = bs.getDrawGraphics();
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
}