package flamesdev.neon.rendering;

import flamesdev.neon.physics.Hitbox;

import java.awt.image.BufferedImage;

public class ImageObject {
    public Hitbox hitbox;
    public BufferedImage sprite;

    public ImageObject(Hitbox hitbox, BufferedImage sprite) {
        this.hitbox = hitbox;
        this.sprite = sprite;
    }
}