package spaceinvaders.drawers;

import javax.swing.*;
import java.awt.*;

public class Heart extends Drawable{
    private final int xPos;
    private final int yPos;
    public Heart(int xPos, int yPos) {
        super(50, 50);
        this.xPos = xPos;
        this.yPos = yPos;
        Image image1 = new ImageIcon("src/assets/heart-full.png").getImage();
        setImage(image1);
    }

    public void empty() {
        Image image2 = new ImageIcon("src/assets/heart-empty.png").getImage();
        this.setImage(image2);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(super.image, xPos - (IMAGE_WIDTH / 2), yPos - (IMAGE_HEIGHT / 2), IMAGE_WIDTH, IMAGE_HEIGHT, null);
    }
}
