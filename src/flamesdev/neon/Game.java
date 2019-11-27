package flamesdev.neon;

import java.awt.Color;
import java.awt.Graphics;

import flamesdev.neon.critical.IGame;
import flamesdev.neon.critical.NeonEngine;
import flamesdev.neon.input.InputEngine;
import flamesdev.neon.physics.Hitbox;
import flamesdev.neon.physics.Vector2D;
import flamesdev.neon.rendering.Rectangle;
import flamesdev.neon.rendering.RenderEngine;
import flamesdev.neon.utils.RandomUtils;

public class Game implements IGame {
	private Rectangle rect = new Rectangle(new Hitbox(new Vector2D(0.5, 0.5), 0.2, 0.2), Color.red, true);
	private Rectangle obstacle = new Rectangle(new Hitbox(new Vector2D(0.3, 0.3), 0.1, 0.1), Color.green, true);
	private Rectangle obstacle2 = new Rectangle(new Hitbox(new Vector2D(0.5, 0.5), 0.2, 0.2), Color.orange, true);
	private int ticks = 0;

	@Override
	public void tick() {
		if (ticks++ >= NeonEngine.getSettings().tickRate) {
			rect.color = new Color(RandomUtils.getInt(0, 255), RandomUtils.getInt(0, 255), RandomUtils.getInt(0, 255));
			ticks = 0;
		}
		rect.hitbox.setCenter(InputEngine.mousePosition);
		rect.hitbox.keepWithinBounds(0, 1, 0, 1);
		rect.hitbox.preventIntersection(obstacle.hitbox);
		rect.hitbox.preventIntersection(obstacle2.hitbox);
	}

	@Override
	public void render(Graphics graphics) {
		RenderEngine.drawRectangle(graphics, rect);
		RenderEngine.drawRectangle(graphics, obstacle);
		RenderEngine.drawRectangle(graphics, obstacle2);
	}
}