package spaceinvaders.movers;

import java.awt.*;

public abstract class Mover extends Drawable {
    protected int xPos;
    protected int yPos;
    protected boolean destroyable = false;

    public Mover(int xPos, int yPos, int imageWidth, int imageHeight) {
        super(imageWidth, imageHeight);
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public boolean isDestroyable() {
        return destroyable;
    }

    public void setDestroyable(boolean destroyable) {
        this.destroyable = destroyable;
    }

    public abstract void setup();

    public abstract void move();

    @Override
    public void draw(Graphics g) {
        g.drawImage(super.image, xPos - (IMAGE_WIDTH / 2), yPos - (IMAGE_HEIGHT / 2), IMAGE_WIDTH, IMAGE_HEIGHT, null);
    }
}
