package neon.physics;

import neon.critical.GameSettings;
import neon.critical.NeonEngine;

import java.awt.Point;

/**
 * A two-dimensional vector with two values: x and y.
 */
public class Vector2D {
    private double x;
    private double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D(Point point) {
        this(point.getX(), point.getY());
    }

    /**
     * WARNING: Do not call this method. It is only to be called by core library classes.
     */
    public void convertCoordinateSystem() {
        GameSettings settings = NeonEngine.getSettings();
        divide(settings.getWidth(), settings.getHeight());
        y = 1 - y;
    }

    /**
     * @return the vector's x-value
     */
    public double getX() {
        return x;
    }

    /**
     * Sets the vector's x-value.
     *
     * @param x the new x-value
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @return the vector's y-value
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the vector's y-value.
     *
     * @param y the new y-value
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Adds another vector to the current vector.
     *
     * @param other the other vector
     */
    public void add(Vector2D other) {
        add(other.x, other.y);
    }

    /**
     * Adds values to the current vector.
     *
     * @param x the x-value
     * @param y the y-value
     */
    public void add(double x, double y) {
        this.x += x;
        this.y += y;
    }

    /**
     * Subtracts the current vector by another vector.
     *
     * @param other the other vector
     */
    public void subtract(Vector2D other) {
        subtract(other.x, other.y);
    }

    /**
     * Subtracts values to the current vector.
     *
     * @param x the x-value
     * @param y the y-value
     */
    public void subtract(double x, double y) {
        this.x -= x;
        this.y -= y;
    }

    /**
     * Multiplies the current vector by another vector.
     *
     * @param other the other vector
     */
    public void multiply(Vector2D other) {
        multiply(other.x, other.y);
    }

    /**
     * Multiplies values to the current vector.
     *
     * @param x the x-value
     * @param y the y-value
     */
    public void multiply(double x, double y) {
        this.x *= x;
        this.y *= y;
    }

    /**
     * Divides the current vector by another vector.
     *
     * @param other the other vector
     */
    public void divide(Vector2D other) {
        divide(other.x, other.y);
    }

    /**
     * Divides values to the current vector.
     *
     * @param x the x-value
     * @param y the y-value
     */
    public void divide(double x, double y) {
        this.x /= x;
        this.y /= y;
    }

    /**
     * Adds another vector to the current vector without changing its value.
     *
     * @param other the other vector
     * @return the result of the operation
     */
    public Vector2D safeAdd(Vector2D other) {
        return safeAdd(other.x, other.y);
    }

    /**
     * Adds values to the current vector without changing its value.
     *
     * @param x the x-value
     * @param y the y-value
     * @return the result of the operation
     */
    public Vector2D safeAdd(double x, double y) {
        return new Vector2D(this.x + x, this.y + y);
    }

    /**
     * Subtracts the current vector by another vector without changing its value.
     *
     * @param other the other vector
     * @return the result of the operation
     */
    public Vector2D safeSubtract(Vector2D other) {
        return safeSubtract(other.x, other.y);
    }

    /**
     * Subtracts values to the current vector without changing its value.
     *
     * @param x the x-value
     * @param y the y-value
     * @return the result of the operation
     */
    public Vector2D safeSubtract(double x, double y) {
        return new Vector2D(this.x - x, this.y - y);
    }

    /**
     * Multiplies the current vector by another vector without changing its value.
     *
     * @param other the other vector
     * @return the result of the operation
     */
    public Vector2D safeMultiply(Vector2D other) {
        return safeMultiply(other.x, other.y);
    }

    /**
     * Multiplies values to the current vector without changing its value.
     *
     * @param x the x-value
     * @param y the y-value
     * @return the result of the operation
     */
    public Vector2D safeMultiply(double x, double y) {
        return new Vector2D(this.x * x, this.y * y);
    }

    /**
     * Divides the current vector by another vector without changing its value.
     *
     * @param other the other vector
     * @return the result of the operation
     */
    public Vector2D safeDivide(Vector2D other) {
        return safeDivide(other.x, other.y);
    }

    /**
     * Divides values to the current vector without changing its value.
     *
     * @param x the x-value
     * @param y the y-value
     * @return the result of the operation
     */
    public Vector2D safeDivide(double x, double y) {
        return new Vector2D(this.x / x, this.y / y);
    }

    /**
     * @return a copy of the object
     */
    public Vector2D copy() {
        return new Vector2D(x, y);
    }
}