package flamesdev.neon.critical;

import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import flamesdev.neon.physics.Vector2D;

/**
 * A class that stores data about a mouse interaction.
 */
public class MouseInteraction {
	public enum MouseButtonType {
		LEFT, MIDDLE, RIGHT, OTHER
	}

	public final MouseButtonType mouseButtonType;
	public final Vector2D position;

	public MouseInteraction(MouseEvent e) {
		this.mouseButtonType = getMouseButtonType(e);
		this.position = new Vector2D(e.getPoint());
	}

	private MouseButtonType getMouseButtonType(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e))
			return MouseButtonType.LEFT;
		if (SwingUtilities.isMiddleMouseButton(e))
			return MouseButtonType.MIDDLE;
		if (SwingUtilities.isRightMouseButton(e))
			return MouseButtonType.RIGHT;
		return MouseButtonType.OTHER;
	}
}