package flamesdev.neon.critical;

import flamesdev.neon.physics.Vector2D;

import java.util.ArrayList;
import java.util.List;

/**
 * A class used to get user input.
 */
public class InputSystem {
    protected static Vector2D mousePosition;

    protected static MouseInteraction[] mousePresses;
    protected static MouseInteraction[] mouseReleases;
    protected static boolean leftMBHeld;
    protected static boolean middleMBHeld;
    protected static boolean rightMBHeld;
    protected static boolean otherMBHeld;

    protected static List<MouseInteraction> mousePressQueue = new ArrayList<>();
    protected static List<MouseInteraction> mouseReleaseQueue = new ArrayList<>();

    protected static void update() {
        mousePresses = mousePressQueue.toArray(new MouseInteraction[0]);
        mousePressQueue.clear();
        mouseReleases = mouseReleaseQueue.toArray(new MouseInteraction[0]);
        mouseReleaseQueue.clear();
    }

    /**
     * The value is updated before each tick.
     *
     * @return the mouse position
     */
    public static Vector2D getMousePosition() {
        return mousePosition;
    }

    /**
     * The value is cleared after each render.
     *
     * @return recent mouse presses
     */
    public static MouseInteraction[] getMousePresses() {
        return mousePresses;
    }

    /**
     * The value is cleared after each render.
     *
     * @return recent mouse releases
     */
    public static MouseInteraction[] getMouseReleases() {
        return mouseReleases;
    }

    /**
     * @return whether the left mouse button is currently being held
     */
    public static boolean leftMouseButtonHeld() {
        return leftMBHeld;
    }

    /**
     * @return whether the middle mouse button is currently being held
     */
    public static boolean middleMouseButtonHeld() {
        return middleMBHeld;
    }

    /**
     * @return whether the right mouse button is currently being held
     */
    public static boolean rightMouseButtonHeld() {
        return rightMBHeld;
    }

    /**
     * @return whether another mouse button is currently being held
     */
    public static boolean otherMouseButtonHeld() {
        return otherMBHeld;
    }
}