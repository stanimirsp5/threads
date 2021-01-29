package threads.threadsExercises;
// new thread every time

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class NewThreadCounter{

    public static boolean isRunning = true;
    public static int counter;

    static JTextField textField = new JTextField();
    static JLabel runnableLabel = new JLabel();
    static JButton startB = new JButton("Start"), pauseB = new JButton("Pause");
    static Thread selfThread = new Thread();
    
    public static void main(String[] args){
        UI ui = new UI();
        ui.init();
    }

//    public void startAction{
//
//    }
    static class StartThread implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            selfThread = null;
            Counter counter = new Counter();
            selfThread = new Thread(counter);
            selfThread.start();
        }
    }
    static class StopThread implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            isRunning = !isRunning;
        }
    }

    static class Counter implements Runnable{
        @Override
        public void run() {
           while (isRunning){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                textField.setText(Thread.currentThread().getName() + " " + counter++);
            }


        }
    }

    static class UI {
        public void init() {
            JFrame f = new JFrame();//creating instance of JFrame

            startB.setBounds(130, 100, 100, 40);//x axis, y axis, width, height
            pauseB.setBounds(130, 200, 100, 40);//x axis, y axis, width, height

            runnableLabel.setBounds(50, 50, 100, 30);
            textField.setBounds(50, 300, 100, 30);

            f.add(startB);f.add(pauseB);
            f.add(textField);
            f.add(runnableLabel);

            f.setSize(400, 500);//400 width and 500 height
            f.setLayout(null);//using no layout managers
            f.setVisible(true);//making the frame visible

            startB.addActionListener(new StartThread());
            pauseB.addActionListener(new StopThread());
        }
    }
}


//    Scanner scanner = new Scanner(System.in);
//        while (true) {
//                String command = scanner .nextLine();
//
//                if (command.equals("")) {
//                isRunning = !isRunning;
//                }
//                if(isRunning){
//                Thread t2 = new Thread(counter);
//                t2.start();
//                }
//                }