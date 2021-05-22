package neon.rendering;

import neon.critical.NeonEngine;
import neon.critical.WindowSettings;

/** A class used to convert between units. */
public class UnitConverter {
  /**
   * Converts height units to width units.
   *
   * @param height the height units
   * @return the respective width units
   */
  public static double toWidth(double height) {
    WindowSettings settings = NeonEngine.getSettings().windowSettings;
    return height * settings.height / settings.width;
  }

  /**
   * Converts width units to height units.
   *
   * @param width the width units
   * @return the respective height units
   */
  public static double toHeight(double width) {
    WindowSettings settings = NeonEngine.getSettings().windowSettings;
    return width * settings.width / settings.height;
  }

  /**
   * Converts pixel units to width units.
   *
   * @param pixels the length in pixels
   * @return the respective width units
   */
  public static double toPixelWidth(double pixels) {
    return pixels / NeonEngine.getSettings().windowSettings.width;
  }

  /**
   * Converts pixel units to height units.
   *
   * @param pixels the length in pixels
   * @return the respective height units
   */
  public static double toPixelHeight(double pixels) {
    return pixels / NeonEngine.getSettings().windowSettings.height;
  }
}
