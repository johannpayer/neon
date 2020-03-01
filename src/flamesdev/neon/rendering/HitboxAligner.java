package flamesdev.neon.rendering;

import flamesdev.neon.physics.Hitbox;

/**
 * A class used to align hitboxes.
 */
public class HitboxAligner {
    /**
     * Aligns the hitbox horizontally.
     *
     * @param hitbox    the hitbox
     * @param alignment the desired alignment
     * @return the modified hitbox
     */
    public static Hitbox align(Hitbox hitbox, HorizontalAlignment alignment) {
        double x = hitbox.getCenter().x;
        if (alignment == HorizontalAlignment.LEFT)
            hitbox.setLowerXBound(x);
        else
            hitbox.setHigherXBound(x);

        return hitbox;
    }

    /**
     * Aligns the hitbox vertically.
     *
     * @param hitbox    the hitbox
     * @param alignment the desired alignment
     * @return the modified hitbox
     */
    public static Hitbox align(Hitbox hitbox, VerticalAlignment alignment) {
        double y = hitbox.getCenter().y;
        if (alignment == VerticalAlignment.BOTTOM)
            hitbox.setLowerYBound(y);
        else
            hitbox.setHigherYBound(y);

        return hitbox;
    }
}