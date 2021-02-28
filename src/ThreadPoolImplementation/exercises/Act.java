package ThreadPoolImplementation.exercises;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Act {
    public static final int NUM_CARS = 10;
    public static final int NUM_THREADS = 5;
    public static void main(String[] args){
        Bridge bridge = new Bridge();
        LinkedBlockingQueue queue = new LinkedBlockingQueue();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(NUM_THREADS,NUM_THREADS, 5000L, TimeUnit.MICROSECONDS, queue);

        for (int i = 0; i < NUM_CARS; i++) {
            Position position = i % 2 == 0 ? Position.LEFT : Position.RIGHT;
            threadPoolExecutor.execute(new Car("Car " + i, bridge, position));
        }
        threadPoolExecutor.shutdown();
    }
}
enum Position{
    LEFT,
    RIGHT
}
enum Direction{
    NONE,
    LEFT,
    RIGHT
}
class Car implements Runnable{
    String name;
    Bridge bridge;
    Position position;

    public Car(String name, Bridge bridge, Position position) {
        this.name = String.format("%s %s",name,position.name());
        this.bridge = bridge;
        this.position = position;
    }

    @Override
    public void run() {

        String destination = position==Position.LEFT ? " > Right" : " < Left ";

        System.out.println(name + " trying to take on bridge to destination " + destination);
        try { Thread.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}
        bridge.takeBridge(this);

        System.out.println(name + " travelling to destination " + destination);

        bridge.leaveBridge(this);

    }
}

class Bridge{
    Direction direction = Direction.NONE;
    int carsOnBridge = 0;
    int bridgeCapacity = 3;
    boolean isClosed;

    public synchronized void takeBridge(Car car){
        while (direction != Direction.NONE && direction.name().equals(car.position.name()) || isClosed){
            System.out.println(car.name + " is waiting");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        carsOnBridge++;
        direction = car.position == Position.LEFT ? Direction.RIGHT : Direction.LEFT;
        System.out.println(car.name + " is on the bridge. Direction " + direction);

        if(carsOnBridge >= bridgeCapacity){
            isClosed = true;
            System.out.println("Bridge is closed");
        }
    }

    public synchronized void leaveBridge(Car car){
        notifyAll();
        carsOnBridge--;
        if(carsOnBridge==0){
            direction = Direction.NONE;
        }
//        if(carsOnBridge >= bridgeCapacity){
//            isClosed = false;
//            System.out.println("Bridge is open");
//        }
        System.out.println(car.name + " left the bridge. Cars left on bridge " + carsOnBridge + " direction "+ direction.name());
        if(carsOnBridge==0){
            isClosed = false;
            System.out.println("Bridge is open");
        }
    }
}