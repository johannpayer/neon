package neon.rendering;

import neon.physics.Hitbox;

/** A class used to align hitboxes. */
public class HitboxAligner {
  private static void alignTop(Hitbox hitbox) {
    hitbox.setHigherYBound(hitbox.getCenter().getY());
  }

  private static void alignBottom(Hitbox hitbox) {
    hitbox.setLowerYBound(hitbox.getCenter().getY());
  }

  private static void alignLeft(Hitbox hitbox) {
    hitbox.setLowerXBound(hitbox.getCenter().getX());
  }

  private static void alignRight(Hitbox hitbox) {
    hitbox.setHigherXBound(hitbox.getCenter().getX());
  }

  /**
   * Aligns the hitbox to the specified alignment.
   *
   * @param hitbox the hitbox
   * @param alignment the alignment
   */
  public static void align(Hitbox hitbox, HitboxAlignment alignment) {
    switch (alignment) {
      case TOP -> alignTop(hitbox);
      case BOTTOM -> alignBottom(hitbox);
      case LEFT -> alignLeft(hitbox);
      case RIGHT -> alignRight(hitbox);
      case TOP_LEFT -> {
        alignTop(hitbox);
        alignLeft(hitbox);
      }
      case TOP_RIGHT -> {
        alignTop(hitbox);
        alignRight(hitbox);
      }
      case BOTTOM_LEFT -> {
        alignBottom(hitbox);
        alignLeft(hitbox);
      }
      case BOTTOM_RIGHT -> {
        alignBottom(hitbox);
        alignRight(hitbox);
      }
    }
  }
}
