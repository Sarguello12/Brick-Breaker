package src;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import javax.swing.JPanel;

public class GamePlay extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;
    private int score = 0;
    private int totalBricks = 21;
    private Timer time;
    private int delay = 5;
    private int player1 = 310;
    private int ballposx = 120;
    private int ballposy = 350;
    private int ballxdir = -1;
    private int ballydir = -2;

    private mapBrick map;

    public GamePlay(){
        map = new mapBrick(4,12);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        time = new Timer(delay, this);
        time.start();
    }

    public void paint(Graphics g){
        //background
        g.setColor(Color.black);
        g.fillRect(1,1,692,592);
        //draw
        map.draw((Graphics2D)g);
        //borders
        g.setColor(Color.yellow);
        g.fillRect(0,0,3,592);
        g.fillRect(0,0,692,3);
        g.fillRect(691, 0,3,592);
        //scores
        g.setColor(Color.white);
        g.setFont(new Font("Verdana", Font.BOLD, 25));
        g.drawString(""+score, 590, 30);
        //paddle
        g.setColor(Color.green);
        g.fillRect(player1,550,100,8);
        //ball
        g.setColor(Color.yellow);
        g.fillOval(ballposx, ballposy,20,20);

        if(totalBricks <= 0){
            play = false;
            ballxdir = 0;
            ballydir = 0;
            g.setColor(Color.red);
            g.setFont(new Font("Verdana", Font.BOLD, 35));
            g.drawString("YOU WON!", 190, 300);

            g.setFont(new Font("Verdana", Font.BOLD, 30));
            g.drawString("Press ENTER to restart", 230, 350);
        }

        if(ballposy > 570){
            play = false;
            ballxdir = 0;
            ballydir = 0;
            g.setColor(Color.red);
            g.setFont(new Font("Verdana", Font.BOLD, 35));
            g.drawString("GAME OVER!", 190, 300);

            g.setFont(new Font("Verdana", Font.BOLD, 30));
            g.drawString("Press ENTER to restart", 230, 350);
        }
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        time.start();
        if(play){
            if(new Rectangle(ballposx, ballposy, 20, 20).intersects(new Rectangle(player1, 550, 100, 8))) {
                ballydir = -ballydir;
            }

            B: for(int i = 0; i < map.map.length; i++){
                for(int j = 0; j < map.map[0].length; j++){
                    if(map.map[i][j] > 0){
                        int brickX = j * map.brickWidth + 80;
                        int brickY = i * map.brickHeight + 50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;

                        Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballposx, ballposy,20,20);
                        Rectangle brickRect = rect;

                        if(ballRect.intersects(brickRect)){
                            map.setBrickVal(0, i, j);
                            totalBricks--;
                            score += 10;

                            if(ballposx + 19 <= brickRect.x || ballposx + 1 >= brickRect.x + brickRect.width){
                                ballxdir = -ballxdir;
                            } else {
                                ballydir = -ballydir;
                            }

                            break B;
                        }
                    }
                }
            }
            ballposx += ballxdir;
            ballposy += ballydir;
            if(ballposx < 0){
                ballxdir = -ballxdir;
            }
            if(ballposy < 0){
                ballydir = -ballydir;
            }
            if(ballposx > 670){
                ballxdir = -ballxdir;
            }
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(player1 >= 600){
                player1 = 600;
            } else {
                moveRight();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(player1 < 10){
                player1 = 10;
            } else {
                moveLeft();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(!play){
                play = true;
                ballposx = 120;
                ballposy = 350;
                ballxdir = -1;
                ballydir = -2;
                player1 = 310;
                score = 0;
                totalBricks = 21;
                map = new mapBrick(3,7);
                repaint();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    public void moveRight(){
        play = true;
        player1 += 20;
    }

    public void moveLeft(){
        play = true;
        player1 -= 20;
    }

}
