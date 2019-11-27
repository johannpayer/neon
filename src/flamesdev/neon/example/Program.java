package flamesdev.neon.example;

import flamesdev.neon.critical.GameSettings;
import flamesdev.neon.critical.NeonEngine;

public class Program {
	public static void main(String[] args) {
		NeonEngine.init(new Game(), new GameSettings("Example Game", false, false, true, 1280, 720, 60, 2));
	}
}