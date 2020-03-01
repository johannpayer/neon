package flamesdev.neon.physics;

import flamesdev.neon.critical.GameSettings;

/**
 * A class used to simulate the physics and interactions of circular hitboxes.
 */
public class CircularHitbox implements Hitbox {
    private static GameSettings settings;
    private Vector2D center;
    private double radius;
    private double width;
    private double height;

    public CircularHitbox(Vector2D center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    /**
     * WARNING: Do not call this method. It is only to be called by core library classes.<br>
     * Sets the settings of the render engine. Once a value is defined, it cannot be
     * changed.
     *
     * @param settings the game settings
     */
    public static void setSettings(GameSettings settings) {
        if (CircularHitbox.settings == null)
            CircularHitbox.settings = settings;
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

    public double getHeight() {
        return height;
    }

    /**
     * Sets the hitbox's radius in width units.
     *
     * @param radius the new radius
     */
    public void setRadiusWidth(double radius) {
        this.radius = radius;
        this.width = radius * 2;
        this.height = width * settings.getWidth() / settings.getHeight();
    }

    /**
     * Sets the hitbox's radius in height units.
     *
     * @param radius the new radius
     */
    public void setRadiusHeight(double radius) {
        this.radius = radius * settings.getHeight() / settings.getWidth();
        this.width = this.radius * 2;
        this.height = radius * 2;
    }

    public boolean containsVector(Vector2D vector) {
        Vector2D difference = center.safeSubtract(vector);
        return Math.sqrt(Math.pow(difference.getX(), 2) + Math.pow(difference.getY() * settings.getHeight() /
                settings.getWidth(), 2)) <= radius;
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

    /**
     * WARNING: This method is unimplemented.
     *
     * @param other the other hitbox
     * @throws UnsupportedOperationException always
     */
    public void preventIntersection(Hitbox other) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("This method is not currently implemented.");
    }

    /**
     * WARNING: This method is unimplemented.
     *
     * @param other the other hitbox
     * @throws UnsupportedOperationException always
     */
    public boolean intersectsHitbox(Hitbox other) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("This method is not currently implemented.");
    }
}
