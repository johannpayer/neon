package flamesdev.neon.physics;

import java.awt.Point;

import flamesdev.neon.critical.GameSettings;
import flamesdev.neon.critical.NeonEngine;

/**
 * A two-dimensional vector with two values: x and y.
 */
public class Vector2D {
	public double x, y;

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

	public Vector2D add(Vector2D other) {
		x += other.x;
		y += other.y;
		return this;
	}

	public Vector2D subtract(Vector2D other) {
		x -= other.x;
		y -= other.y;
		return this;
	}

	public Vector2D multiply(Vector2D other) {
		x *= other.x;
		y *= other.y;
		return this;
	}

	public Vector2D divide(Vector2D other) {
		x /= other.x;
		y /= other.y;
		return this;
	}
}