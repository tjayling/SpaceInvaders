package spaceinvaders.drawers.movers.bullets;

import javax.swing.*;
import java.awt.*;

public class MediumBullet extends AlienBullet {

    public MediumBullet(int xPos, int yPos) {
        super(xPos, yPos);
        Image image1 = new ImageIcon("src/assets/bullets/enemy-bullet-2.png").getImage();
        setImage(image1);
    }
}