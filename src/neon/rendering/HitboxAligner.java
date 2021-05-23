package neon.rendering;

import neon.physics.Hitbox;

/** A class used to align hitboxes. */
public class HitboxAligner {
  /**
   * Aligns the hitbox to its top.
   *
   * @param hitbox the hitbox
   */
  public static void alignTop(Hitbox hitbox) {
    hitbox.setHigherYBound(hitbox.getCenter().getY());
  }

  /**
   * Aligns the hitbox to its bottom.
   *
   * @param hitbox the hitbox
   */
  public static void alignBottom(Hitbox hitbox) {
    hitbox.setLowerYBound(hitbox.getCenter().getY());
  }

  /**
   * Aligns the hitbox to its left.
   *
   * @param hitbox the hitbox
   */
  public static void alignLeft(Hitbox hitbox) {
    hitbox.setLowerXBound(hitbox.getCenter().getX());
  }

  /**
   * Aligns the hitbox to its right.
   *
   * @param hitbox the hitbox
   */
  public static void alignRight(Hitbox hitbox) {
    hitbox.setHigherXBound(hitbox.getCenter().getX());
  }

  /**
   * Aligns the hitbox to its top left.
   *
   * @param hitbox the hitbox
   */
  public static void alignTopLeft(Hitbox hitbox) {
    alignTop(hitbox);
    alignLeft(hitbox);
  }

  /**
   * Aligns the hitbox to its top right.
   *
   * @param hitbox the hitbox
   */
  public static void alignTopRight(Hitbox hitbox) {
    alignTop(hitbox);
    alignRight(hitbox);
  }

  /**
   * Aligns the hitbox to its bottom left.
   *
   * @param hitbox the hitbox
   */
  public static void alignBottomLeft(Hitbox hitbox) {
    alignBottom(hitbox);
    alignLeft(hitbox);
  }

  /**
   * Aligns the hitbox to its bottom right.
   *
   * @param hitbox the hitbox
   */
  public static void alignBottomRight(Hitbox hitbox) {
    alignBottom(hitbox);
    alignRight(hitbox);
  }
}
