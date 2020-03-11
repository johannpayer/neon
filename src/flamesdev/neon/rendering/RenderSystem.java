package flamesdev.neon.rendering;

import flamesdev.neon.critical.GameSettings;
import flamesdev.neon.critical.NeonEngine;
import flamesdev.neon.physics.Hitbox;
import flamesdev.neon.physics.RectangularHitbox;

import java.awt.Color;
import java.awt.Graphics;

/**
 * A class used to render graphics.
 */
public class RenderSystem {
    /**
     * Draws a rectangle.
     *
     * @param graphics  the graphics object used to draw the game's graphics
     * @param rectangle the rectangle to be drawn
     */
    public static void drawRectangle(Graphics graphics, Rectangle rectangle) {
        Color color = rectangle.getColor();
        if (color != null)
            graphics.setColor(color);

        RectangularHitbox hitbox = rectangle.getHitbox();
        GameSettings settings = NeonEngine.getSettings();
        int width = settings.getWidth();
        int height = settings.getHeight();
        int[] parameters = new int[] { (int) Math.round(hitbox.getLowerXBound() * width),
                (int) Math.round(reverseY(hitbox.getHigherYBound() * height)),
                (int) Math.round(hitbox.getWidth() * width), (int) Math.round(hitbox.getHeight() * height) };
        if (rectangle.isFill())
            graphics.fillRect(parameters[0], parameters[1], parameters[2], parameters[3]);
        else
            graphics.drawRect(parameters[0], parameters[1], parameters[2], parameters[3]);
    }

    /**
     * Draws an image.
     *
     * @param graphics the graphics object used to draw the game's graphics
     * @param imgObj   the image to be drawn
     */
    public static void drawImage(Graphics graphics, ImageObject imgObj) {
        Hitbox hitbox = imgObj.getHitbox();
        GameSettings settings = NeonEngine.getSettings();
        int width = settings.getWidth();
        int height = settings.getHeight();
        graphics.drawImage(imgObj.getSprite(), (int) Math.round(hitbox.getLowerXBound() * width),
                (int) Math.round(reverseY(hitbox.getHigherYBound() * height)),
                (int) Math.round(hitbox.getWidth() * width), (int) Math.round(hitbox.getHeight() * height), null);
    }

    /**
     * Draws text.
     *
     * @param graphics the graphics object used to draw the game's graphics
     * @param textObj  the text to be drawn
     */
    public static void drawText(Graphics graphics, TextObject textObj) {
        graphics.setFont(textObj.font);
        graphics.setColor(textObj.getColor());

        GameSettings settings = NeonEngine.getSettings();
        int width = settings.getWidth();
        int height = settings.getHeight();
        graphics.drawString(textObj.text, (int) Math.round(textObj.hitbox.getLowerXBound() * width),
                (int) Math.round(reverseY((textObj.hitbox.getCenter().getY() - textObj.descent * 1.5) * height)));
    }

    private static double reverseY(double y) {
        GameSettings settings = NeonEngine.getSettings();
        return settings.getHeight() - y;
    }
}