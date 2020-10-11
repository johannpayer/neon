package neon.rendering;

import java.awt.image.BufferedImage;
import neon.physics.Hitbox;

/** A class containing a Hitbox and BufferedImage property. */
public class ImageObject {
  private Hitbox hitbox;
  private BufferedImage sprite;

  /**
   * @param hitbox the hitbox of the object
   * @param sprite the sprite of the object
   */
  public ImageObject(Hitbox hitbox, BufferedImage sprite) {
    this.hitbox = hitbox;
    this.sprite = sprite;
  }

  /** @return the object's hitbox */
  public Hitbox getHitbox() {
    return hitbox;
  }

  /**
   * Sets the object's hitbox.
   *
   * @param hitbox the new hitbox
   */
  public void setHitbox(Hitbox hitbox) {
    this.hitbox = hitbox;
  }

  /** @return the object's sprite */
  public BufferedImage getSprite() {
    return sprite;
  }

  /**
   * Sets the object's sprite.
   *
   * @param sprite the new sprite
   */
  public void setSprite(BufferedImage sprite) {
    this.sprite = sprite;
  }

  /** @return a copy of the object */
  public ImageObject copy() {
    return new ImageObject(hitbox.copy(), sprite);
  }
}
