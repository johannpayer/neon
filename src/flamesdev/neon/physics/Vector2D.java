package flamesdev.neon.physics;

import flamesdev.neon.critical.GameSettings;
import flamesdev.neon.critical.NeonEngine;

import java.awt.*;

/**
 * A two-dimensional vector with two values: x and y.
 */
public class Vector2D {
    private static GameSettings settings;
    private double x;
    private double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D(Point point, boolean directMap) {
        this(point.getX(), point.getY());

        if (!directMap) {
            Point screenPosition = NeonEngine.getPositionOnScreen();
            subtract(new Vector2D(screenPosition, true));
            divide(settings.getWidth(), settings.getHeight());
            this.y = 1 - this.y;
        }
    }

    /**
     * WARNING: Do not call this method. It is only to be called by core library
     * classes.<br>
     * Sets the settings of the render engine. Once a value is defined, it cannot be
     * changed.
     *
     * @param settings the game settings
     */
    public static void setSettings(GameSettings settings) {
        if (Vector2D.settings == null)
            Vector2D.settings = settings;
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
     * @return the result of the operation
     */
    public Vector2D add(Vector2D other) {
        add(other.x, other.y);
        return this;
    }

    /**
     * Adds values to the current vector.
     *
     * @param x the x-value
     * @param y the y-value
     * @return the result of the operation
     */
    public Vector2D add(double x, double y) {
        this.x += x;
        this.y += y;
        return this;
    }

    /**
     * Subtracts the current vector by another vector.
     *
     * @param other the other vector
     * @return the result of the operation
     */
    public Vector2D subtract(Vector2D other) {
        subtract(other.x, other.y);
        return this;
    }

    /**
     * Subtracts values to the current vector.
     *
     * @param x the x-value
     * @param y the y-value
     * @return the result of the operation
     */
    public Vector2D subtract(double x, double y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    /**
     * Multiplies the current vector by another vector.
     *
     * @param other the other vector
     * @return the result of the operation
     */
    public Vector2D multiply(Vector2D other) {
        multiply(other.x, other.y);
        return this;
    }

    /**
     * Multiplies values to the current vector.
     *
     * @param x the x-value
     * @param y the y-value
     * @return the result of the operation
     */
    public Vector2D multiply(double x, double y) {
        this.x *= x;
        this.y *= y;
        return this;
    }

    /**
     * Divides the current vector by another vector.
     *
     * @param other the other vector
     * @return the result of the operation
     */
    public Vector2D divide(Vector2D other) {
        divide(other.x, other.y);
        return this;
    }

    /**
     * Divides values to the current vector.
     *
     * @param x the x-value
     * @param y the y-value
     * @return the result of the operation
     */
    public Vector2D divide(double x, double y) {
        this.x /= x;
        this.y /= y;
        return this;
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

    @Override
    public Vector2D clone() {
        return new Vector2D(this.x, this.y);
    }
}