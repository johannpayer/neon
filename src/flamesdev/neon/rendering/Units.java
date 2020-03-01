package flamesdev.neon.rendering;

import flamesdev.neon.critical.GameSettings;

/**
 * A class that converts width and height units.
 */
public class Units {
    private static GameSettings settings;

    /**
     * WARNING: Do not call this method. It is only to be called by core library classes.<br>
     * Sets the settings of the render engine. Once a value is defined, it cannot be
     * changed.
     *
     * @param settings the game settings
     */
    public static void setSettings(GameSettings settings) {
        if (Units.settings == null)
            Units.settings = settings;
    }

    /**
     * Converts height units to width units.
     *
     * @param height the height units
     * @return the respective width units
     */
    public static double toWidth(double height) {
        return height * settings.width / settings.height;
    }

    /**
     * Converts width units to height units.
     *
     * @param width the width units
     * @return the respective height units
     */
    public static double toHeight(double width) {
        return width * settings.width / settings.height;
    }

    /**
     * Converts pixel units to width units.
     *
     * @param pixels the length in pixels
     * @return the respective width units
     */
    public static double toPixelWidth(double pixels) {
        return pixels / settings.width;
    }

    /**
     * Converts pixel units to height units.
     *
     * @param pixels the length in pixels
     * @return the respective height units
     */
    public static double toPixelHeight(double pixels) {
        return pixels / settings.height;
    }
}