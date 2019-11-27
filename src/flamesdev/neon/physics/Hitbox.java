package flamesdev.neon.physics;

import flamesdev.neon.utils.GeneralUtils;

public class Hitbox {
	private Vector2D center;
	private double width, height;

	public Hitbox(Vector2D center, double width, double height) {
		this.center = center;
		this.width = width;
		this.height = height;
	}

	public double getLowerXBound() {
		return center.x - width / 2;
	}

	public double getHigherXBound() {
		return center.x + width / 2;
	}

	public double getLowerYBound() {
		return center.y - height / 2;
	}

	public double getHigherYBound() {
		return center.y + height / 2;
	}

	public Vector2D getCenter() {
		return center;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	public void setCenter(Vector2D center) {
		this.center = center;
	}

	public void setLowerXBound(double bound) {
		center.x = bound + width / 2;
	}

	public void setHigherXBound(double bound) {
		center.x = bound - width / 2;
	}

	public void setLowerYBound(double bound) {
		center.y = bound + height / 2;
	}

	public void setHigherYBound(double bound) {
		center.y = bound - height / 2;
	}

	public void keepWithinBounds(double lowX, double highX, double lowY, double highY) {
		if (getLowerXBound() < lowX)
			setLowerXBound(lowX);
		if (getHigherXBound() > highX)
			setHigherXBound(highX);
		if (getLowerYBound() < lowY)
			setLowerYBound(lowY);
		if (getHigherYBound() > highY)
			setHigherYBound(highY);
	}

	public void preventIntersection(Hitbox other) {
		if (intersectsHitbox(other)) {
			double xDifference = center.x - other.center.x;
			double yDifference = center.y - other.center.y;
			if (Math.abs(xDifference) >= Math.abs(yDifference))
				if (xDifference < 0)
					setHigherXBound(other.getLowerXBound());
				else
					setLowerXBound(other.getHigherXBound());
			else if (yDifference < 0)
				setHigherYBound(other.getLowerYBound());
			else
				setLowerYBound(other.getHigherYBound());
		}
	}

	public boolean containsVector(Vector2D vector) {
		return GeneralUtils.withinRange(vector.x, getLowerXBound(), getHigherXBound())
				&& GeneralUtils.withinRange(vector.y, getLowerYBound(), getHigherYBound());
	}

	public boolean intersectsHitbox(Hitbox other) {
		return Math.abs(center.x - other.center.x) <= GeneralUtils.getAverage(new double[] { width, other.width })
				&& Math.abs(center.y - other.center.y) <= GeneralUtils
						.getAverage(new double[] { height, other.height });
	}
}