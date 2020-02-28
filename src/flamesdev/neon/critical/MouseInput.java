package flamesdev.neon.critical;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * A class that automatically updates the information in the InputSystem class.
 */
public class MouseInput extends MouseAdapter {
    /**
     * WARNING: Do not call this method. It is only to be called by core library classes.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        MouseInteraction mi = new MouseInteraction(e);
        setMouseButtonHeld(mi.mouseButtonType, true);
        InputSystem.mousePressQueue.add(mi);
    }

    /**
     * WARNING: Do not call this method. It is only to be called by core library classes.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        MouseInteraction mi = new MouseInteraction(e);
        setMouseButtonHeld(mi.mouseButtonType, false);
        InputSystem.mouseReleaseQueue.add(mi);
    }

    private void setMouseButtonHeld(MouseButtonType type, boolean value) {
        switch (type) {
            case LEFT:
                InputSystem.leftMBHeld = value;
                break;
            case MIDDLE:
                InputSystem.middleMBHeld = value;
                break;
            case RIGHT:
                InputSystem.rightMBHeld = value;
                break;
            case OTHER:
                InputSystem.otherMBHeld = value;
                break;
        }
    }
}