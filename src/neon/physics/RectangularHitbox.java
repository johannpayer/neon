package neon.physics;

import neon.critical.GameSettings;
import neon.critical.NeonEngine;
import neon.utils.GeneralUtils;
import java.awt.image.BufferedImage;

/**
 * A class used to simulate the physics and interactions of rectangular hitboxes.
 */
public class RectangularHitbox extends Hitbox {
    private double width;
    private double height;

    public RectangularHitbox(Vector2D center, double width, double height) {
        this.center = center;
        this.width = width;
        this.height = height;
    }

    public RectangularHitbox(Vector2D center, BufferedImage image, double scale) {
        GameSettings settings = NeonEngine.getSettings();
        this.center = center;
        width = image.getWidth() * scale / settings.getWidth();
        height = image.getHeight() * scale / settings.getHeight();
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

    /**
     * Sets the collider's width.
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
     * Sets the collider's height.
     *
     * @param height the new height
     */
    public void setHeight(double height) {
        this.height = height;
    }

    public boolean containsVector(Vector2D vector) {
        return GeneralUtils.isWithinRange(vector.getX(), getLowerXBound(), getHigherXBound()) &&
                GeneralUtils.isWithinRange(vector.getY(), getLowerYBound(), getHigherYBound());
    }

    public boolean preventIntersection(Hitbox other) {
        boolean intersects = intersectsHitbox(other);
        if (intersects) {
            Vector2D difference = center.safeSubtract(other.getCenter());
            if (Math.abs(difference.getX()) >= Math.abs(difference.getY())) {
                if (difference.getX() < 0) {
                    setHigherXBound(other.getLowerXBound());
                } else {
                    setLowerXBound(other.getHigherXBound());
                }
            } else if (difference.getY() < 0) {
                setHigherYBound(other.getLowerYBound());
            } else {
                setLowerYBound(other.getHigherYBound());
            }
        }

        return intersects;
    }

    public boolean intersectsHitbox(Hitbox other) {
        assert other instanceof RectangularHitbox;
        Vector2D difference = center.safeSubtract(other.getCenter());
        return Math.abs(difference.getX()) < GeneralUtils.getAverage(new double[] { width, other.getWidth() }) &&
                Math.abs(difference.getY()) < GeneralUtils.getAverage(new double[] { height, other.getHeight() });
    }

    public RectangularHitbox copy() {
        return new RectangularHitbox(center.copy(), width, height);
    }
}