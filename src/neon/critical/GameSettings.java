package neon.critical;

/** A class that stores data about the game's settings. */
public class GameSettings {
  public final String title;
  public final boolean doFullscreen;
  public final boolean doMaximize;
  public final boolean isUndecorated;
  public final double tickRate;
  public final double frameRate;
  public final boolean doSleepThread;
  public final boolean doCreateWindow;
  private final int bufferCount;
  private int width;
  private int height;

  /**
   * @param title the title of the window
   * @param doFullscreen whether the window should be fullscreen
   * @param isUndecorated whether the window should be decorated
   * @param doMaximize whether the window should be maximized
   * @param width the width of the window in pixels
   * @param height the height of the window in pixels
   * @param tickRate the number of ticks per second
   * @param frameRate the number of frames per second
   * @param doSleepThread whether the thread should be put to sleep
   * @param bufferCount the number of buffer frames
   * @param doCreateWindow whether the game library should create a window; if set to "false", the
   *     "render" method for the game will not be called
   */
  public GameSettings(
      String title,
      boolean doFullscreen,
      boolean isUndecorated,
      boolean doMaximize,
      int width,
      int height,
      double tickRate,
      double frameRate,
      boolean doSleepThread,
      int bufferCount,
      boolean doCreateWindow) {
    this.title = title;
    this.doFullscreen = doFullscreen;
    this.isUndecorated = isUndecorated;
    this.doMaximize = doMaximize;
    this.width = width;
    this.height = height;
    this.tickRate = tickRate;
    this.frameRate = frameRate;
    this.doSleepThread = doSleepThread;
    this.bufferCount = bufferCount;
    this.doCreateWindow = doCreateWindow;
  }

  /** @return the window's width */
  public int getWidth() {
    return width;
  }

  /** @return the window's height */
  public int getHeight() {
    return height;
  }

  /** @return the number of buffers used to render graphics */
  public int getBufferCount() {
    return bufferCount;
  }

  /**
   * WARNING: Do not call this method. It is only to be called by core library classes.
   *
   * @param width the window's width
   * @param height the window's height
   */
  public void setDimensions(int width, int height) {
    this.width = width;
    this.height = height;
  }
}
