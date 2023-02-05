package spaceinvaders;

import spaceinvaders.movers.aliens.Alien;
import spaceinvaders.movers.aliens.LargeAlien;
import spaceinvaders.movers.aliens.MediumAlien;
import spaceinvaders.movers.aliens.SmallAlien;

import java.util.Objects;

public class AlienFactory {
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
