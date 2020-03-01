package flamesdev.neon.example;

import flamesdev.neon.critical.IGame;
import flamesdev.neon.critical.InputSystem;
import flamesdev.neon.physics.Hitbox;
import flamesdev.neon.physics.Vector2D;
import flamesdev.neon.rendering.ImageObject;
import flamesdev.neon.rendering.Rectangle;
import flamesdev.neon.rendering.RenderSystem;
import flamesdev.neon.rendering.Units;
import flamesdev.neon.utils.GeneralUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Game implements IGame {
    private BufferedImage buff = GeneralUtils.loadImage("res/neon-logo.png");
    private Rectangle rect;
    private Rectangle obstacle = new Rectangle(new Hitbox(new Vector2D(0.5, 0.7), 0.2, 0.2), Color.green, true);
    private Rectangle obstacle2 = new Rectangle(new Hitbox(new Vector2D(0.5, 0.3), 0.15, 0.15), Color.orange, true);

    @Override
    public void init() {
        rect = new Rectangle(new Hitbox(new Vector2D(0.5, 0.5), 0.1, Units.toHeight(0.1)), Color.red, true);
    }

    @Override
    public void tick() {
        rect.setColor(InputSystem.leftMouseButtonHeld() ? Color.red : Color.blue);
        Hitbox hitbox = rect.getHitbox();
        hitbox.setCenter(InputSystem.getMousePosition());
        hitbox.keepWithinBounds(0, 1, 0, 1);
        hitbox.preventIntersection(obstacle.getHitbox());
        hitbox.preventIntersection(obstacle2.getHitbox());
    }

    @Override
    public void render(Graphics graphics) {
        RenderSystem.drawImage(graphics, new ImageObject(new Hitbox(new Vector2D(0.15, 0.85), 0.3, 0.3), buff));
        RenderSystem.drawRectangle(graphics, rect);
        RenderSystem.drawRectangle(graphics, obstacle);
        RenderSystem.drawRectangle(graphics, obstacle2);
    }
}