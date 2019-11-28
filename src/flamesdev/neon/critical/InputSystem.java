package flamesdev.neon.critical;

import java.util.ArrayList;
import java.util.List;

import flamesdev.neon.physics.Vector2D;

/**
 * A class used to get user input.
 */
public class InputSystem {
	protected static Vector2D mousePosition;

	protected static MouseInteraction[] mousePresses, mouseReleases;
	protected static boolean leftMBHeld, middleMBHeld, rightMBHeld, otherMBHeld;

	protected static List<MouseInteraction> mousePressQueue = new ArrayList<>();
	protected static List<MouseInteraction> mouseReleaseQueue = new ArrayList<>();

	protected static void update() {
		mousePresses = mousePressQueue.toArray(MouseInteraction[]::new);
		mousePressQueue.clear();
		mouseReleases = mouseReleaseQueue.toArray(MouseInteraction[]::new);
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

	public static boolean leftMouseButtonHeld() {
		return leftMBHeld;
	}

	public static boolean middleMouseButtonHeld() {
		return middleMBHeld;
	}

	public static boolean rightMouseButtonHeld() {
		return rightMBHeld;
	}

	public static boolean otherMouseButtonHeld() {
		return otherMBHeld;
	}
}