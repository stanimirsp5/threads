package threads.threadsExercises;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ThreadFactory implements Runnable{
    JButton toggleB = new JButton("Toggle");
    JTextField textField = new JTextField();
    static Thread selfThread;
    static ArrayList<ThreadFactory> threads = new ArrayList<ThreadFactory>();
    private static int NUM_THREADS = 3;
    boolean isRunning = true;
    static boolean threadsStarted;



    public void createThread(int y){
        selfThread = new Thread(ThreadFactory.this);

        //JPanel p = new JPanel();
        toggleB = new JButton("Toggle " + selfThread.getName());
        textField = new JTextField("Text f" + y);
        toggleB.setBounds(150, y, 200, 30);
        textField.setBounds(50, y, 100, 30);
        toggleB.addActionListener(new ThreadFactory.ToggleThread());
        GUI.f.add(toggleB);GUI.f.add(textField);//GUI.f.add(p);
        selfThread.start();
    }

    @Override
    public void run() {
        int counter = 0;

        while (true){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(isRunning) {
                textField.setText(String.valueOf(counter));
                counter++;
                System.out.println(Thread.currentThread().getName() + " counter: " + counter);
            }
            if(counter == 50){
                textField.setText(String.valueOf(counter) + " Job done");
                break;
            }
        }
    }

    public void start(){
        isRunning = true;

    }
    public void stop(){
        isRunning = false;

    }

    class ToggleThread implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            isRunning = !isRunning;
        }
    }


    static class StartThread implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(!threadsStarted) {
                threadsStarted = true;
                //threads = new ThreadFactory[NUM_THREADS];
                for (int i = 0; i < NUM_THREADS; i++) {
                    ThreadFactory threadFactory = new ThreadFactory();
                    threads.add(threadFactory);
                    threadFactory.createThread(i*25+100);
                }
            }else{
                for (int i = 0; i < NUM_THREADS; i++) {
                    threads.get(i).start();
                }
            }
        }
    }
    static class StopThread implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {

                for (int i = 0; i < NUM_THREADS; i++) {
                    threads.get(i).stop();
                }

        }
    }
    static class NewThread implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                NUM_THREADS++;
                ThreadFactory threadFactory = new ThreadFactory();
                threadFactory.createThread(threads.size()*25+100);
                threads.add(threadFactory);
            }
        }

}
