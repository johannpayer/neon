package flamesdev.neon.physics;

import flamesdev.neon.utils.GeneralUtils;

/**
 * A class used to simulate the physics and interactions of rectangular hitboxes.
 */
public class RectangularHitbox implements Hitbox {
    private Vector2D center;
    private double width;
    private double height;

    public RectangularHitbox(Vector2D center, double width, double height) {
        this.center = center;
        this.width = width;
        this.height = height;
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

    public Vector2D getCenter() {
        return center;
    }

    public void setCenter(Vector2D center) {
        this.center = center;
    }

    public double getWidth() {
        return width;
    }

    /**
     * Sets the collider's width
     *
     * @param width the new width
     */
    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    /**
     * Sets the collider's height
     *
     * @param height the new height
     */
    public void setHeight(double height) {
        this.height = height;
    }

    public boolean containsVector(Vector2D vector) {
        return GeneralUtils.withinRange(vector.getX(), getLowerXBound(), getHigherXBound())
                && GeneralUtils.withinRange(vector.getY(), getLowerYBound(), getHigherYBound());
    }

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

    public boolean intersectsHitbox(Hitbox other) {
        assert other instanceof RectangularHitbox;
        Vector2D difference = center.safeSubtract(other.getCenter());
        return Math.abs(difference.getX()) <= GeneralUtils.getAverage(new double[] { width, other.getWidth() })
                && Math.abs(difference.getY()) <= GeneralUtils.getAverage(new double[] { height, other.getHeight() });
    }
}