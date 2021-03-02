package BlockingQueueImplementation;

public class ItemsAdder {
    public static final int QUEUE_LIMIT = 5;
    public static void main(String[] args){


        BlockingQueue<Integer> queue = new BlockingQueue<>(QUEUE_LIMIT);

        for (int i = 0; i < 20; i++) {
            queue.enqueue(i);
        }


    }
}
