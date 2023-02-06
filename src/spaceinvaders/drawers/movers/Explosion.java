package spaceinvaders.drawers.movers;

import spaceinvaders.drawers.Drawable;

import javax.swing.*;
import java.awt.*;

public class Explosion extends Drawable {
    private final int xPos;
    private final int yPos;

    private boolean destroyable = false;
    private int age = 0;

    public Explosion(int xPos, int yPos) {
        super(75, 50);
        this.xPos = xPos;
        this.yPos = yPos;
        Image image1 = new ImageIcon("src/assets/explosion-1.png").getImage();
        setImage(image1);
    }

    public void setup() {
        Image image2 = new ImageIcon("src/assets/explosion-2.png").getImage();
        age++;
        if (age == 5) {
            setImage(image2);
        }
        if (age >= 10) {
            destroyable = true;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(super.image, xPos - (IMAGE_WIDTH / 2), yPos - (IMAGE_HEIGHT / 2), IMAGE_WIDTH, IMAGE_HEIGHT, null);
    }

    public boolean isDestroyable() {
        return destroyable;
    }
}
