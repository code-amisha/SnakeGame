import java.util.Random;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int DELAY = 120;

    Timer timer;
    char direction = 'R';

    int[] x = new int[100];
    int[] y = new int[100];

    int bodyParts = 5;
    Random random;

    int appleX;
    int appleY;

    GamePanel() {

        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());

        for (int i = 0; i < bodyParts; i++) {
            x[i] = 100 - (i * UNIT_SIZE);
            y[i] = 100;
        }


        random = new Random();
        newApple();
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < bodyParts; i++) {

            if (i == 0) {
                g.setColor(Color.GREEN);
            } else {
                g.setColor(new Color(45, 180, 0));
            }

            g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
        }
        g.setColor(Color.RED);
        g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
    }

    public void move() {

        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direction) {

            case 'U':
                y[0] -= UNIT_SIZE;
                break;

            case 'D':
                y[0] += UNIT_SIZE;
                break;

            case 'L':
                x[0] -= UNIT_SIZE;
                break;

            case 'R':
                x[0] += UNIT_SIZE;
                break;
        }
    }public void newApple() {

        appleX = random.nextInt(SCREEN_WIDTH / UNIT_SIZE) * UNIT_SIZE;
        appleY = random.nextInt(SCREEN_HEIGHT / UNIT_SIZE) * UNIT_SIZE;

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            switch (e.getKeyCode()) {

                case KeyEvent.VK_LEFT:
                    if (direction != 'R')
                        direction = 'L';
                    break;

                case KeyEvent.VK_RIGHT:
                    if (direction != 'L')
                        direction = 'R';
                    break;

                case KeyEvent.VK_UP:
                    if (direction != 'D')
                        direction = 'U';
                    break;

                case KeyEvent.VK_DOWN:
                    if (direction != 'U')
                        direction = 'D';
                    break;
            }
        }
    }
}