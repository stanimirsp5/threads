package threads.Synchronization.exercise;

public class Model {
    public static final int VEHICLE_NUM = 20;
    enum Direction {
        LEFT,
        RIGHT
    }
    public static void main( String args[] ){

        Bridge b = new Bridge();
        for (int i = 0; i < VEHICLE_NUM; i++) {
            Direction direction = Math.random() > 0.5 ? Direction.LEFT : Direction.RIGHT;
            Vehicle vehicle = new Vehicle(b,direction);
            new Thread(vehicle).start();
        }

    }
}

class Vehicle implements Runnable{



    @Override
    public void run() {

    }
}

class Bridge {

    public void takeBridge(){

    }

    public void leaveBridge(){

    }

}

