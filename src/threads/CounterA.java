package threads;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CounterA extends JPanel implements Runnable  {
    private int count = 0;
    private boolean runFlag = true;
    private Thread selfThread = null;
    private Button
            onOff = new Button("Stop"),
            start = new Button("Start");
    private TextField t = new TextField(10);
    private Label  l = new Label("Thread: no Thread counter yet");
    public void init() {
        add(t);
        start.addActionListener(new StartL());
        add(start);
        onOff.addActionListener(new OnOffL());
        add(onOff);
        add(l);
    }
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e){}
            if(runFlag) {
                t.setText(Integer.toString(count++));
                l.setText("Thread: "+selfThread.getName());
            }
        }
    }

    class StartL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(selfThread == null){
                selfThread = new Thread(CounterA.this);
                selfThread.start();
            }
            runFlag = true;
        }
    }
    class OnOffL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            runFlag = false;
        }
    }
    public static void main(String[] args) {
        CounterA cnt = new CounterA();
        JFrame frame = new JFrame("threads.CounterA");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(cnt);
        frame.setSize(300,200);
        cnt.init();
        frame.setVisible(true);
    }
}  