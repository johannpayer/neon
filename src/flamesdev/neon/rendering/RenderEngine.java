package flamesdev.neon.rendering;

import java.awt.Graphics;

import flamesdev.neon.critical.GameSettings;

public class RenderEngine {
	private static GameSettings settings;

	public static void setSettings(GameSettings settings) {
		RenderEngine.settings = settings;
	}

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

	public static double reverseY(double y) {
		return settings.height - y;
	}
}