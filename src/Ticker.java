//: CounterPT.java
// If you separate your thread from the main
// class, you can have as many threads as you want.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Ticker extends Thread {
    private Button b = new Button("Toggle");
    private TextField t = new TextField(10);
    private int count = 0;
    private boolean runFlag = true;
    public Ticker(Container c) {
        b.addActionListener(new ToggleL());
        JPanel p = new JPanel();
        p.add(t); p.add(b); c.add(p);
    }
    class ToggleL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            runFlag = !runFlag;
        }
    }
    public void run() {
        while (true) {
            if(runFlag)
                t.setText(Integer.toString(count++));
            try {
                sleep(100);
            } catch (InterruptedException e){}
        }
    }
    public void stp() {
        runFlag = false;
    }
    public void restart() {
        runFlag = true;
    }
}

class CounterPT extends JPanel {
    private Button start = new Button("Start");
    private Button stop = new Button("Stop");
    private Button restart = new Button("Restart");
    private boolean started = false;
    private Ticker[] s;
    private int size;
    public void init() {
        this.setLayout(new FlowLayout());
        s = new Ticker[size];
        for(int i = 0; i < s.length; i++)
            s[i] = new Ticker(this);
        start.addActionListener(new StartL());
        add(start);
        stop.addActionListener(new StopL());
        add(stop);
        restart.addActionListener(new RestartL());
        add(restart);
    }
    class StartL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(!started) {
                started = true;
                for(int i = 0; i < s.length; i++) s[i].start();
            }
        }
    }
    class StopL implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            for(int i=0;i<s.length;i++) s[i].stp();
        }
    }
    class RestartL implements ActionListener{
        public void actionPerformed(ActionEvent e){
            for(int i=0; i< s.length; i++) s[i].restart();
        }
    }
    public static void main(String[] args) {
        CounterPT cnt = new CounterPT();

        cnt.size = (args.length == 0 ? 5 : Integer.parseInt(args[0]));
        JFrame aFrame = new JFrame("CounterPT");
        aFrame.add(cnt);
        aFrame.setSize(200*(1+cnt.size/10), 500);
        aFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cnt.init();
        aFrame.setVisible(true);
    }
}