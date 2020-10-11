package neon.critical;

import java.awt.Graphics;

/**
 * An interface containing a tick and render method which are necessary to make a functioning game.
 */
public interface IGame {
  /** A method called when the game starts. */
  void init();

  /** A method called by the NeonEngine every tick at the rate specified in the game settings. */
  void tick();

  /**
   * A method used to render graphics that is called directly after each tick.
   *
   * @param graphics an object used to draw graphics
   */
  void render(Graphics graphics);

  /** A method called when the game is closed. */
  void onExit();
}
