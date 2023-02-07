package spaceinvaders.drawers.movers;

import spaceinvaders.Board;
import spaceinvaders.drawers.HealthBar;
import spaceinvaders.drawers.movers.aliens.Alien;
import spaceinvaders.drawers.movers.bullets.AlienBullet;
import spaceinvaders.drawers.movers.bullets.PlayerBullet;
import spaceinvaders.observers.HitObserver;
import spaceinvaders.observers.PlayerHitObserver;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

import static spaceinvaders.Constants.*;


public class Player extends Mover {
    private static final int WIDTH = 75;
    private static final int HEIGHT = 75;
    private static final int STARTING_X = SCREEN_WIDTH / 2;
    private static final int STARTING_Y = SCREEN_HEIGHT - (HEIGHT / 2);
    private static Player instance;
    private PlayerBullet playerBullet;
    private boolean movingLeft;
    private boolean movingRight;
    private boolean shooting;
    private int health = STARTING_HEALTH;
    private HealthBar healthBar;


    private Player() {
        super(STARTING_X, STARTING_Y, 75, 75);
        super.setImage(new ImageIcon("src/assets/ship.png").getImage());

        healthBar = HealthBar.getInstance();
        healthBar.reset();
        PlayerHitObserver.subscribe(healthBar);
    }

    public static Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }
        return instance;
    }

    public PlayerBullet getBullet() {
        return playerBullet;
    }

    public void reset() {
        instance = new Player();
    }

    public void checkCollisions(AlienBullet bullet) {
        float halfWidth = WIDTH / 2f;
        float halfHeight = HEIGHT / 2f;
        int bulletX = bullet.getxPos();
        int bulletY = bullet.getyPos();
        if ((bulletX <= xPos + halfWidth)
                && (bulletX >= xPos - halfWidth)
                && (bulletY <= yPos + halfHeight)
                && (bulletY >= yPos - halfHeight)) {
            health--;
            PlayerHitObserver.alert();
        }
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
        Board.getAliens().stream().map(Alien::getBullet).filter(Objects::nonNull).forEach(this::checkCollisions);
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
        healthBar.draw(g);
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

    public int getHealth() {
        return health;
    }
}