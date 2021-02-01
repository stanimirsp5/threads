package threads.Synchronization.exercise;

public class Incrementer {
    public static final int N = 5;

    public static void main(String[] args) {
        Counter counter = new Counter();

        Thread producerThread = new Thread(new ProducerThread(counter));
        producerThread.start();
        Thread consumerThread = new Thread(new ConsumerThread(counter));
        consumerThread.start();
    }

    static class ProducerThread implements Runnable {
        Counter counter;

        public ProducerThread(Counter counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            for (int i = 0; i < N; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                counter.setValue(i);
            }
        }
    }

    static class ConsumerThread implements Runnable {
        Counter counter;

        public ConsumerThread(Counter counter) {
            //super("Set Shared");
            this.counter = counter;
        }

        @Override
        public void run() {
            int val = 0;
            while (val <= 10) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                val = counter.getValue();
            }
        }
    }


    static class Counter {
        int value;

        public void setValue(int inc) {
            value += inc;
        }

        public int getValue() {
            System.out.println(value);
            return value;
        }
    }

}