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

    private mapbrick map;

    public GamePlay(){
        map = new mapBrick (3,7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);
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
    }





    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
