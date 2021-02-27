package ThreadPoolImplementation.exercises;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Act {
    public static final int NUM_CARS = 6;
    public static void main(String[] args){
        Bridge bridge = new Bridge();
        LinkedBlockingQueue queue = new LinkedBlockingQueue();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(6,6, 5000L, TimeUnit.MICROSECONDS, queue);

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
        this.name = name;
        this.bridge = bridge;
        this.position = position;
    }

    @Override
    public void run() {

        String destination = position==Position.LEFT ? " > Right" : " < Left ";

        System.out.println(destination + " " + name + " trying to take on bridge. ");
        try { Thread.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}
        bridge.takeBridge(this);

        System.out.println(name + " is on bridge. Travel to destination " + destination);

        bridge.leaveBridge(this);

        System.out.println(name + " arrived at " + destination + " side of the bridge");

    }

    @Override
    public String toString(){
        return String.format("%s %s",name,position.name());
    }
}

class Bridge{
    Direction direction = Direction.NONE;
    //int isAtSameDirection = 0; // 1 left, 2 right
    int carsOnBridge = 0;
    public synchronized void takeBridge(Car car){
        if (direction != Direction.NONE && !direction.name().equals(car.position.name())){
            System.out.println(car.name + " is waiting");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        carsOnBridge++;
        direction = car.position == Position.LEFT ? Direction.RIGHT : Direction.RIGHT;
        System.out.println(car.name + " is on the bridge");
    }

    public synchronized void leaveBridge(Car car){
        notifyAll();
        carsOnBridge--;
        if(carsOnBridge==0){
            direction = Direction.NONE;
        }
        System.out.println(car.name + " left the bridge. Cars left on bridge " + carsOnBridge);
    }
}