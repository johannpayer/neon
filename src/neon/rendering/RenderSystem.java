package neon.rendering;

import java.awt.Color;
import java.awt.Graphics;
import neon.critical.NeonEngine;
import neon.critical.WindowSettings;
import neon.physics.Hitbox;
import neon.physics.RectangularHitbox;

/** A class used to render graphics. */
public class RenderSystem {
  /**
   * Draws a rectangle.
   *
   * @param graphics the graphics object used to draw the game's graphics
   * @param rectangle the rectangle to be drawn
   */
  public static void drawRectangle(Graphics graphics, Rectangle rectangle) {
    Color color = rectangle.getColor();
    if (color != null) {
      graphics.setColor(color);
    }

    RectangularHitbox hitbox = rectangle.getHitbox();
    WindowSettings settings = NeonEngine.getSettings().windowSettings;
    int width = settings.width;
    int height = settings.height;
    int[] parameters =
        new int[] {
          (int) Math.round(hitbox.getLowerXBound() * width),
          (int) Math.round(reverseY(hitbox.getHigherYBound() * height)),
          (int) Math.round(hitbox.getWidth() * width),
          (int) Math.round(hitbox.getHeight() * height)
        };
    if (rectangle.isDoFill()) {
      graphics.fillRect(parameters[0], parameters[1], parameters[2], parameters[3]);
    } else {
      graphics.drawRect(parameters[0], parameters[1], parameters[2], parameters[3]);
    }
  }

  /**
   * Draws a rectangle if it is in view.
   *
   * @param graphics the graphics object used to draw the game's graphics
   * @param rectangle the rectangle to be drawn
   */
  public static void smartDrawRectangle(Graphics graphics, Rectangle rectangle) {
    if (isHitboxInView(rectangle.getHitbox())) {
      drawRectangle(graphics, rectangle);
    }
  }

  /**
   * Draws an image.
   *
   * @param graphics the graphics object used to draw the game's graphics
   * @param imgObj the image to be drawn
   */
  public static void drawImage(Graphics graphics, ImageObject imgObj) {
    Hitbox hitbox = imgObj.getHitbox();
    WindowSettings settings = NeonEngine.getSettings().windowSettings;
    int width = settings.width;
    int height = settings.height;
    graphics.drawImage(
        imgObj.getSprite(),
        (int) Math.round(hitbox.getLowerXBound() * width),
        (int) Math.round(reverseY(hitbox.getHigherYBound() * height)),
        (int) Math.round(hitbox.getWidth() * width),
        (int) Math.round(hitbox.getHeight() * height),
        null);
  }

  /**
   * Draws an image if it is in view.
   *
   * @param graphics the graphics object used to draw the game's graphics
   * @param imgObj the image to be drawn
   */
  public static void smartDrawImage(Graphics graphics, ImageObject imgObj) {
    if (isHitboxInView(imgObj.getHitbox())) {
      drawImage(graphics, imgObj);
    }
  }

  /**
   * Draws text.
   *
   * @param graphics the graphics object used to draw the game's graphics
   * @param textObj the text to be drawn
   */
  public static void drawText(Graphics graphics, TextObject textObj) {
    graphics.setFont(textObj.font);
    graphics.setColor(textObj.getColor());

    WindowSettings settings = NeonEngine.getSettings().windowSettings;
    graphics.drawString(
        textObj.text,
        (int) Math.round(textObj.hitbox.getLowerXBound() * settings.width),
        (int)
            Math.round(
                reverseY(
                    (textObj.hitbox.getCenter().getY() - textObj.descent * 1.5)
                        * settings.height)));
  }

  /**
   * Draws text if it is in view.
   *
   * @param graphics the graphics object used to draw the game's graphics
   * @param textObj the text to be drawn
   */
  public static void smartDrawText(Graphics graphics, TextObject textObj) {
    if (isHitboxInView(textObj.hitbox)) {
      drawText(graphics, textObj);
    }
  }

  /**
   * @param hitbox the specified hitbox
   * @return whether the hitbox is within view
   */
  public static boolean isHitboxInView(Hitbox hitbox) {
    return !(hitbox.getHigherXBound() < 0
        || hitbox.getLowerXBound() > 1
        || hitbox.getHigherYBound() < 0
        || hitbox.getLowerYBound() > 1);
  }

  private static double reverseY(double y) {
    return NeonEngine.getSettings().windowSettings.height - y;
  }
}
