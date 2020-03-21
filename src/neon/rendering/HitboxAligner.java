package neon.rendering;

import neon.physics.Hitbox;

/**
 * A class used to align hitboxes.
 */
public class HitboxAligner {
    /**
     * Aligns the hitbox horizontally.
     *
     * @param hitbox    the hitbox
     * @param alignment the desired alignment
     */
    public static void align(Hitbox hitbox, HorizontalAlignment alignment) {
        double x = hitbox.getCenter().getX();
        if (alignment == HorizontalAlignment.LEFT)
            hitbox.setLowerXBound(x);
        else
            hitbox.setHigherXBound(x);
    }

    /**
     * Aligns the hitbox vertically.
     *
     * @param hitbox    the hitbox
     * @param alignment the desired alignment
     */
    public static void align(Hitbox hitbox, VerticalAlignment alignment) {
        double y = hitbox.getCenter().getY();
        if (alignment == VerticalAlignment.BOTTOM)
            hitbox.setLowerYBound(y);
        else
            hitbox.setHigherYBound(y);
    }
}