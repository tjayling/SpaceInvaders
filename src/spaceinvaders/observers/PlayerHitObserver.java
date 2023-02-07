package spaceinvaders.observers;

import spaceinvaders.drawers.Drawable;
import spaceinvaders.drawers.HealthBar;

import java.util.ArrayList;
import java.util.List;

public class PlayerHitObserver {
    private static final List<Drawable> listeners = new ArrayList<>();

    private PlayerHitObserver() {
        throw new UnsupportedOperationException("PlayerHitObserver cannot be instantiated");
    }

    public static void subscribe(Drawable drawable) {
        listeners.add(drawable);
    }

    public static void unsubscribe(Drawable drawable) {
        listeners.remove(drawable);
    }

    public static void alert() {
        for (Drawable listener : listeners) {
            if (listener.getClass() == HealthBar.class) {
                ((HealthBar) listener).alertHit();
            }
        }
    }
}
