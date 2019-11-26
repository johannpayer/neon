package flamesdev.neon.critical;

public class GameSettings {
	public final String title;
	public final boolean fullscreen, undecorated;
	public final int width;
	public final int height;
	public final double tickRate;

	public GameSettings(String title, boolean fullscreen, boolean undecorated, int width, int height, double tickRate) {
		this.title = title;
		this.fullscreen = fullscreen;
		this.undecorated = undecorated;
		this.width = width;
		this.height = height;
		this.tickRate = tickRate;
	}
}