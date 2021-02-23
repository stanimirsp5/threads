package ThreadPoolImplementation.examples;

import java.util.concurrent.*;
import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;
import java.text.*;

public class FibonacciNumber {
    public static void main(String[] args) {
        int nTasks = 20;    // number of tasks to be submitted to pool
        long n = 30;       //Fibonacci number
        int tpSize = 5;  // corePoolSize
        LinkedBlockingQueue<Runnable> q;

        ThreadPoolExecutor tpe = new ThreadPoolExecutor(
                tpSize, tpSize, 50000L, TimeUnit.MILLISECONDS,
                ( q=new LinkedBlockingQueue<Runnable>( )));

/*     public ThreadPoolExecutor(int corePoolSize,
                  int maximumPoolSize,
                  long keepAliveTime,
                  TimeUnit unit,
                  BlockingQueue<Runnable> workQueue)
*/
        System.out.println("Initial number of threads:"+tpe.getActiveCount());
        Task[] tasks = new Task[nTasks];
        for (int i = 0; i < nTasks; i++) {
            tasks[i] = new Task(n, "Task " + i,tpe);
            tpe.execute(tasks[i]);
            System.out.println("submit int task "+i+
                    " number of active threads "+tpe.getActiveCount()+
                    " number of task in the queue "+q.size());
        }
        tpe.shutdown( );
    }
}

class Task implements Runnable {
    long n;
    String id;
    ThreadPoolExecutor tpe;
    private long fib(long n) {
        if (n == 0)
            return 0L;
        if (n == 1)
            return 1L;
        return fib(n - 1) + fib(n - 2);
    }
    public Task(long n, String id, ThreadPoolExecutor tpe) {
        this.n = n;
        this.id = id;
        this.tpe=tpe;
    }
    public void run( ) {
        Date d = new Date( );
        DateFormat df = new SimpleDateFormat("HH:mm:ss:SSS");
        long startTime = System.currentTimeMillis( );
        d.setTime(startTime);
        System.out.println("Starting task " + id + " at " + df.format(d)+ "; active threads:"
                +tpe.getActiveCount());
        System.out.println("\tfibonatchi "+ n+":"+ fib(n));
        long endTime = System.currentTimeMillis( );
        d.setTime(endTime);
        System.out.println("\tEnding task " + id + " at " + df.format(d) +" after "
                + (endTime - startTime) + " milliseconds");
    }
}