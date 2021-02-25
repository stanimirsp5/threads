package ThreadPoolImplementation.exercises;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FibonacciNumber {
    public static final int NUM_TASKS = 10;
    public static final int FIBONACCI_NUM = 30;
    public static void main(String[] args){
        int numThreads = 1,
            maxThreads = 15;
        long killInactiveThreadWhenMoreThanNumThreads = 5000L;
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            numThreads,
            maxThreads,
            killInactiveThreadWhenMoreThanNumThreads,
            timeUnit,
            queue
        );

        for (int i = 0; i < NUM_TASKS ; i++) {
            threadPoolExecutor.execute(new MyTask("Task "+ i, FIBONACCI_NUM));
        }
        threadPoolExecutor.shutdown();
    }
}

class MyTask implements Runnable{
    String name;
    int n;

    public MyTask(String name, int n) {
        this.name = name;
        this.n = n;
    }
    public int fib(int n){

        if(n == 0){
            return 0;
        }
        if(n == 1){
            return 1;
        }

        return fib(n-1) + fib(n-2);

    }
    @Override
    public void run() {
        System.out.println(name + " started counting fibonacci. Run by " + Thread.currentThread().getName());
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " ended counting fibonacci. Run by " + Thread.currentThread().getName() + " result is: "+ fib(n));
    }
}



