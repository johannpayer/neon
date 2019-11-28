package flamesdev.neon.critical;

/**
 * A class that stores data about the game's settings.
 */
public class GameSettings {
	public final String title;
	public final boolean fullscreen, maximize, undecorated;
	public int width, height, buffers;
	public final double tickRate;

	public GameSettings(String title, boolean fullscreen, boolean undecorated, boolean maximize, int width, int height,
			double tickRate, int buffers) {
		this.title = title;
		this.fullscreen = fullscreen;
		this.undecorated = undecorated;
		this.maximize = maximize;
		this.width = width;
		this.height = height;
		this.tickRate = tickRate;
		this.buffers = buffers;
	}
}