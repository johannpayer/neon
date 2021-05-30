package neon.rendering;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import neon.physics.RectangularHitbox;
import neon.physics.Vector2D;

/** A class used for representing text game objects. */
public class TextObject {
  private Vector2D position;
  private RectangularHitbox hitbox;
  private double descent;
  private String text;
  private Font font;
  private Color color;
  private HitboxAlignment alignment;

  private void init(Graphics graphics, Vector2D position, String text, Font font, Color color) {
    this.position = position;
    this.font = font;
    this.color = color;
    setText(graphics, text);
  }

  /**
   * @param graphics the graphics object used to determine the size of the text
   * @param position the position of the text
   * @param text the text
   * @param font the font of the text
   * @param color the color of the text
   */
  public TextObject(Graphics graphics, Vector2D position, String text, Font font, Color color) {
    init(graphics, position, text, font, color);
  }

  /**
   * @param graphics the graphics object used to determine the size of the text
   * @param position the position of the text
   * @param text the text
   * @param font the font of the text
   * @param color the color of the text
   */
  public TextObject(
      Graphics graphics,
      Vector2D position,
      String text,
      Font font,
      Color color,
      HitboxAlignment alignment) {
    this.alignment = alignment;
    init(graphics, position, text, font, color);
  }

  private void updateHitbox(Graphics graphics) {
    FontMetrics metrics = graphics.getFontMetrics(font);
    Rectangle2D size = metrics.getStringBounds(text, graphics);
    hitbox =
        new RectangularHitbox(
            position.copy(),
            UnitConverter.toWidthFromPixels(size.getWidth()),
            UnitConverter.toHeightFromPixels(size.getHeight()));
    if (alignment != null) {
      HitboxAligner.align(hitbox, alignment);
    }

    descent = UnitConverter.toHeightFromPixels(metrics.getDescent());
  }

  /**
   * Sets the object's position.
   *
   * @param graphics the graphics object used to determine the size of the text
   * @param position the new position
   */
  public void setPosition(Graphics graphics, Vector2D position) {
    this.position = position;
    updateHitbox(graphics);
  }

  /**
   * Sets the object's text.
   *
   * @param graphics the graphics object used to determine the size of the text
   * @param text the new text
   */
  public void setText(Graphics graphics, String text) {
    this.text = text;
    updateHitbox(graphics);
  }

  /**
   * Sets the object's alignment.
   *
   * @param graphics the graphics object used to determine the size of the text
   * @param alignment the new alignment
   */
  public void setAlignment(Graphics graphics, HitboxAlignment alignment) {
    this.alignment = alignment;
    updateHitbox(graphics);
  }

  /** @return the object's hitbox */
  public RectangularHitbox getHitbox() {
    return hitbox;
  }

  /** @return the object's descent */
  public double getDescent() {
    return descent;
  }

  /** @return the object's text */
  public String getText() {
    return text;
  }

  /** @return the object's font */
  public Font getFont() {
    return font;
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
