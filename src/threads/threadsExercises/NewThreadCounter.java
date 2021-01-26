package threads.threadsExercises;
// new thread every time

import java.util.Scanner;

public class NewThreadCounter{

    public static boolean isRunning = true;
    public static int counter;

    public static void main(String[] args){
        Counter counter = new Counter();
        Thread t = new Thread(counter);
        t.start();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner .nextLine();

            if (command.equals("")) {
                isRunning = !isRunning;
            }
            if(isRunning){
                Thread t2 = new Thread(counter);
                t2.start();
            }
        }

    }

    static class Counter implements Runnable{
        @Override
        public void run() {

           while (true){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (isRunning){
                    System.out.print(Thread.currentThread().getName() + " " + counter++);
                }else {
                    break;
                }

            }

        }
    }
}
