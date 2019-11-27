package flamesdev.neon;

import flamesdev.neon.critical.GameSettings;
import flamesdev.neon.critical.NeonEngine;

public class Program {
	public static void main(String[] args) {
		NeonEngine.init(new Game(), new GameSettings("Test", false, false, 1280, 720, 60));
	}
}