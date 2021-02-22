package threads.Timers.exercise;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class ClickSquareGame {

    ClickSquareGame()
    {

        JFrame f= new JFrame("Panel Example");
        JPanel panel=new JPanel();
        panel.setBounds(40,80,50,50);
        panel.setBackground(Color.gray);
        Label l = new Label("0");
        FrameSquare frameSquare = new FrameSquare(l);

        l.setBounds(50,50, 100,30);
        f.add(l);
        f.add(panel);
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frameSquare.addNum();
                frameSquare.move(panel);
            }
        });
        f.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frameSquare.extractNum();
            }
        });
        f.setSize(400,400);
        f.setLayout(null);
        f.setVisible(true);
    }

    public static void main(String args[])
    {

        new ClickSquareGame();
    }
}

class FrameSquare{
    int number;
    Label l;

    public FrameSquare(Label l) {
        this.l = l;
    }

    public void addNum(){
        number++;
        l.setText(String.valueOf(number));
    }
    public void extractNum(){
        number--;
        l.setText(String.valueOf(number));
    }

    public void move(JPanel panel){
        panel.setBounds(140,180,50,50);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

            }
        },1000);
    }
}

//class Mo{
//
//}
