package spaceinvaders.factories;

import spaceinvaders.drawers.movers.aliens.Alien;
import spaceinvaders.drawers.movers.aliens.LargeAlien;
import spaceinvaders.drawers.movers.aliens.MediumAlien;
import spaceinvaders.drawers.movers.aliens.SmallAlien;

import java.util.Objects;

public class AlienFactory {
    private AlienFactory() {
        throw new UnsupportedOperationException("Alien factory cannot be instantiated and it's methods should be called statically");
    }

    public static Alien createAlien(String type, int xPos, int yPos) {
        if (Objects.equals(type, "small")) {
            return new SmallAlien(xPos, yPos);
        }
        if (Objects.equals(type, "medium")) {
            return new MediumAlien(xPos, yPos);
        }
        if (Objects.equals(type, "large")) {
            return new LargeAlien(xPos, yPos);
        }
        return null;
    }
}
