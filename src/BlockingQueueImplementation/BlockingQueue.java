package BlockingQueueImplementation;
import java.io.*;
import java.util.*;

//public class test {
//
//}
public class BlockingQueue<E> {

    List<E> queue = new LinkedList<>();
    private int boundLimit;

    public BlockingQueue(int boundLimit){
        this.boundLimit = boundLimit;
    }

    public synchronized void enqueue(E item){
        if(queue.size() == boundLimit){
            System.out.printf("queue is full for item %d. Waiting %s \n", item, Thread.currentThread().getName());
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println("element is added by " + Thread.currentThread().getName());

        queue.add(item);
    }

    public synchronized E dequeue(){
        E item = queue.remove(0);
        System.out.println("element is removed by "+Thread.currentThread().getName());

        if(queue.size() == 0){
            System.out.println("No elements in queue, notify all threads " + Thread.currentThread().getName());

            notifyAll();
        }

        return item;
    }
    public int size(){
        return queue.size();
    }
    public boolean isQueueFull(){
        //System.out.println(Thread.currentThread().getName()+" checking for full queue -> "+(queue.size() >= boundLimit)+" queue size "+queue.size()+" limit "+boundLimit);
        return queue.size() >= boundLimit;
    }
}