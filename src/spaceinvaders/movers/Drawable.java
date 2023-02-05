package spaceinvaders.movers;

import java.awt.*;

public abstract class Drawable {
    protected int IMAGE_WIDTH;
    protected int IMAGE_HEIGHT;
    protected Image image;

    public Drawable(int width, int height) {
        IMAGE_WIDTH = width;
        IMAGE_HEIGHT = height;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public abstract void draw(Graphics g);
}
