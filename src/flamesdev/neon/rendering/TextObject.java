package flamesdev.neon.rendering;

import flamesdev.neon.critical.GameSettings;
import flamesdev.neon.physics.Hitbox;
import flamesdev.neon.physics.Vector2D;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * A class containing a Hitbox, String, and Font property.
 */
public class TextObject {
    private static GameSettings settings;

    public Hitbox hitbox;
    public String text;
    public Font font;

    /**
     * @param position the position of the text
     * @param text     the text
     * @param font     the font of the text
     * @param graphics the graphics used to determine the size of the text
     */
    public TextObject(Vector2D position, String text, Font font, Graphics graphics) {
        this.text = text;
        this.font = font;

        Rectangle2D size = graphics.getFontMetrics(font).getStringBounds(text, graphics);
        hitbox = new Hitbox(position, size.getWidth() / settings.width, size.getHeight() / settings.height);
    }

    /**
     * @param position the position of the text
     * @param text     the text
     * @param graphics the graphics used to determine the size of the text
     */
    public TextObject(Vector2D position, String text, Graphics graphics) {
        this.text = text;
        this.font = graphics.getFont();

        Rectangle2D size = graphics.getFontMetrics(this.font).getStringBounds(text, graphics);
        hitbox = new Hitbox(position, size.getWidth() / settings.width, size.getHeight() / settings.height);
    }

    /**
     * WARNING: Do not call this method. It is only to be called by core library classes.<br>
     * Sets the settings of the render engine. Once a value is defined, it cannot be
     * changed.
     *
     * @param settings the game settings
     */
    public static void setSettings(GameSettings settings) {
        if (TextObject.settings == null)
            TextObject.settings = settings;
    }
}
