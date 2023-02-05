package spaceinvaders.movers.aliens;

import spaceinvaders.HitObserver;
import spaceinvaders.movers.Bullet;
import spaceinvaders.movers.Explosion;
import spaceinvaders.movers.Mover;

import java.awt.*;

import static spaceinvaders.movers.aliens.Direction.LEFT;
import static spaceinvaders.movers.aliens.Direction.RIGHT;

public abstract class Alien extends Mover {
    private final int WIDTH = 50;
    private final int HEIGHT = 50;
    private Explosion explosion;
    private boolean destroyable = false;
    private boolean visible = true;
    private int age = 0;
    private Image image1;
    private Image image2;
    private Direction direction = LEFT;

    public Alien(int startingX, int startingY) {
        super(startingX, startingY, 50, 50);
    }

    public void setImages(Image image1, Image image2) {
        this.image1 = image1;
        this.image2 = image2;
    }


    public void checkCollision(Bullet playerBullet) {
        if (visible) {
            final int pX = playerBullet.getxPos();
            final int pY = playerBullet.getyPos();
            if (pX < (xPos + (WIDTH / 2)) && pX > (xPos - (WIDTH / 2)) && pY < (yPos + (HEIGHT / 2)) && pY > (yPos - (HEIGHT / 2))) {
                visible = false;
                explosion = new Explosion(xPos, yPos);
                HitObserver.alert();
            }
        }
    }

    @Override
    public void setup() {
        age += 1;
        if (explosion != null) {
            explosion.setup();
            if (explosion.isDestroyable()) {
                explosion = null;
                destroyable = true;
            }
        }
        if (age % 30 == 15) {
            setImage(image2);
            return;
        }
        if (age % 30 == 0) {
            setImage(image1);
        }
    }

    @Override
    public void move() {
        final int SPEED = 1;
        xPos = direction == LEFT ? xPos + SPEED : xPos - SPEED;
        if (direction == RIGHT && age % 200 < 100) {
            direction = LEFT;
        }
        if (direction == LEFT && age % 200 >= 100) {
            direction = RIGHT;
        }
        if (age > 0 && age % 100 == 0) {
            yPos += 10;
        }
    }

    @Override
    public void draw(Graphics g) {
        if (visible) {
            super.draw(g);
            return;
        }
        if (explosion != null) {
            explosion.draw(g);
        }
    }

    @Override
    public boolean isDestroyable() {
        return destroyable;
    }
}
