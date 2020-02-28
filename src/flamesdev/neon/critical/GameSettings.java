package flamesdev.neon.critical;

/**
 * A class that stores data about the game's settings.
 */
public class GameSettings {
    public final String title;
    public final boolean fullscreen;
    public final boolean maximize;
    public final boolean undecorated;
    public final double tickRate;
    public int width;
    public int height;
    public int buffers;

    /**
     * @param title       the title of the window
     * @param fullscreen  whether the window should be fullscreen
     * @param undecorated whether the window should be decorated
     * @param maximize    whether the window should be maximized
     * @param width       the width of the window in pixels
     * @param height      the height of the window in pixels
     * @param tickRate    the number of ticks per second
     * @param buffers     the number of buffer frames
     */
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