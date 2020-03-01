package flamesdev.neon.physics;

import flamesdev.neon.utils.GeneralUtils;

/**
 * A fundamental class used to simulate physics. Each game object which has
 * collisions is encapsulated by this class.
 */
public class Hitbox {
    private Vector2D center;
    private double width;
    private double height;

    /**
     * @param center the center point of the hitbox
     * @param width  the width of the hitbox
     * @param height the height of the hitbox
     */
    public Hitbox(Vector2D center, double width, double height) {
        this.center = center;
        this.width = width;
        this.height = height;
    }

    /**
     * @return the lowest x-value contained in the hitbox
     */
    public double getLowerXBound() {
        return center.getX() - width / 2;
    }

    /**
     * Modifies the hitbox's center to match the bound specified.
     *
     * @param bound the specified bound
     */
    public void setLowerXBound(double bound) {
        center.setX(bound + width / 2);
    }

    /**
     * @return the highest x-value contained in the hitbox
     */
    public double getHigherXBound() {
        return center.getX() + width / 2;
    }

    /**
     * Modifies the hitbox's center to match the bound specified.
     *
     * @param bound the specified bound
     */
    public void setHigherXBound(double bound) {
        center.setX(bound - width / 2);
    }

    /**
     * @return the lowest y-value contained in the hitbox
     */
    public double getLowerYBound() {
        return center.getY() - height / 2;
    }

    /**
     * Modifies the hitbox's center to match the bound specified.
     *
     * @param bound the specified bound
     */
    public void setLowerYBound(double bound) {
        center.setY(bound + height / 2);
    }

    /**
     * @return the highest y-value contained in the hitbox
     */
    public double getHigherYBound() {
        return center.getY() + height / 2;
    }

    /**
     * Modifies the hitbox's center to match the bound specified.
     *
     * @param bound the specified bound
     */
    public void setHigherYBound(double bound) {
        center.setY(bound - height / 2);
    }

    /**
     * @return the hitbox's center
     */
    public Vector2D getCenter() {
        return center;
    }

    /**
     * Sets the hitbox's center.
     *
     * @param center the new position
     */
    public void setCenter(Vector2D center) {
        this.center = center;
    }

    /**
     * @return the hitbox's width
     */
    public double getWidth() {
        return width;
    }

    /**
     * Sets the hitbox's width
     *
     * @param width the new width
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * @return the hitbox's height
     */
    public double getHeight() {
        return height;
    }

    /**
     * Sets the hitbox's height
     *
     * @param height the new height
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Shifts the hitbox so that it fits within the specified bounds.
     *
     * @param lowX  the low X bound
     * @param highX the high X bound
     * @param lowY  the low Y bound
     * @param highY the high Y bound
     */
    public void keepWithinBounds(double lowX, double highX, double lowY, double highY) {
        if (getLowerXBound() < lowX)
            setLowerXBound(lowX);
        else if (getHigherXBound() > highX)
            setHigherXBound(highX);
        if (getLowerYBound() < lowY)
            setLowerYBound(lowY);
        else if (getHigherYBound() > highY)
            setHigherYBound(highY);
    }

    /**
     * Shifts the hitbox in order to prevent it from intersecting with another
     * hitbox.
     *
     * @param other the other hitbox
     */
    public void preventIntersection(Hitbox other) {
        if (intersectsHitbox(other)) {
            Vector2D difference = center.safeSubtract(other.getCenter());
            if (Math.abs(difference.getX()) >= Math.abs(difference.getY()))
                if (difference.getX() < 0)
                    setHigherXBound(other.getLowerXBound());
                else
                    setLowerXBound(other.getHigherXBound());
            else if (difference.getY() < 0)
                setHigherYBound(other.getLowerYBound());
            else
                setLowerYBound(other.getHigherYBound());
        }
    }

    /**
     * @param vector the specified vector
     * @return whether the hitbox contains the vector
     */
    public boolean containsVector(Vector2D vector) {
        return GeneralUtils.withinRange(vector.getX(), getLowerXBound(), getHigherXBound())
                && GeneralUtils.withinRange(vector.getY(), getLowerYBound(), getHigherYBound());
    }

    /**
     * @param other the other hitbox
     * @return whether the current hitbox intersects the specified hitbox
     */
    public boolean intersectsHitbox(Hitbox other) {
        Vector2D difference = center.safeSubtract(other.center);
        return Math.abs(difference.getX()) <= GeneralUtils.getAverage(new double[] { width, other.width })
                && Math.abs(difference.getY()) <= GeneralUtils.getAverage(new double[] { height, other.height });
    }
}