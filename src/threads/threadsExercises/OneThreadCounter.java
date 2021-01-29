package threads.threadsExercises;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.util.Set;

//https://www.javatpoint.com/java-jtextfield
public class OneThreadCounter implements Runnable{
    public static boolean isRunning = true;
    public int count;
    static JTextField textField = new JTextField();
    static JLabel runnableLabel = new JLabel();
    private Thread selfThread = null;

    public static void main(String[] args) throws InterruptedException {

        JFrame f=new JFrame();//creating instance of JFrame

        JButton b=new JButton("start");//creating instance of JButton
        b.setBounds(130,100,100, 40);//x axis, y axis, width, height

        JButton pauseB=new JButton("pause");//creating instance of JButton
        pauseB.setBounds(130,200,100, 40);//x axis, y axis, width, height

        runnableLabel = new JLabel("First Label.");
        runnableLabel.setBounds(50,50, 100,30);
        f.add(runnableLabel);

        textField.setBounds(50,300, 100,30);

        f.add(b);//adding button in JFrame
        f.add(pauseB);//adding button in JFrame
        f.add(textField);

        f.setSize(400,500);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible

        System.out.println(Thread.currentThread().getName());

        b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                OneThreadCounter o = new OneThreadCounter();
                o.startThread();
            }
        });
        pauseB.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                OneThreadCounter o = new OneThreadCounter();
                o.pauseCounter();
            }
        });

    }

    public void showActiveThreads(){
        Thread.getAllStackTraces().keySet().forEach((t) -> System.out.println(t.getName()));
    }

    public void startThread(){
        Thread t = new Thread(OneThreadCounter.this);
        t.start();
//        selfThread = new Thread(OneThreadCounter.this);
//        selfThread.start();
    }

    public void pauseCounter(){
        isRunning = !isRunning;
        runnableLabel.setText(String.valueOf(isRunning));
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());

        while (true){

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (isRunning){
                textField.setText(count++ + " " + Thread.currentThread().getName() + " ");
            }
        }
    }
}


//    public void startThread(){ // stop running of the thread when type
//        Thread t = new Thread(OneThreadCounter.this);
//        t.start();
//        selfThread = new Thread(OneThreadCounter.this);
//        selfThread.start();

//        Scanner scanner  = new Scanner(System.in);
//        while (true) {
//            String command = scanner.nextLine();
//
//            if (command.equals("")) {
//                isRunning = !isRunning;
//            }
//        }
//    }