import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BlockyWorld extends JPanel implements KeyListener {
    private int playerX, playerY;
    private int[][] map;
    private ArrayList<Enemy> enemies;

    public BlockyWorld() {
        JFrame frame = new JFrame("BlockyWorld");
        frame.setSize(640, 480);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(this);
        frame.addKeyListener(this);

        map = new int[10][10];
        enemies = new ArrayList<Enemy>();

        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                map[i][j] = 0;
            }
        }

        playerX = 5;
        playerY = 5;

        enemies.add(new Enemy(2, 2));
        enemies.add(new Enemy(7, 7));
        enemies.add(new Enemy(1, 9));
        enemies.add(new Enemy(8, 1));

        frame.setVisible(true);
    }

    public void paint(Graphics g) {
        super.paint(g);

        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                if(map[i][j] == 0) {
                    g.setColor(Color.WHITE);
                } else {
                    g.setColor(Color.BLACK);
                }
                g.fillRect(i * 32, j * 32, 32, 32);
            }
        }

        g.setColor(Color.BLUE);
        g.fillRect(playerX * 32, playerY * 32, 32, 32);

        g.setColor(Color.RED);
        for(Enemy e : enemies) {
            g.fillRect(e.x * 32, e.y * 32, 32, 32);
        }
    }

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_LEFT) {
            if(playerX > 0) playerX--;
        } else if(code == KeyEvent.VK_RIGHT) {
            if(playerX < 9) playerX++;
        } else if(code == KeyEvent.VK_UP) {
            if(playerY > 0) playerY--;
        } else if(code == KeyEvent.VK_DOWN) {
            if(playerY < 9) playerY++;
        } else if(code == KeyEvent.VK_SPACE) {
            if(map[playerX][playerY] == 0) {
                map[playerX][playerY] = 1;
            } else {
                map[playerX][playerY] = 0;
            }
        }

        for(int i = 0; i < enemies.size(); i++) {
            Enemy e1 = enemies.get(i);
            if(playerX == e1.x && playerY == e1.y) {
                enemies.remove(i);
                break;
            }
        }

        repaint();
    }

    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        new BlockyWorld();
    }

    class Enemy {
        int x, y;

        public Enemy(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}