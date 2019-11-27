package flamesdev.neon.physics;

import java.awt.Point;

public class Vector2D {
	public double x, y;

	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Vector2D(Point point) {
		this.x = point.getX();
		this.y = point.getY();
	}

	public Vector2D add(Vector2D other) {
		return new Vector2D(x + other.x, y + other.y);
	}

	public Vector2D subtract(Vector2D other) {
		return new Vector2D(x - other.x, y - other.y);
	}

	public Vector2D multiply(Vector2D other) {
		return new Vector2D(x * other.x, y * other.y);
	}

	public Vector2D divide(Vector2D other) {
		return new Vector2D(x / other.x, y / other.y);
	}
}