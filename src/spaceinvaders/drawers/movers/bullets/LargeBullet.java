package spaceinvaders.drawers.movers.bullets;

import javax.swing.*;
import java.awt.*;

public class LargeBullet extends AlienBullet {
    public LargeBullet(int xPos, int yPos) {
        super(xPos, yPos);
        Image image1 = new ImageIcon("src/assets/bullets/bullet.png").getImage();
        setImage(image1);
    }
}
