package flamesdev.neon.rendering;

import flamesdev.neon.physics.Hitbox;

import java.awt.*;

/**
 * A representation of a rectangle with a hitbox, color, and fill setting.
 */
public class Rectangle {
    public Hitbox hitbox;
    public Color color;
    public boolean fill;

    /**
     * @param hitbox the hitbox of the rectangle
     * @param color  the color of the rectangle
     * @param fill   whether the rectangle should be filled when drawn
     */
    public Rectangle(Hitbox hitbox, Color color, boolean fill) {
        this.hitbox = hitbox;
        this.color = color;
        this.fill = fill;
    }
}