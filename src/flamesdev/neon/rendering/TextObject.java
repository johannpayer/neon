package flamesdev.neon.rendering;

import flamesdev.neon.critical.GameSettings;
import flamesdev.neon.critical.NeonEngine;
import flamesdev.neon.physics.RectangularHitbox;
import flamesdev.neon.physics.Vector2D;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

/**
 * A class containing a Hitbox, String, and Font property.
 */
public class TextObject {
    private RectangularHitbox hitbox;
    private String text;
    private Font font;
    private Color color;

    /**
     * @param position the position of the text
     * @param text     the text
     * @param font     the font of the text
     * @param color    the color of the text
     * @param graphics the graphics used to determine the size of the text
     */
    public TextObject(Vector2D position, String text, Font font, Color color, Graphics graphics) {
        this.text = text;
        this.font = font;
        this.color = color;

        Rectangle2D size = graphics.getFontMetrics(font).getStringBounds(text, graphics);
        GameSettings settings = NeonEngine.getSettings();
        hitbox = new RectangularHitbox(position, size.getWidth() / settings.getWidth(),
                size.getHeight() / settings.getHeight());
    }

    /**
     * @return the object's hitbox
     */
    public RectangularHitbox getHitbox() {
        return hitbox;
    }

    /**
     * Sets the hitbox of the object.
     *
     * @param hitbox the new hitbox
     */
    public void setHitbox(RectangularHitbox hitbox) {
        this.hitbox = hitbox;
    }

    /**
     * @return the object's text
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the object's text.
     *
     * @param text the new text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the object's font
     */
    public Font getFont() {
        return font;
    }

    /**
     * Sets the object's font.
     *
     * @param font the new font
     */
    public void setFont(Font font) {
        this.font = font;
    }

    /**
     * @return the object's color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the object's color.
     *
     * @param color the new color
     */
    public void setColor(Color color) {
        this.color = color;
    }
}
