package neon.critical;

import java.awt.Dimension;
import java.awt.Toolkit;

public class WindowSettings {
  public final String title;
  public final WindowOption windowOption;
  public final int bufferCount;
  public final int width;
  public final int height;
  public final boolean doClearFrames;

  /**
   * @param title the title of the window
   * @param windowOption additional window settings
   * @param bufferCount the number of buffer frames
   * @param doClearFrames whether frames should be cleared when re-rendering
   */
  public WindowSettings(
      String title, WindowOption windowOption, int bufferCount, boolean doClearFrames) {
    this(title, windowOption, bufferCount, 0, 0, doClearFrames);
  }

  /**
   * @param title the title of the window
   * @param windowOption additional window settings
   * @param bufferCount the number of buffer frames
   * @param width the width of the window in pixels
   * @param height the height of the window in pixels
   * @param doClearFrames whether frames should be cleared when re-rendering
   */
  public WindowSettings(
      String title,
      WindowOption windowOption,
      int bufferCount,
      int width,
      int height,
      boolean doClearFrames) {
    this.title = title;
    this.windowOption = windowOption;
    this.bufferCount = bufferCount;
    this.doClearFrames = doClearFrames;

    if (windowOption.doMaximize) {
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      this.width = screenSize.width;
      this.height = screenSize.height;
    } else {
      this.width = width;
      this.height = height;
    }
  }
}
