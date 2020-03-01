package flamesdev.neon.physics;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * An interface for hitboxes.
 */
public interface Hitbox {
    /**
     * @return the lowest x-value contained in the hitbox
     */
    double getLowerXBound();

    /**
     * Modifies the hitbox's center to match the bound specified.
     *
     * @param bound the specified bound
     */
    void setLowerXBound(double bound);

    /**
     * @return the highest x-value contained in the hitbox
     */
    double getHigherXBound();

    /**
     * Modifies the hitbox's center to match the bound specified.
     *
     * @param bound the specified bound
     */
    void setHigherXBound(double bound);

    /**
     * @return the lowest y-value contained in the hitbox
     */
    double getLowerYBound();

    /**
     * Modifies the hitbox's center to match the bound specified.
     *
     * @param bound the specified bound
     */
    void setLowerYBound(double bound);

    /**
     * @return the highest y-value contained in the hitbox
     */
    double getHigherYBound();

    /**
     * Modifies the hitbox's center to match the bound specified.
     *
     * @param bound the specified bound
     */
    void setHigherYBound(double bound);

    /**
     * @return the hitbox's center
     */
    Vector2D getCenter();

    /**
     * Sets the hitbox's center.
     *
     * @param center the new position
     */
    void setCenter(Vector2D center);

    /**
     * @return the hitbox's width
     */
    double getWidth();

    /**
     * @return the hitbox's height
     */
    double getHeight();

    /**
     * @param vector the specified vector
     * @return whether the hitbox contains the vector
     */
    boolean containsVector(Vector2D vector);

    /**
     * Shifts the hitbox so that it fits within the specified bounds.
     *
     * @param lowX  the low X bound
     * @param highX the high X bound
     * @param lowY  the low Y bound
     * @param highY the high Y bound
     */
    void keepWithinBounds(double lowX, double highX, double lowY, double highY);

    /**
     * @param other the other hitbox
     * @return whether the current hitbox intersects the specified hitbox
     */
    boolean intersectsHitbox(Hitbox other) throws NotImplementedException;

    /**
     * Shifts the hitbox in order to prevent it from intersecting with another
     * hitbox.
     *
     * @param other the other hitbox
     */
    void preventIntersection(Hitbox other) throws NotImplementedException;
}