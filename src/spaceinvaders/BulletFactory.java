package spaceinvaders;

import spaceinvaders.drawers.movers.aliens.Alien;
import spaceinvaders.drawers.movers.aliens.LargeAlien;
import spaceinvaders.drawers.movers.aliens.MediumAlien;
import spaceinvaders.drawers.movers.aliens.SmallAlien;
import spaceinvaders.drawers.movers.bullets.AlienBullet;
import spaceinvaders.drawers.movers.bullets.LargeBullet;
import spaceinvaders.drawers.movers.bullets.MediumBullet;
import spaceinvaders.drawers.movers.bullets.SmallBullet;

public class BulletFactory {
    private BulletFactory() {
        throw new UnsupportedOperationException("PlayerBullet factory cannot be instantiated and it's methods should be called statically");
    }

    public static AlienBullet createBullet(Alien alien) {
        if (alien.getClass() == SmallAlien.class) {
            return new SmallBullet(alien.getxPos(), alien.getyPos());
        }
        if (alien.getClass() == MediumAlien.class) {
            return new MediumBullet(alien.getxPos(), alien.getyPos());
        }
        if (alien.getClass() == LargeAlien.class) {
            return new LargeBullet(alien.getxPos(), alien.getyPos());
        }
        return null;
    }
}
