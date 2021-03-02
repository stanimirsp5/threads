package BlockingQueueImplementation;

public class ItemsAdder {
    public static final int QUEUE_LIMIT = 5;
    public static void main(String[] args){

        BlockingQueue<Integer> queue = new BlockingQueue<>(QUEUE_LIMIT);
        Worker producer;
        Worker consumer;
        for (int i = 0; i < 20; i++) {
            producer = new Worker(queue,i, true);
            producer.start();
        }
    }
}
class Worker<E> extends Thread{
    BlockingQueue<E> queue;
    E item;
    boolean pOc;

    public Worker(BlockingQueue<E> queue,E item,boolean pOc){
        this.queue = queue;
        this.item = item;
        this.pOc = pOc;
    }

    @Override
    public void run() {

        if (pOc) {
            queue.enqueue(item);
        } else {
            queue.dequeue();
        }

    }
}
