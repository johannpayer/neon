package neon.rendering;

import neon.physics.Hitbox;

import java.awt.Color;

/** A class used to store information about a drawable shape's hitbox, color, and fill setting. */
public class DrawableShape {
  protected Hitbox hitbox;
  protected Color color;
  protected boolean doFill;

  /**
   * @param hitbox the shape's hitbox
   * @param color the shape's color
   * @param doFill whether the shape should be filled when drawn
   */
  public DrawableShape(Hitbox hitbox, Color color, boolean doFill) {
    this.hitbox = hitbox;
    this.color = color;
    this.doFill = doFill;
  }

  /** @return the shape's hitbox */
  public Hitbox getHitbox() {
    return hitbox;
  }

  /**
   * Sets the shape's hitbox.
   *
   * @param hitbox the new hitbox
   */
  public void setHitbox(Hitbox hitbox) {
    this.hitbox = hitbox;
  }

  /** @return the shape's color */
  public Color getColor() {
    return color;
  }

  /**
   * Sets the shape's color.
   *
   * @param color the new color
   */
  public void setColor(Color color) {
    this.color = color;
  }

  /** @return whether the shape is to be filled */
  public boolean doFill() {
    return doFill;
  }

  /**
   * Sets whether the shape is to be filled.
   *
   * @param doFill the new value
   */
  public void setDoFill(boolean doFill) {
    this.doFill = doFill;
  }

  public DrawableShape copy() {
    return new DrawableShape(hitbox.copy(), new Color(color.getRGB()), doFill);
  }
}
