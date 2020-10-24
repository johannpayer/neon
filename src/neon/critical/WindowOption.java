package neon.critical;

/** An enum containing several window setting options. */
public enum WindowOption {
  NONE(false, false),
  MAXIMIZE(false, true),
  FULLSCREEN_AND_MAXIMIZE(true, true);

  public final boolean doFullscreen;
  public final boolean doMaximize;

  WindowOption(boolean doFullscreen, boolean doMaximize) {
    this.doFullscreen = doFullscreen;
    this.doMaximize = doMaximize;
  }
}
