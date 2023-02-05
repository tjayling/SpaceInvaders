package spaceinvaders.movers;

import javax.swing.*;

public class Bullet extends Mover {
    private static final int WIDTH = 5;
    private static final int HEIGHT = 50;
    private static final int SPEED = 30;

    public Bullet(int xPos, int yPos) {
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
        yPos -= SPEED;
    }
}
