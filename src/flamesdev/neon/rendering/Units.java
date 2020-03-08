package flamesdev.neon.rendering;

import flamesdev.neon.critical.GameSettings;
import flamesdev.neon.critical.NeonEngine;

/**
 * A class that converts width and height units.
 */
public class Units {
    /**
     * Converts height units to width units.
     *
     * @param height the height units
     * @return the respective width units
     */
    public static double toWidth(double height) {
        GameSettings settings = NeonEngine.getSettings();
        return height * settings.getWidth() / settings.getHeight();
    }

    /**
     * Converts width units to height units.
     *
     * @param width the width units
     * @return the respective height units
     */
    public static double toHeight(double width) {
        GameSettings settings = NeonEngine.getSettings();
        return width * settings.getWidth() / settings.getHeight();
    }

    /**
     * Converts pixel units to width units.
     *
     * @param pixels the length in pixels
     * @return the respective width units
     */
    public static double toPixelWidth(double pixels) {
        GameSettings settings = NeonEngine.getSettings();
        return pixels / settings.getWidth();
    }

    /**
     * Converts pixel units to height units.
     *
     * @param pixels the length in pixels
     * @return the respective height units
     */
    public static double toPixelHeight(double pixels) {
        GameSettings settings = NeonEngine.getSettings();
        return pixels / settings.getHeight();
    }
}