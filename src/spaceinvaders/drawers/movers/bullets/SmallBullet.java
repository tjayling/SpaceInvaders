package spaceinvaders.drawers.movers.bullets;

import javax.swing.*;
import java.awt.*;

public class SmallBullet extends AlienBullet {
    private final Image image1 = new ImageIcon("src/assets/bullets/enemy-bullet-1-1.png").getImage();
    private final Image image2 = new ImageIcon("src/assets/bullets/enemy-bullet-1-2.png").getImage();

    public SmallBullet(int xPos, int yPos) {
        super(xPos, yPos);
    }

    @Override
    public void setup() {
        super.setup();
        if (image != image1 && age % 30 < 15) {
            setImage(image1);
            return;
        }
        if (image != image2 && age % 30 >= 15) {
            setImage(image2);
        }
    }
}
