package spaceinvaders.observers;

import spaceinvaders.drawers.movers.Mover;
import spaceinvaders.drawers.movers.bullets.PlayerBullet;

import java.util.ArrayList;
import java.util.List;

public class HitObserver {
    private static final List<Mover> listeners = new ArrayList<>();

    public static void subscribe(Mover listener) {
        listeners.add(listener);
    }

    public static void unsubscribe(Mover listener) {
        listeners.remove(listener);
    }

    public static void alert() {
        for (Mover listener : listeners) {
            if (listener.getClass() == PlayerBullet.class) {
                ((PlayerBullet) listener).destroy();
            }
        }
    }
}
