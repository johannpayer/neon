package neon.physics;

import neon.rendering.UnitConverter;

/** A class used to simulate the physics and interactions of circular hitboxes. */
public class CircularHitbox extends Hitbox {
  /** The radius of the circle in width units. */
  private double radius;

  /**
   * @param center the center of the hitbox
   * @param radius the radius of the hitbox in width units
   */
  public CircularHitbox(Vector2D center, double radius) {
    this.center = center;
    this.radius = radius;
  }

  public double getLowerXBound() {
    return center.getX() - getWidth() / 2;
  }

  public void setLowerXBound(double bound) {
    center.setX(bound + getWidth() / 2);
  }

  public double getHigherXBound() {
    return center.getX() + getWidth() / 2;
  }

  public void setHigherXBound(double bound) {
    center.setX(bound - getWidth() / 2);
  }

  public double getLowerYBound() {
    return center.getY() - getHeight() / 2;
  }

  public void setLowerYBound(double bound) {
    center.setY(bound + getHeight() / 2);
  }

  public double getHigherYBound() {
    return center.getY() + getHeight() / 2;
  }

  public void setHigherYBound(double bound) {
    center.setY(bound - getHeight() / 2);
  }

  /** @return the radius of the hitbox in width units */
  public double getRadiusWidth() {
    return radius;
  }

  /**
   * Sets the hitbox's radius in width units.
   *
   * @param radius the new radius
   */
  public void setRadiusWidth(double radius) {
    this.radius = radius;
  }

  /** @return the radius of the hitbox in height units */
  public double getRadiusHeight() {
    return UnitConverter.toHeight(radius);
  }

  /**
   * Sets the hitbox's radius in height units.
   *
   * @param radius the new radius
   */
  public void setRadiusHeight(double radius) {
    this.radius = UnitConverter.toWidth(radius);
  }

  public double getWidth() {
    return radius * 2;
  }

  public double getHeight() {
    return UnitConverter.toHeight(getWidth());
  }

  public boolean doesContainVector(Vector2D vector) {
    Vector2D difference = center.safeSubtract(vector);
    return Math.sqrt(
            Math.pow(difference.getX(), 2) + Math.pow(UnitConverter.toWidth(difference.getY()), 2))
        <= radius;
  }

  /**
   * WARNING: This method is unimplemented.
   *
   * @param other the other hitbox
   * @throws UnsupportedOperationException always
   */
  public boolean doesIntersectHitbox(Hitbox other) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This method is not currently implemented.");
  }

  /**
   * WARNING: This method is unimplemented.
   *
   * @param other the other hitbox
   * @throws UnsupportedOperationException always
   */
  public boolean preventIntersection(Hitbox other) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("This method is not currently implemented.");
  }

  public CircularHitbox copy() {
    return new CircularHitbox(center.copy(), radius);
  }
}
