package neon.physics;

import neon.rendering.Units;

/**
 * A class used to simulate the physics and interactions of circular hitboxes.
 */
public class CircularHitbox extends Hitbox {
    private double radius;
    private double width;
    private double height;

    public CircularHitbox(Vector2d center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public double getLowerXBound() {
        return center.getX() - width / 2;
    }

    public void setLowerXBound(double bound) {
        center.setX(bound + width / 2);
    }

    public double getHigherXBound() {
        return center.getX() + width / 2;
    }

    public void setHigherXBound(double bound) {
        center.setX(bound - width / 2);
    }

    public double getLowerYBound() {
        return center.getY() - height / 2;
    }

    public void setLowerYBound(double bound) {
        center.setY(bound + height / 2);
    }

    public double getHigherYBound() {
        return center.getY() + height / 2;
    }

    public void setHigherYBound(double bound) {
        center.setY(bound - height / 2);
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    /**
     * Sets the hitbox's radius in width units.
     *
     * @param radius the new radius
     */
    public void setRadiusWidth(double radius) {
        this.radius = radius;
        width = radius * 2;
        height = Units.toHeight(width);
    }

    /**
     * Sets the hitbox's radius in height units.
     *
     * @param radius the new radius
     */
    public void setRadiusHeight(double radius) {
        this.radius = Units.toWidth(radius);
        width = this.radius * 2;
        height = radius * 2;
    }

    public boolean containsVector(Vector2d vector) {
        Vector2d difference = center.safeSubtract(vector);
        return Math.sqrt(Math.pow(difference.getX(), 2) + Math.pow(Units.toWidth(difference.getY()), 2)) <= radius;
    }

    /**
     * WARNING: This method is unimplemented.
     *
     * @param other the other hitbox
     * @throws UnsupportedOperationException always
     */
    public boolean unintersect(Hitbox other) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("This method is not currently implemented.");
    }

    /**
     * WARNING: This method is unimplemented.
     *
     * @param other the other hitbox
     * @throws UnsupportedOperationException always
     */
    public boolean intersectsHitbox(Hitbox other) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("This method is not currently implemented.");
    }

    public CircularHitbox copy() {
        return new CircularHitbox(center.copy(), radius);
    }
}
