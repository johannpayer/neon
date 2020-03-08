package flamesdev.neon.utils;

import java.awt.image.BufferedImage;

/**
 * A class used to load sprites from sprite sheets.
 */
public class SpriteSheet {
    private final BufferedImage image;
    private final int width;
    private final int height;

    /**
     * @param image  the image
     * @param width  the width of each sprite in the image
     * @param height the height of each sprite in the image
     */
    public SpriteSheet(BufferedImage image, int width, int height) {
        this.image = image;
        this.width = width;
        this.height = height;
    }

    /**
     * @param path   the path of the image file
     * @param width  the width of each sprite in the image
     * @param height the height of each sprite in the image
     */
    public SpriteSheet(String path, int width, int height) {
        this(GeneralUtils.loadImage(path), width, height);
    }

    /**
     * Crops the sprite from the sprite sheet and returns it.
     *
     * @param x the x value of the sprite on the grid
     * @param y the y value of the sprite on the grid
     * @return the sprite
     */
    public BufferedImage getSprite(int x, int y) {
        return image.getSubimage(x * width, y * height, width, height);
    }
}