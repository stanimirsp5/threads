package BlockingQueueImplementation;

public class ItemsAdder {
    public static final int QUEUE_LIMIT = 5;

    public static void main(String[] args){

        BlockingQueue<Integer> queue = new BlockingQueue<>(QUEUE_LIMIT);
        Worker producer = new Worker(queue, WorkerType.PRODUCER, QUEUE_LIMIT);
        Worker consumer = new Worker(queue, WorkerType.CONSUMER, QUEUE_LIMIT);
        producer.start();
        consumer.start();
    }
}
enum WorkerType{
    PRODUCER,
    CONSUMER
}

class Worker<E> extends Thread{
    BlockingQueue<E> queue;
    WorkerType workerType;
    public static final int MAX_NUM = 20;
    int queueLimit;
    Integer i = 0;

    public Worker(BlockingQueue<E> queue, WorkerType workerType, int queueLimit){
        this.queue = queue;
        this.workerType = workerType;
        this.queueLimit = queueLimit;
    }

    public void addItem(E item){
        queue.enqueue(item);
    }

    @Override
    public void run() {

        if (workerType.equals(WorkerType.PRODUCER)) {
            for (;i <= MAX_NUM; i++) {
//                if(queue.size() >= queueLimit)
                addItem((E) i);
                System.out.println(" added item " + i);
            }
        } else {

            while ( i <= MAX_NUM){
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(queue.isQueueFull()){
                    System.out.printf("queue is full. %s will dequeue\n", Thread.currentThread().getName());

                    for (int j = 0; j < queueLimit; j++) {
                        E item = queue.dequeue();

                        System.out.println( item + " dequeue " + Thread.currentThread().getName());

                    }

                }
            }
        }
    }
}
