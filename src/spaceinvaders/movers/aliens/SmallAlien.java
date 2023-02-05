package spaceinvaders.movers.aliens;

import javax.swing.*;
import java.awt.*;

public class SmallAlien extends Alien{

    public SmallAlien(int startingX, int startingY) {
        super(startingX, startingY);
        Image image1 = new ImageIcon("src/assets/aliens/alien-small-1.png").getImage();
        Image image2 = new ImageIcon("src/assets/aliens/alien-small-2.png").getImage();
        setImages(image1, image2);
    }
}
