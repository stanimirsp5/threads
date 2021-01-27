package threads.threadsExercises;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.util.Set;

//https://www.javatpoint.com/java-jtextfield
public class OneThreadCounter implements Runnable{
    public volatile boolean isRunning = true;
    public int count;
    static JTextField textField = new JTextField();
    private Thread selfThread = null;

    public static void main(String[] args) throws InterruptedException {

        JFrame f=new JFrame();//creating instance of JFrame

        JButton b=new JButton("start");//creating instance of JButton
        b.setBounds(130,100,100, 40);//x axis, y axis, width, height

        textField.setBounds(50,300, 100,30);

        f.add(b);//adding button in JFrame
        f.add(textField);

        f.setSize(400,500);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible

//        b.addActionListener(new ActionListener(){
//            public void actionPerformed(ActionEvent e){
//                OneThreadCounter o = new OneThreadCounter();
//                o.startThread();
//            }
//        });
        OneThreadCounter o = new OneThreadCounter();
        o.startThread();
        o.showActiveThreads();

    }

    public void showActiveThreads(){
//        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
//        System.out.println(threadSet);

        Thread.getAllStackTraces().keySet().forEach((t) -> System.out.println(t.getName()));
    }

    public void startThread(){
//        Thread t = new Thread(OneThreadCounter.this);
//        t.start();
        selfThread = new Thread(OneThreadCounter.this);
        selfThread.start();

//        Scanner scanner  = new Scanner(System.in);
//        while (true) {
//            String command = scanner .nextLine();
//
//            if (command.equals("")) {
//                isRunning = !isRunning;
//            }
//        }
    }

    @Override
    public void run() {

//        while (true){
//
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
////            if (isRunning){
////                textField.setText(count++ + " " + selfThread.getName() + " ");
////                System.out.print(count++ + " " + selfThread.getName() + " ");
////            }
//
//
//        }
        //System.out.println(Thread.currentThread().getName());
        //System.out.println(selfThread.getName());

    }
}
