package ThreadPoolImplementation.NormalTP;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
