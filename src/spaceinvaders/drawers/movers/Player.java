package spaceinvaders.drawers.movers;

import spaceinvaders.HitObserver;
import spaceinvaders.drawers.movers.bullets.PlayerBullet;

import javax.swing.*;
import java.awt.*;

import static spaceinvaders.Constants.*;


public class Player extends Mover {
    private static final int WIDTH = 75;
    private static final int HEIGHT = 75;
    private static final int STARTING_X = SCREEN_WIDTH / 2;
    private static final int STARTING_Y = SCREEN_HEIGHT - (HEIGHT / 2);
    private static Player player;
    private PlayerBullet playerBullet;
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

    public PlayerBullet getBullet() {
        return playerBullet;
    }

    public void reset() {
        player = new Player();
    }

    public void shoot() {
        if (shooting) {
            if (playerBullet == null) {
                playerBullet = new PlayerBullet(xPos, STARTING_Y - HEIGHT / 2);
                HitObserver.subscribe(playerBullet);
            }
        }
    }

    @Override
    public void setup() {
        if (playerBullet != null) {
            playerBullet.setup();
            if (playerBullet.isDestroyable()) {
                HitObserver.unsubscribe(playerBullet);
                playerBullet = null;
            }
        }
        shoot();
    }

    @Override
    public void move() {
        if (movingLeft) {
            if (xPos - BULLET_SPEED >= WIDTH / 2) {
                xPos -= PLAYER_SPEED;
            }
        }

        if (movingRight) {
            if (xPos + BULLET_SPEED <= SCREEN_WIDTH - (WIDTH / 2)) {
                xPos += PLAYER_SPEED;
            }
        }
        if (playerBullet != null) {
            playerBullet.move();
        }
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        if (playerBullet != null) {
            playerBullet.draw(g);
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