package spaceinvaders.movers;

import spaceinvaders.Board;
import spaceinvaders.HitObserver;

import javax.swing.*;
import java.awt.*;


public class Player extends Mover {
    private static final int WIDTH = 75;
    private static final int HEIGHT = 75;
    private static final int STARTING_X = Board.WIDTH / 2;
    private static final int STARTING_Y = Board.HEIGHT - (HEIGHT / 2);
    private static final int SPEED = 25;
    private static Player player;
    private Bullet bullet;
    private boolean movingLeft;
    private boolean movingRight;
    private boolean shooting;

    private Player() {
        super(STARTING_X, STARTING_Y, 75, 75);
        super.setImage(new ImageIcon("src/assets/ship.png").getImage());
    }

    public static Player getInstance() {
        if (player == null) {
            player = new Player();
        }
        return player;
    }

    public Bullet getBullet() {
        return bullet;
    }

    public void reset() {
        player = new Player();
    }

    public void shoot() {
        if (shooting) {
            if (bullet == null) {
                bullet = new Bullet(xPos, STARTING_Y - HEIGHT / 2);
                HitObserver.subscribe(bullet);
            }
        }
    }

    @Override
    public void setup() {
        if (bullet != null) {
            bullet.setup();
            if (bullet.isDestroyable()) {
                HitObserver.unsubscribe(bullet);
                bullet = null;
            }
        }
        shoot();
    }

    @Override
    public void move() {
        if (movingLeft) {
            if (xPos - SPEED >= WIDTH / 2) {
                xPos -= SPEED;
            }
        }

        if (movingRight) {
            if (xPos + SPEED <= Board.WIDTH - (WIDTH / 2)) {
                {
                    xPos += SPEED;
                }
            }
        }
        if (bullet != null) {
            bullet.move();
        }
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        if (bullet != null) {
            bullet.draw(g);
        }
    }

    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    public void setShooting(boolean shooting) {
        this.shooting = shooting;
    }
}