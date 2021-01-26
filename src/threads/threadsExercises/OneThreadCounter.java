package threads.threadsExercises;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
//https://www.javatpoint.com/java-jtextfield
public class OneThreadCounter implements Runnable{
    public volatile boolean isRunning = true;
    public int count;
    static JTextField textField = new JTextField();

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

        b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                OneThreadCounter o = new OneThreadCounter();
                o.startThread();
            }
        });
        for (int i = 0; i < 100; i++) {
            Thread.sleep(100);
            textField.setText(String.valueOf(i));

        }
    }

    public void startThread(){
        Thread t = new Thread(OneThreadCounter.this);
        t.start();

        Scanner scanner  = new Scanner(System.in);
        while (true) {
            String command = scanner .nextLine();

            if (command.equals("")) {
                isRunning = !isRunning;
            }
        }
    }

    @Override
    public void run() {

        while (true){

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (isRunning){
                textField.setText(count++ + " " + Thread.currentThread().getName() + " ");
                System.out.print(count++ + " " + Thread.currentThread().getName() + " ");
            }

        }

    }
}
