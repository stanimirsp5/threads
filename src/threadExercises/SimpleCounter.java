package threadExercises;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimpleCounter {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Counter counter = new Counter();

        for (int i = 0; i < 10000; i++) {
            executorService.submit(() -> counter.increment());
        }

        executorService.shutdown();
        executorService.awaitTermination(60, TimeUnit.SECONDS);

        System.out.println("Final count is : "+ counter.getCount());

    }

    static class Counter{
       int count = 0;

        public void increment(){

            synchronized (this){ // intrinsic lock
                count++;
            }
            // When a thread acquires the intrinsic lock on an object, other threads must wait until the lock is released. However, the thread that currently owns the lock can acquire it multiple times without any problem.
            // The idea of allowing a thread to acquire the same lock more than once is called Reentrant Synchronization.
        }

        public int getCount(){
            return count;
        }

    }

}

//public class VolatileKeywordExample {
//    private static volatile boolean sayHello = false;
//
//    public static void main(String[] args) throws InterruptedException {
//
//        Thread thread = new Thread(() -> {
//            while(!sayHello) {
//            }
//
//            System.out.println("Hello World!");
//
//            while(sayHello) {
//            }
//
//            System.out.println("Good Bye!");
//        });
//
//        thread.start();
//
//        Thread.sleep(1000);
//        System.out.println("Say Hello..");
//        sayHello = true;
//
//        Thread.sleep(1000);
//        System.out.println("Say Bye..");
//        sayHello = false;
//    }
//}