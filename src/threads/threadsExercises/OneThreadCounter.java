package threads.threadsExercises;

import java.util.Scanner;

public class OneThreadCounter implements Runnable{
    public volatile boolean isRunning = true;
    public int count;
    public static void main(String[] args){
        OneThreadCounter o = new OneThreadCounter();
        o.startThread();


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
                System.out.print(count++ + " " + Thread.currentThread().getName() + " ");
            }

        }

    }
}
