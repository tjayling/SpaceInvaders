package spaceinvaders.drawers;

import javax.swing.*;
import java.awt.*;

public class Heart extends Drawable{
    private final int xPos;
    private final int yPos;
    public Heart(int width, int height, int xPos, int yPos) {
        super(width, height);
        this.xPos = xPos;
        this.yPos = yPos;
        Image image1 = new ImageIcon("src/assets/heart-full.png").getImage();
        Image image2 = new ImageIcon("src/assets/heart-empty.png").getImage();
        setImage(image1);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(super.image, xPos - (IMAGE_WIDTH / 2), yPos - (IMAGE_HEIGHT / 2), IMAGE_WIDTH, IMAGE_HEIGHT, null);
    }
}
