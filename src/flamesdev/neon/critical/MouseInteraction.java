package flamesdev.neon.critical;

import flamesdev.neon.physics.Vector2D;

import javax.swing.*;
import java.awt.event.MouseEvent;

/**
 * A class that stores data about a mouse interaction.
 */
public class MouseInteraction {
    public final MouseButtonType mouseButtonType;
    public final Vector2D position;

    public MouseInteraction(MouseEvent event) {
        this.mouseButtonType = getMouseButtonType(event);
        this.position = new Vector2D(event.getPoint());
    }

    private MouseButtonType getMouseButtonType(MouseEvent event) {
        if (SwingUtilities.isLeftMouseButton(event))
            return MouseButtonType.LEFT;
        if (SwingUtilities.isMiddleMouseButton(event))
            return MouseButtonType.MIDDLE;
        if (SwingUtilities.isRightMouseButton(event))
            return MouseButtonType.RIGHT;
        return MouseButtonType.OTHER;
    }
}