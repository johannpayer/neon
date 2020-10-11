package neon.rendering;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import neon.physics.RectangularHitbox;
import neon.physics.Vector2D;

/** A class containing a Hitbox, String, and Font property. */
public class TextObject {
  public final RectangularHitbox hitbox;
  public final double descent;
  public final String text;
  public final Font font;
  private Color color;

  /**
   * @param position the position of the text
   * @param text the text
   * @param font the font of the text
   * @param color the color of the text
   * @param graphics the graphics used to determine the size of the text
   */
  public TextObject(Vector2D position, String text, Font font, Color color, Graphics graphics) {
    this.text = text;
    this.font = font;
    this.color = color;

    FontMetrics metrics = graphics.getFontMetrics(font);
    Rectangle2D size = metrics.getStringBounds(text, graphics);
    hitbox =
        new RectangularHitbox(
            position, Units.toPixelWidth(size.getWidth()), Units.toPixelHeight(size.getHeight()));
    descent = Units.toPixelHeight(metrics.getDescent());
  }

  /** @return the object's color */
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
