package threads.Synchronization.exercise;

public class Model {
    public static final int VEHICLE_NUM = 20;

    public static void main( String args[] ){

        Bridge b = new Bridge();
        for (int i = 0; i < VEHICLE_NUM; i++) {
            Direction direction = Math.random() > 0.5 ? Direction.LEFT : Direction.RIGHT;
            Vehicle vehicle = new Vehicle(b,direction);
            new Thread(vehicle).start();
        }

    }
}

class Vehicle implements Runnable {
    Bridge b;
    Direction direction;

    public Vehicle(Bridge b, Direction direction) {
        this.b = b;
        this.direction = direction;
    }

    @Override
    public void run() {
        b.takeBridge(direction);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        b.leaveBridge(direction);
    }
}

class Bridge {
    int vehiclesOnBridge = 0; // right > 0 > left

    synchronized public void takeBridge(Direction direction) {

        while (vehiclesOnBridge > 0 && direction == Direction.LEFT ||
                vehiclesOnBridge < 0 && direction == Direction.RIGHT){
            System.out.println("\t"+direction.name() + " Waiting Vehicle " + Thread.currentThread().getName());

            try{     wait();   }
            catch(InterruptedException e){
                System.err.println(e);
            }
        }

        if(direction == Direction.RIGHT) vehiclesOnBridge++;
        else vehiclesOnBridge--;

        System.out.println(direction.name() + " Vehicle " + Thread.currentThread().getName() + " take on the bridge");
        //System.out.println("Vehicle " + Thread.currentThread().getName() + " take on the bridge");
    }

    synchronized public void leaveBridge(Direction direction){
        if(vehiclesOnBridge > 0) vehiclesOnBridge--;
        else vehiclesOnBridge++;
        System.out.println("\t\t"+direction.name() + " Vehicle " + Thread.currentThread().getName() + " leave the bridge");
        notifyAll();

    }

}

enum Direction {
    LEFT,
    RIGHT
}