package ThreadPoolImplementation.examples;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SwimPool {
    public final static int NUM_SWIMMERS = 10;
    public static void main(String[] args){
        LinkedBlockingQueue queue = new LinkedBlockingQueue();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,10,50000L, TimeUnit.MILLISECONDS, queue);
        PoolManagement poolManagement = new PoolManagement();
        for (int i = 0; i < NUM_SWIMMERS; i++) {
            threadPoolExecutor.execute(new Swimmer(poolManagement));
            System.out.println("next client,there is "+queue.size()+" elements in queue");
//            threadPoolExecutor.execute(new Swimmer(poolManagement,"Swimmer "+ i));
        }
        threadPoolExecutor.shutdown();
    }
}

class Swimmer implements Runnable{
    String name;
    static int n = 0;
    PoolManagement poolManagement;
    Swimmer(PoolManagement poolManagement) {
        name = "Swimmer " + ++n;
        this.poolManagement = poolManagement;
        //this.name = name;
        //poolManagement.swimmerName = name;
    }

    @Override
    public String toString(){
        return name;
    }

    @Override
    public void run() {

        System.out.println(this.toString() + " enters pool");
        try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
        poolManagement.takeBasket();
        try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}

        poolManagement.enterWardrobe();
        try {Thread.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}

        poolManagement.leaveWardrobe();

        System.out.println(name+" swimming");
        try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();}

        poolManagement.enterWardrobe();
        try {Thread.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}

        poolManagement.leaveWardrobe();
        try {Thread.sleep(50);} catch (InterruptedException e) {e.printStackTrace();}

        poolManagement.loseBasket();

        System.out.println(name + " leave pool");
    }
}

class PoolManagement{
    String swimmerName;
    int basketCount = 3;
    int wardrobeCount = 2;

    public synchronized void takeBasket(){
        if(basketCount == 0){
            System.out.printf("\rno more free baskets %s is waiting\n", Thread.currentThread().getName());
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        basketCount--;
        System.out.printf("basket is taken by %s, baskets left %d", Thread.currentThread().getName(), basketCount);
    }

    public synchronized void loseBasket(){
        notifyAll();
        basketCount++;
        System.out.printf("\r\rbasket is lose by %s. There are %d free baskets\n", Thread.currentThread().getName(), basketCount);
    }

    public synchronized void enterWardrobe(){
        if(wardrobeCount == 0){
            System.out.printf("\rno more free wardrobes %s is waiting\n", Thread.currentThread().getName());
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        wardrobeCount--;
        System.out.printf("%s entered wardrobe, capacity left %d", Thread.currentThread().getName(), wardrobeCount);
    }
    public synchronized void leaveWardrobe(){
        notifyAll();
        wardrobeCount++;
        System.out.printf("\r\r\r %s left wardrobe. There are %d free wardrobes\n", Thread.currentThread().getName(), wardrobeCount);
    }
}