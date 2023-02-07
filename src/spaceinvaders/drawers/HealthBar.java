package spaceinvaders.drawers;

import spaceinvaders.drawers.movers.Player;

import java.awt.*;

import static spaceinvaders.Constants.STARTING_HEALTH;

public class HealthBar extends Drawable {
    private static HealthBar instance;
    private final int X_POS = 100;
    private final int Y_POS = 50;
    private Heart[] hearts = new Heart[STARTING_HEALTH];

    private HealthBar() {
        super(165, 50);
        for (int i = 0; i < STARTING_HEALTH; i++) {
            hearts[i] = new Heart(((i * 50) + 5) + X_POS / 2, Y_POS);
        }
    }

    public static HealthBar getInstance() {
        if (instance == null) {
            instance = new HealthBar();
        }
        return instance;
    }

    public void reset() {
        instance = new HealthBar();
    }

    public void alertHit() {
        int playerHealth = Player.getInstance().getHealth();


        for (int i = hearts.length - 1; i > playerHealth; i--) {
            hearts[i].empty();
        }
    }

    @Override
    public void draw(Graphics g) {
        for (Heart heart : hearts) {
            heart.draw(g);
        }
    }

}
