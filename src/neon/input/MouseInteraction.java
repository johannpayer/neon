package neon.input;

import javax.swing.SwingUtilities;
import neon.physics.Vector2D;
import java.awt.event.MouseEvent;

/**
 * A class that stores data about a mouse interaction.
 */
public class MouseInteraction {
    public final MouseButtonType mouseButtonType;
    public final Vector2D position;

    public MouseInteraction(MouseEvent event) {
        mouseButtonType = getMouseButtonType(event);
        position = new Vector2D(event.getPoint());
        position.convertCoordinateSystem();
    }

    private static MouseButtonType getMouseButtonType(MouseEvent event) {
        if (SwingUtilities.isLeftMouseButton(event)) {
            return MouseButtonType.LEFT;
        }
        if (SwingUtilities.isMiddleMouseButton(event)) {
            return MouseButtonType.MIDDLE;
        }
        if (SwingUtilities.isRightMouseButton(event)) {
            return MouseButtonType.RIGHT;
        }
        return MouseButtonType.OTHER;
    }
}