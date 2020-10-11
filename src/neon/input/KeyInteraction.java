package neon.input;

import java.awt.event.KeyEvent;

/** A class that stores data about a key interaction. */
public class KeyInteraction {
  public final char character;
  public final int keyCode;

  public KeyInteraction(KeyEvent event) {
    this.character = event.getKeyChar();
    this.keyCode = event.getKeyCode();
  }
}
