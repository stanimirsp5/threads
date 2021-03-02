package BlockingQueueImplementation;
import java.io.*;
import java.util.*;

//public class test {
//
//}
public class BlockingQueue<E> {

    List<E> queue = new LinkedList<>();
    private int boundLimit = 10;

    public BlockingQueue(int boundLimit){
        this.boundLimit = boundLimit;
    }

    public synchronized void enqueue(E item){
        if(queue.size() == boundLimit){
            System.out.println("queue is full. waiting " + item);
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("element is added");

        queue.add(item);
    }

    public synchronized E dequeue(){
        if(queue.size() == 0){
            System.out.println("No elements in queue, notify all");

            notifyAll();
        }
        System.out.println("element is removed");

        return queue.remove(0);
    }

}