package flamesdev.neon.critical;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.MouseInfo;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import flamesdev.neon.input.InputEngine;
import flamesdev.neon.physics.Vector2D;
import flamesdev.neon.rendering.RenderEngine;
import flamesdev.neon.utils.GeneralUtils;
import flamesdev.neon.utils.GeneralUtils.OSType;

public class NeonEngine extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	private final static NeonEngine instance = new NeonEngine();
	private Thread thread;
	private IGame game;
	private static GameSettings settings;

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
		NeonEngine.settings = settings;

		RenderEngine.setSettings(settings);
	}

	public void start() {
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		try {
			while (true) {
				// Inputs
				try {
					InputEngine.mousePosition = new Vector2D(MouseInfo.getPointerInfo().getLocation())
							.subtract(new Vector2D(getLocationOnScreen()))
							.divide(new Vector2D(settings.width, settings.height));
					InputEngine.mousePosition.y = 1 - InputEngine.mousePosition.y;
				} catch (Exception ex) {
					// Ignore
				}

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

	public static GameSettings getSettings() {
		return settings;
	}
}