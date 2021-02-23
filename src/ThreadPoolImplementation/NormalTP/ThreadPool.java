package ThreadPoolImplementation.NormalTP;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//    public ThreadPoolExecutor(int corePoolSize - the number of threads to keep in the pool, even if they are idle
//                              int maximumPoolSize - the maximum number of threads to allow in the pool
//                              long keepAliveTime - when the number of threads is greater than the core,
//                                                      this is the maximum time that excess idle threads will wait for new tasks before terminating.
//                              TimeUnit unit - the time unit for the keepAliveTime argument
//                              BlockingQueue<Runnable> workQueue - the queue to use for holding tasks
//                                                                    before they are executed. This queue will hold
//                                                                    only the Runnable tasks submitted by the execute
//                                                                    method
//                              )

public class ThreadPool {

    static final int THREAD_NUM = 3;

    public static void main(String[] args){
       Pool p = new Pool();
       p.ExecuteTasks();
    }
    static class Pool {
        public void ExecuteTasks() {

            Runnable r1 = new Task();

            ExecutorService pool = Executors.newFixedThreadPool(5);//.newFixedThreadPool(THREAD_NUM);
            pool.execute(r1);
            pool.execute(new Task());
            pool.execute(new Task());
            pool.execute(new Task());
        }
    }

    static class Task implements Runnable{

        @Override
        public void run() {
            System.out.println("Name " + Thread.currentThread().getName()+" started");
        }
    }

}
