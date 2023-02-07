package spaceinvaders.drawers.movers.aliens;

import spaceinvaders.factories.BulletFactory;
import spaceinvaders.observers.HitObserver;
import spaceinvaders.drawers.movers.Explosion;
import spaceinvaders.drawers.movers.Mover;
import spaceinvaders.drawers.movers.bullets.AlienBullet;
import spaceinvaders.drawers.movers.bullets.PlayerBullet;

import java.awt.*;
import java.util.Random;

import static spaceinvaders.drawers.movers.aliens.Direction.LEFT;
import static spaceinvaders.drawers.movers.aliens.Direction.RIGHT;

public abstract class Alien extends Mover {
    private final int WIDTH = 50;
    private final int HEIGHT = 50;
    protected AlienBullet bullet;
    private Explosion explosion;
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

    public void checkCollision(PlayerBullet playerBullet) {
        if (visible) {
            final int pX = playerBullet.getxPos();
            final int pY = playerBullet.getyPos();
            if (pX <= (xPos + (WIDTH / 2)) && pX >= (xPos - (WIDTH / 2)) && pY <= (yPos + (HEIGHT / 2)) && pY >= (yPos - (HEIGHT / 2))) {
                visible = false;
                explosion = new Explosion(xPos, yPos);
                HitObserver.alert();
            }
        }
    }

    public void shoot() {
        if (bullet == null) {
            bullet = BulletFactory.createBullet(this);
        }
    }

    @Override
    public void setup() {
        age++;

        if (age > 50) {
            if (new Random().nextDouble() < 0.001) {
                shoot();
            }
        }

        if (explosion != null) {
            explosion.setup();
            if (explosion.isDestroyable()) {
                explosion = null;
                setDestroyable(true);
            }
        }

        if (image != image1 && age % 30 < 15) {
            setImage(image1);
        }
        if (image != image2 && age % 30 >= 15) {
            setImage(image2);
        }

        if (bullet != null) {
            bullet.setup();
            if (bullet.isDestroyable()) {
                bullet = null;
            }
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
        if (bullet != null) {
            bullet.move();
        }
    }

    @Override
    public void draw(Graphics g) {
        if (bullet != null) {
            bullet.draw(g);
        }
        if (visible) {
            super.draw(g);
            return;
        }
        if (explosion != null) {
            explosion.draw(g);
        }
    }

    public AlienBullet getBullet() {
        return bullet;
    }
}
