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
        return height * settings.width / settings.height;
    }

    /**
     * Converts width units to height units.
     *
     * @param width the width units
     * @return the respective height units
     */
    public static double toHeight(double width) {
        GameSettings settings = NeonEngine.getSettings();
        return width * settings.width / settings.height;
    }
}