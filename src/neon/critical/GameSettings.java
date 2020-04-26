package neon.critical;

/**
 * A class that stores data about the game's settings.
 */
public class GameSettings {
    public final String title;
    public final boolean fullscreen;
    public final boolean maximize;
    public final boolean undecorated;
    public final double tickRate;
    public final double frameRate;
    public final boolean sleepThread;
    public final boolean createWindow;
    private final int buffers;
    private int width;
    private int height;

    /**
     * @param title        the title of the window
     * @param fullscreen   whether the window should be fullscreen
     * @param undecorated  whether the window should be decorated
     * @param maximize     whether the window should be maximized
     * @param width        the width of the window in pixels
     * @param height       the height of the window in pixels
     * @param tickRate     the number of ticks per second
     * @param frameRate    the number of frames per second
     * @param sleepThread  whether the thread should be sleeped
     * @param buffers      the number of buffer frames
     * @param createWindow whether the game library should create a window; if set to "false", the "render" method for
     *                     the game will not be called
     */
    public GameSettings(String title, boolean fullscreen, boolean undecorated, boolean maximize, int width, int height,
            double tickRate, double frameRate, boolean sleepThread, int buffers, boolean createWindow) {
        this.title = title;
        this.fullscreen = fullscreen;
        this.undecorated = undecorated;
        this.maximize = maximize;
        this.width = width;
        this.height = height;
        this.tickRate = tickRate;
        this.frameRate = frameRate;
        this.sleepThread = sleepThread;
        this.buffers = buffers;
        this.createWindow = createWindow;
    }

    /**
     * @return the window's width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return the window's height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return the number of buffers used to render graphics
     */
    public int getBuffers() {
        return buffers;
    }

    /**
     * WARNING: Do not call this method. It is only to be called by core library classes.
     *
     * @param width  the window's width
     * @param height the window's height
     */
    public void setDimensions(int width, int height) {
        this.width = width;
        this.height = height;
    }
}