import java.io.*;

public class Threads {

    public static void main(String[] args) {

//        RunnableImpl thread1 = new RunnableImpl();
//        thread1.run();

        ThreadExtends thread2 = new ThreadExtends();
        thread2.RunThread();

    }

}

class RunnableImpl implements Runnable{

    @Override
    public void run() {
        System.out.println("This code is running in a thread");
    }
}

class ThreadExtends extends Thread{
    public static int amount = 0;

    //run thread
    public static void RunThread(){
        ThreadExtends thread = new ThreadExtends();
        thread.start();

        // Wait for the thread to finish
        while(thread.isAlive()) {
            System.out.println("Waiting...");
        }

        System.out.println(amount);
        amount++;
        System.out.println(amount);
    }

    @Override
    public void run() { // create thread
        amount++;
        System.out.println("This code is running in a thread");
    }
}
