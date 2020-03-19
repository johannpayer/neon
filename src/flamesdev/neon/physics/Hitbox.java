package flamesdev.neon.physics;

/**
 * An interface for hitboxes.
 */
public abstract class Hitbox {
    protected Vector2D center;

    /**
     * @return the lowest x-value contained in the hitbox
     */
    public abstract double getLowerXBound();

    /**
     * Modifies the hitbox's center to match the bound specified.
     *
     * @param bound the specified bound
     */
    public abstract void setLowerXBound(double bound);

    /**
     * @return the highest x-value contained in the hitbox
     */
    public abstract double getHigherXBound();

    /**
     * Modifies the hitbox's center to match the bound specified.
     *
     * @param bound the specified bound
     */
    public abstract void setHigherXBound(double bound);

    /**
     * @return the lowest y-value contained in the hitbox
     */
    public abstract double getLowerYBound();

    /**
     * Modifies the hitbox's center to match the bound specified.
     *
     * @param bound the specified bound
     */
    public abstract void setLowerYBound(double bound);

    /**
     * @return the highest y-value contained in the hitbox
     */
    public abstract double getHigherYBound();

    /**
     * Modifies the hitbox's center to match the bound specified.
     *
     * @param bound the specified bound
     */
    public abstract void setHigherYBound(double bound);

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
    public abstract double getWidth();

    /**
     * @return the hitbox's height
     */
    public abstract double getHeight();

    /**
     * @param vector the specified vector
     * @return whether the hitbox contains the vector
     */
    public abstract boolean containsVector(Vector2D vector);

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
     * @param other the other hitbox
     * @return whether the current hitbox intersects the specified hitbox
     */
    public abstract boolean intersectsHitbox(Hitbox other) throws UnsupportedOperationException;

    /**
     * Shifts the hitbox in order to prevent it from intersecting with another
     * hitbox.
     *
     * @param other the other hitbox
     * @return whether the hitboxes collided
     */
    public abstract boolean preventIntersection(Hitbox other) throws UnsupportedOperationException;

    /**
     * @return a copy of the object
     */
    public abstract Hitbox copy();
}