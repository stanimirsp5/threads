package threadExamples;
// An atomic operation is something that cannot be interrupted
public class IncrementCounter {
    static Counter counter;
    static int total;
    public static void main(String[] args) throws InterruptedException {
        counter = new Counter();
        for (int i = 1; i <=2; i++) {

            Thread t = new Thread(new ThreadGenerator("Thread " + (i-1)));
            t.start();

            t.join();

        }
//        System.out.println(counter.getCount());
        System.out.println(total);

    }

    public static class Counter {
//        volatile
        int count = 1; //var is no-cached in thread and it`s taken from main memory
        //  no thread will keep a local copy of that variable in its cache. Instead, the thread will always use the official, main copy of the variable.
       void inc() {
            count = count + count;
           //System.out.println(count);

       }

       int getCount() {
            return count;
        }
    }

    static class ThreadGenerator implements Runnable {
        private String caller;
        public ThreadGenerator(String caller) {
            this.caller = caller;
        }

        @Override
        public void run() {
            //counter.inc();
            for ( int i = 0; i < 100; i++ )
            {
                System.out.println(Thread.currentThread().getName() );

                total = total + 1;
            }
//            System.out.println(caller);
          //  System.out.println("Caller: " + caller + " thread: " + Thread.currentThread().getName() + " count " + counter.getCount());
        }
    }
}