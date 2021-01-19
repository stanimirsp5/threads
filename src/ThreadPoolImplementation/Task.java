package ThreadPoolImplementation;

public class Task implements Runnable {
    private int num;
    private long sleepTime;

    public Task(int n, long sleepTime){
        num = n;
        this.sleepTime = sleepTime;
    }

    public void run(){
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Task "+ num + " is running on " + Thread.currentThread().getName());
    }

}
