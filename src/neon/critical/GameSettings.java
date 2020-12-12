package neon.critical;

/** A class that stores data about the game's settings. */
public class GameSettings {
  public final WindowSettings windowSettings;
  public final double tickRate;
  public final double frameRate;
  public final boolean doSleepThread;

  /**
   * @param windowSettings the settings for the window
   * @param tickRate the number of ticks per second
   * @param frameRate the number of frames per second
   * @param doSleepThread whether the thread should be put to sleep
   */
  public GameSettings(
      WindowSettings windowSettings, double tickRate, double frameRate, boolean doSleepThread) {
    this.windowSettings = windowSettings;
    this.tickRate = tickRate;
    this.frameRate = frameRate;
    this.doSleepThread = doSleepThread;
  }
}
