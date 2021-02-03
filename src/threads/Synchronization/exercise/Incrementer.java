package threads.Synchronization.exercise;

public class Incrementer {
    public static final int N = 5;

    public static void main(String[] args) {
        Counter counter = new Counter();

        Thread producerThread = new Thread(new ProducerThread(counter));
        Thread consumerThread = new Thread(new ConsumerThread(counter));
        producerThread.start();
        consumerThread.start();

    }

    static class ProducerThread implements Runnable {
        Counter counter;

        public ProducerThread(Counter counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            for (int i = 1; i <= N; i++) {
                try {
                    Thread.sleep(50);
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
            this.counter = counter;
        }

        @Override
        public void run() {
            int val = 0;
            int sum = 0;
            while (val <= N) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                val = counter.getValue();
                sum += val;
                System.out.println("Val "+ val);

            }
            System.out.println("Total sum: "+ sum);
        }
    }


    static class Counter {
        int value;
        private boolean isValueGet = true;

        public synchronized void setValue(int inc) {
            while (!isValueGet){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            isValueGet = false;
            notify();
            value += inc;
        }

        public synchronized int getValue() {

            while (isValueGet){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            isValueGet = true;
            notify();
            //System.out.println(value);
            return value;
        }
    }

}