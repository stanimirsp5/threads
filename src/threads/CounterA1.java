package threads;//: CompteurA1.java
// Using the Runnable interface to turn the
// main class into a thread.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CounterA1 extends JPanel implements Runnable {
    private int count = 0;
    private Thread selfThread = null;
    private Button    stop = new Button("Stop"),
            start = new Button("Start");
    private TextField t = new TextField(10);
    private Label  l = new Label("Thread: no Thread counter yet");
    private boolean runFlag=true;
    public void init() {
        add(t);
        add(l);
        start.addActionListener(new StartL());
        add(start);
        stop.addActionListener(new StopL());
        add(stop);
    }
    public void run() {
        while (runFlag) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e){}
            t.setText(Integer.toString(count++));
            l.setText("Thread: "+selfThread.getName());
        }
        selfThread = null;
    }
    class StartL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(selfThread == null){
                selfThread = new Thread(CounterA1.this);
                runFlag=true;
                selfThread.start();
            }
        }
    }
    class StopL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(selfThread != null) {https://ff.tu-sofia.bg/AdvJava/Threads/Threads.html#Creat
            //  selfThread.stop();     deprecated
            runFlag =false;
            }
        }
    }
    public static void main(String[] args) {
        CounterA1 cnt = new CounterA1();
        JFrame aFrame = new JFrame("threads.CounterA1");
        aFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aFrame.add(cnt);
        aFrame.setSize(300,200);
        cnt.init();
        aFrame.setVisible(true);
    }
}