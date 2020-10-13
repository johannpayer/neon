package neon.rendering;

import java.awt.Color;
import neon.physics.RectangularHitbox;

/** A representation of a rectangle with a hitbox, color, and fill setting. */
public class Rectangle {
  private RectangularHitbox hitbox;
  private Color color;
  private boolean doFill;

  /**
   * @param hitbox the hitbox of the rectangle
   * @param color the color of the rectangle
   * @param doFill whether the rectangle should be filled when drawn
   */
  public Rectangle(RectangularHitbox hitbox, Color color, boolean doFill) {
    this.hitbox = hitbox;
    this.color = color;
    this.doFill = doFill;
  }

  /** @return the rectangle's hitbox */
  public RectangularHitbox getHitbox() {
    return hitbox;
  }

  /**
   * Sets the rectangle's hitbox.
   *
   * @param hitbox the new hitbox
   */
  public void setHitbox(RectangularHitbox hitbox) {
    this.hitbox = hitbox;
  }

  /** @return the rectangle's color */
  public Color getColor() {
    return color;
  }

  /**
   * Sets the rectangle's color.
   *
   * @param color the new color
   */
  public void setColor(Color color) {
    this.color = color;
  }

  /** @return whether is rectangle is to be filled */
  public boolean isDoFill() {
    return doFill;
  }

  /**
   * Sets whether the rectangle is to be filled.
   *
   * @param doFill the new value
   */
  public void setDoFill(boolean doFill) {
    this.doFill = doFill;
  }

  public Rectangle copy() {
    return new Rectangle(hitbox.copy(), new Color(color.getRGB()), doFill);
  }
}
