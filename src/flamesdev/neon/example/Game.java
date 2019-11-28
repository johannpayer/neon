package flamesdev.neon.example;

import java.awt.Color;
import java.awt.Graphics;

import flamesdev.neon.critical.IGame;
import flamesdev.neon.critical.InputSystem;
import flamesdev.neon.physics.Hitbox;
import flamesdev.neon.physics.Vector2D;
import flamesdev.neon.rendering.Rectangle;
import flamesdev.neon.rendering.RenderSystem;
import flamesdev.neon.rendering.Units;

public class Game implements IGame {
	private Rectangle rect;
	private Rectangle obstacle = new Rectangle(new Hitbox(new Vector2D(0.5, 0.7), 0.2, 0.2), Color.green, true);
	private Rectangle obstacle2 = new Rectangle(new Hitbox(new Vector2D(0.5, 0.3), 0.15, 0.15), Color.orange, true);

	@Override
	public void init() {
		rect = new Rectangle(new Hitbox(new Vector2D(0.5, 0.5), 0.1, Units.toHeight(0.1)), Color.red, true);
	}

	@Override
	public void tick() {
		rect.color = InputSystem.leftMouseButtonHeld() ? Color.red : Color.blue;
		rect.hitbox.setCenter(InputSystem.getMousePosition());
		rect.hitbox.keepWithinBounds(0, 1, 0, 1);
		rect.hitbox.preventIntersection(obstacle.hitbox);
		rect.hitbox.preventIntersection(obstacle2.hitbox);
	}

	@Override
	public void render(Graphics graphics) {
		RenderSystem.drawRectangle(graphics, rect);
		RenderSystem.drawRectangle(graphics, obstacle);
		RenderSystem.drawRectangle(graphics, obstacle2);
	}
}