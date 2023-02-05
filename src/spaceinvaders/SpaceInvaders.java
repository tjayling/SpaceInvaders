package spaceinvaders;

import javax.swing.*;
import java.awt.*;

public class SpaceInvaders extends JFrame {
    private void initUI() {
        add(new Board());

        pack();

        setTitle("Space Invaders");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setResizable(false);
    }

    public SpaceInvaders() {
        initUI();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new SpaceInvaders();
            frame.setVisible(true);
        });
    }
}