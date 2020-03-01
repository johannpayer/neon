package flamesdev.neon.physics;

import flamesdev.neon.critical.GameSettings;
import flamesdev.neon.critical.NeonEngine;

import java.awt.*;

/**
 * A two-dimensional vector with two values: x and y.
 */
public class Vector2D {
    private static GameSettings settings;

    public double x;
    public double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D(Point point) {
        this.x = point.getX();
        this.y = point.getY();

        Point screenPosition = NeonEngine.getPositionOnScreen();
        subtract(new Vector2D(screenPosition.getX(), screenPosition.getY()));
        GameSettings settings = NeonEngine.getSettings();
        divide(new Vector2D(settings.width, settings.height));
        y = 1 - y;
    }

    /**
     * WARNING: Do not call this method. It is only to be called by core library.
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
     * Adds another vector to the current vector.
     *
     * @param other the other vector
     * @return the result of the operation
     */
    public Vector2D add(Vector2D other) {
        x += other.x;
        y += other.y;
        return this;
    }

    /**
     * Subtracts the current vector by another vector.
     *
     * @param other the other vector
     * @return the result of the operation
     */
    public Vector2D subtract(Vector2D other) {
        x -= other.x;
        y -= other.y;
        return this;
    }

    /**
     * Multiplies the current vector by another vector.
     *
     * @param other the other vector
     * @return the result of the operation
     */
    public Vector2D multiply(Vector2D other) {
        x *= other.x;
        y *= other.y;
        return this;
    }

    /**
     * Divides the current vector by another vector.
     *
     * @param other the other vector
     * @return the result of the operation
     */
    public Vector2D divide(Vector2D other) {
        x /= other.x;
        y /= other.y;
        return this;
    }

    /**
     * Adds another vector to the current vector without changing its value.
     *
     * @param other the other vector
     * @return the result of the operation
     */
    public Vector2D safeAdd(Vector2D other) {
        return new Vector2D(x + other.x, y + other.y);
    }

    /**
     * Subtracts the current vector by another vector without changing its value.
     *
     * @param other the other vector
     * @return the result of the operation
     */
    public Vector2D safeSubtract(Vector2D other) {
        return new Vector2D(x - other.x, y - other.y);
    }

    /**
     * Multiplies the current vector by another vector without changing its value.
     *
     * @param other the other vector
     * @return the result of the operation
     */
    public Vector2D safeMultiply(Vector2D other) {
        return new Vector2D(x * other.x, y * other.y);
    }

    /**
     * Divides the current vector by another vector without changing its value.
     *
     * @param other the other vector
     * @return the result of the operation
     */
    public Vector2D safeDivide(Vector2D other) {
        return new Vector2D(x / other.x, y / other.y);
    }
}