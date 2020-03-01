package flamesdev.neon.rendering;

import flamesdev.neon.critical.GameSettings;

import java.awt.*;

/**
 * A class used to render graphics.
 */
public class RenderSystem {
    private static GameSettings settings;

    /**
     * WARNING: Do not call this method. It is only to be called by core library classes.<br>
     * Sets the settings of the render engine. Once a value is defined, it cannot be
     * changed.
     *
     * @param settings the game settings
     */
    public static void setSettings(GameSettings settings) {
        if (RenderSystem.settings == null)
            RenderSystem.settings = settings;
    }

    /**
     * Draws a rectangle.
     *
     * @param graphics  the graphics object used to draw the game's graphics
     * @param rectangle the rectangle to be drawn
     */
    public static void drawRectangle(Graphics graphics, Rectangle rectangle) {
        if (rectangle.color != null)
            graphics.setColor(rectangle.color);

        int[] parameters = new int[] { (int) Math.round(rectangle.hitbox.getLowerXBound() * settings.width),
                (int) Math.round(reverseY(rectangle.hitbox.getHigherYBound() * settings.height)),
                (int) Math.round(rectangle.hitbox.getWidth() * settings.width),
                (int) Math.round(rectangle.hitbox.getHeight() * settings.height) };
        if (rectangle.fill)
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
        graphics.drawImage(imgObj.sprite, (int) Math.round(imgObj.hitbox.getLowerXBound() * settings.width),
                (int) Math.round(reverseY(imgObj.hitbox.getHigherYBound() * settings.height)),
                (int) Math.round(imgObj.hitbox.getWidth() * settings.width),
                (int) Math.round(imgObj.hitbox.getHeight() * settings.height), null);
    }

    /**
     * Draws text.
     *
     * @param graphics the graphics object used to draw the game's graphics
     * @param textObj  the text to be drawn
     */
    public static void drawText(Graphics graphics, TextObject textObj) {
        graphics.setFont(textObj.font);
        graphics.drawString(textObj.text, (int) Math.round(textObj.hitbox.getLowerXBound() * settings.width),
                (int) Math.round(reverseY(textObj.hitbox.getCenter().y * settings.height)));
    }

    private static double reverseY(double y) {
        return settings.height - y;
    }
}