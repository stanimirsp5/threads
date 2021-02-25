package ThreadPoolImplementation.exercises;

import java.util.concurrent.*;

//    public ThreadPoolExecutor(int corePoolSize - the number of threads to keep in the pool, even if they are idle;
//                              int maximumPoolSize - the maximum number of threads to allow in the pool;
//                              long keepAliveTime - when the number of threads is greater than the core,
//                                                      this is the maximum time that excess idle threads will wait for new tasks before terminating.
//                              TimeUnit unit - the time unit for the keepAliveTime argument
//                              BlockingQueue<Runnable> workQueue - the queue to use for holding tasks
//                                                                    before they are executed. This queue will hold
//                                                                    only the Runnable tasks submitted by the execute
//                                                                    method
//                              )
public class PoolExtractor {
    public final static int NUM_TASKS = 10;
    public final static int NUM_THREADS = 2;
    public static void main(String[] args){

        //poolWithExtractor();
        poolWithExecutor();
    }

    public static void poolWithExecutor() {
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        for (int i = 0; i < NUM_TASKS; i++) {
                executor.execute(new Task("e task " + i));
        }
    }

    public static void poolWithExtractor(){
        int numThreadsInPool = NUM_THREADS;
        int maxThreads = 2;
        long keepAlive = 50000L;
        TimeUnit timeUnitForKeepAlive =TimeUnit.MILLISECONDS;
        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                numThreadsInPool,
                maxThreads,
                keepAlive,
                timeUnitForKeepAlive,
                queue
        );

        for (int i = 0; i < NUM_TASKS; i++) {
            threadPoolExecutor.execute(new Task("task "+i));
            //new Thread(new Task("task "+i)).start();
        }
        threadPoolExecutor.shutdown();
    }

}

class Task implements Runnable{
    String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + " started by " + Thread.currentThread().getName());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       // System.out.println(name + " ended by " + Thread.currentThread().getName());
    }
}

