package threads.Timers.exercise;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import java.util.TimerTask;
import javax.swing.*;
import java.util.Timer;

public class TestApp {
    public static void main(String arg[]) {
        JFrame frame = new JFrame("Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Test ex = new Test();
        frame.add(ex);
        ex.init();
        frame.setSize(250, 250);
        frame.setVisible(true);
    }
}

class Test extends JPanel{
    boolean bleu = true;
    public int x=30,y=30, rd=30;
    Label l = new Label("0");
    int cnt=0;
    Color c;
    public void init(){
        c=Color.blue;
        setForeground(c);
        addMouseListener(new MouseHandler());
        add(l);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g) ;
        g.fillRect(x, y, rd, rd);
    }
    boolean in (int mx,int my){
        if((mx>x) && (mx<x+rd ))
            if((my>y)&&(my<y+rd)) return true;
        return false;
    }
    class MouseHandler extends MouseAdapter {
        public void mousePressed(MouseEvent e){
            if(in(e.getX(),e.getY())) cnt++;
            else cnt--;
            l.setText(cnt+"");
        }
    }
}

class Game {
    static Timer move;
    static Timer clck;
    static Test ex;
    static int width=250, height=250;
    public static void main (String arg[]) {
        move =new Timer();
        JFrame frame = new JFrame("Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ex = new Test();
        frame.add(ex);
        ex.init();
        frame.setSize(width, height);
        frame.setVisible(true);

        move.schedule(new MvTask(),
                1000,        //initial delay
                1*1000);  //subsequent rate
    }
    static class MvTask extends TimerTask {
        public void run() {
            ex.x= (int)(Math.random()*(width-2*ex.rd)+ex.rd);
            ex.y= (int)(Math.random()*(height-2*ex.rd)+ex.rd);
            ex.repaint();
        }
    }
}