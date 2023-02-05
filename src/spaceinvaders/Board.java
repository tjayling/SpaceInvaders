package spaceinvaders;

import spaceinvaders.movers.Bullet;
import spaceinvaders.movers.Mover;
import spaceinvaders.movers.Player;
import spaceinvaders.movers.aliens.Alien;
import spaceinvaders.movers.aliens.MediumAlien;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Board extends JPanel implements ActionListener {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 600;
    private final Timer timer = new Timer(30, this);
    private java.util.List<Mover> movers;
    private boolean gameRunning = false;
    private Player player;
    private final List<Alien> enemies = new ArrayList<>();

    public Board() {
        initBoard();
    }

    public void initBoard() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setVisible(true);
        setFocusable(true);
        setBackground(Color.black);

        addKeyListener(new Adapter());

        timer.start();
    }

    public void startGame() {
        movers = new ArrayList<>();

        player = Player.getInstance();
        player.reset();
        movers.add(player);

        // load level 1
        try {
            File file = new File("src/levels/level1.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                enemies.add(AlienFactory.createAlien(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2])));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("can't load level 1");
        }

        movers.addAll(enemies);

        gameRunning = true;
    }

    public void checkCollisions() {
        final Bullet playerBullet = player.getBullet();

        if (playerBullet != null) {
            for (Alien alien : enemies) {
                alien.checkCollision(playerBullet);
            }
        }
    }

    public void setup() {
        if (gameRunning) {
            List<Mover> toBeDestroyed = new ArrayList<>();
            for (Mover mover : movers) {
                if (mover != null) {
                    mover.setup();
                    if (mover.getClass() == MediumAlien.class) {
                        if (mover.isDestroyable()) {
                            toBeDestroyed.add(mover);
                        }
                    }
                }
            }
            movers.removeAll(toBeDestroyed);
            checkCollisions();
        }
    }

    public void move() {
        if (gameRunning) {
            for (Mover mover : movers) {
                if (mover != null) {
                    mover.move();
                }
            }
        }
    }

    public void draw(Graphics g) {
        if (gameRunning) {
            for (Mover mover : movers) {
                if (mover != null) {
                    mover.draw(g);
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setup();
        move();
        repaint();
    }

    private class Adapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            final int key = e.getKeyCode();
            if (gameRunning) {
                setPlayerMovement(key, true);
            }
            if (key == KeyEvent.VK_SPACE) if (!gameRunning) {
                startGame();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            final int key = e.getKeyCode();
            if (gameRunning) {
                setPlayerMovement(key, false);
            }
        }

        private void setPlayerMovement(int key, boolean value) {
            if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
                player.setShooting(value);
            }
            if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
                player.setMovingLeft(value);
            }
            if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
                player.setMovingRight(value);
            }
            if (key == KeyEvent.VK_ESCAPE) {
                gameRunning = false;
            }
        }
    }
}