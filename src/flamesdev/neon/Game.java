package flamesdev.neon;

import java.awt.Color;
import java.awt.Graphics;

import flamesdev.neon.critical.IGame;

public class Game implements IGame {
	@Override
	public void tick() {
		System.out.println("Tick!");
	}

	@Override
	public void render(Graphics graphics) {
		graphics.setColor(Color.blue);
		graphics.fillRect(100, 100, 120, 120);
	}
}