package threads.Deadlocks.exercise;

public class WaitExercise {
    public static void main(String[] args){

        for (int i = 0; i < 5; i++) {
            MyThread myThread = new MyThread(Integer.toString(i));
            new Thread(myThread).start();
        }

    }
}
class WaitThreads{
     synchronized public void waitSecondThread(MyThread thread) throws InterruptedException {
        if(thread.name.equals("1") || thread.name.equals("0")){
            System.out.println("Thread is waiting " + thread.name);
            wait();
        }else {
            System.out.println(Thread.currentThread().getName() + " Thread is OK " + thread.name);
        }
    }

    synchronized public void notifyThread(MyThread thread) throws InterruptedException {
         if(thread.name.equals("4")) {
             notifyAll();

             System.out.println(Thread.currentThread().getName() + " Thread is notified");
         }
    }
}

class MyThread implements Runnable {
    String name;
    WaitThreads waitThreads = new WaitThreads();
    public MyThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try {
            waitThreads.waitSecondThread(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            waitThreads.notifyThread(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}