package flamesdev.neon.rendering;

import java.awt.Color;

import flamesdev.neon.physics.Hitbox;

public class Rectangle {
	public Hitbox hitbox;
	public Color color;
	public boolean fill;

	public Rectangle(Hitbox hitbox, Color color, boolean fill) {
		this.hitbox = hitbox;
		this.color = color;
		this.fill = fill;
	}
}