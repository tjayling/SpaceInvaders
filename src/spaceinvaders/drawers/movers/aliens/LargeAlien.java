package spaceinvaders.drawers.movers.aliens;

import javax.swing.*;
import java.awt.*;

public class LargeAlien extends Alien {

    public LargeAlien(int startingX, int startingY) {
        super(startingX, startingY);
        Image image1 = new ImageIcon("src/assets/aliens/alien-large-1.png").getImage();
        Image image2 = new ImageIcon("src/assets/aliens/alien-large-2.png").getImage();
        setImages(image1, image2);
    }
}
