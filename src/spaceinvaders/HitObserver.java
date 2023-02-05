package spaceinvaders;

import spaceinvaders.movers.Bullet;
import spaceinvaders.movers.Mover;
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
            if (listener.getClass() == Bullet.class ) {
                ((Bullet) listener).destroy();
            }
        }
    }
}
