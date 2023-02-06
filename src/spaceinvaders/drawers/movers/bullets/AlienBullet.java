package spaceinvaders.drawers.movers.bullets;

import spaceinvaders.drawers.movers.Mover;

import static spaceinvaders.Constants.BULLET_SPEED;
import static spaceinvaders.Constants.SCREEN_HEIGHT;

public abstract class AlienBullet extends Mover {
    protected int age;

    public AlienBullet(int xPos, int yPos) {
        super(xPos, yPos, 75, 50);
    }

    @Override
    public void setup() {
        age++;
        if (yPos > SCREEN_HEIGHT) {
            destroyable = true;
        }
    }

    @Override
    public void move() {
        yPos += BULLET_SPEED;
    }
}
