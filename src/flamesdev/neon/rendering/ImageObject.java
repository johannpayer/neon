package flamesdev.neon.rendering;

import flamesdev.neon.physics.Hitbox;

import java.awt.image.BufferedImage;

/**
 * A class containing a Hitbox and BufferedImage property.
 */
public class ImageObject {
    public Hitbox hitbox;
    public BufferedImage sprite;

    /**
     * @param hitbox the hitbox of the image
     * @param sprite the sprite of the image
     */
    public ImageObject(Hitbox hitbox, BufferedImage sprite) {
        this.hitbox = hitbox;
        this.sprite = sprite;
    }
}