package ThreadPoolImplementation;

import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {
    private final int nThreads;
    private final PoolWorker[] threads;
    private final LinkedBlockingQueue queue;
    public volatile boolean isProcessing = true;

    public ThreadPool(int nThreads) {
        this.nThreads = nThreads;
        queue = new LinkedBlockingQueue();
        threads = new PoolWorker[nThreads];

        for (int i = 0; i < nThreads; i++) {
            threads[i] = new PoolWorker(i);
            threads[i].start();
        }
    }

    public void execute(Runnable task){
        synchronized (queue){ // You do not have to synchronize a whole method. Sometimes it is preferable to synchronize only part of a method. Java synchronized blocks inside methods makes this possible.
            queue.add(task);
            queue.notify();
        }
    }

    public void shutdown(){
        isProcessing = false;
    }

    private class PoolWorker extends Thread{
        int threadName;
        public PoolWorker(int name){
                this.threadName = name;
        }

        public void run(){
            Runnable task;
            //System.out.println("Pool worker: Thread-" + threadName + " created");
            while (isProcessing){
                synchronized (queue){
                    while (queue.isEmpty()){
                        try {
                            System.out.println("wait...");
                            queue.wait();
                        }catch (InterruptedException e){
                            System.out.println("Error while queue is waiting: " + e.getMessage());
                        }
                    }
                    task = (Runnable) queue.poll();
                }

                try{
                    task.run();
                }catch (RuntimeException e){
                    System.out.println("Thread pool is interupted due to an issue: "+ e.getMessage());
                }

            }

        }

    }

}
