package spaceinvaders.drawers.movers.bullets;

import spaceinvaders.drawers.movers.Mover;

import javax.swing.*;

import static spaceinvaders.Constants.BULLET_SPEED;

public class PlayerBullet extends Mover {
    private static final int WIDTH = 5;
    private static final int HEIGHT = 50;

    public PlayerBullet(int xPos, int yPos) {
        super(xPos, yPos, 75, 50);
        setImage(new ImageIcon("src/assets/bullets/bullet.png").getImage());
    }

    public void destroy() {
        destroyable = true;
    }

    @Override
    public void setup() {
        if (yPos <= 0) {
            setDestroyable(true);
        }
    }

    @Override
    public void move() {
        yPos -= BULLET_SPEED;
    }
}
