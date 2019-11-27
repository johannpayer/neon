package flamesdev.neon.rendering;

import java.awt.Graphics;

import flamesdev.neon.critical.GameSettings;

public class RenderEngine {
	private static GameSettings settings;

	/**
	 * Sets the settings of the render engine. Once a value is defined, it cannot be
	 * changed.
	 * 
	 * @param settings the game settings
	 */
	public static void setSettings(GameSettings settings) {
		if (RenderEngine.settings == null)
			RenderEngine.settings = settings;
	}

	/**
	 * Draws a rectangle.
	 * 
	 * @param graphics  the graphics object used to draw the game's graphics
	 * @param rectangle the rectangle to be drawn
	 */
	public static void drawRectangle(Graphics graphics, Rectangle rectangle) {
		if (rectangle.color != null)
			graphics.setColor(rectangle.color);

		int[] parameters = new int[] { (int) Math.round(rectangle.hitbox.getLowerXBound() * settings.width),
				(int) Math.round(reverseY(rectangle.hitbox.getHigherYBound() * settings.height)),
				(int) Math.round(rectangle.hitbox.getWidth() * settings.width),
				(int) Math.round(rectangle.hitbox.getHeight() * settings.height) };
		if (rectangle.fill)
			graphics.fillRect(parameters[0], parameters[1], parameters[2], parameters[3]);
		else
			graphics.drawRect(parameters[0], parameters[1], parameters[2], parameters[3]);
	}

	private static double reverseY(double y) {
		return settings.height - y;
	}
}