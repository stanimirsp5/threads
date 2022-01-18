package ExamWorkshop.vechiclesExercises.CarsOnBridge;

import ExamWorkshop.vechiclesExercises.Stopwatch.Stopwatch;

public class Car implements Runnable{

    String name;
    Direction direction;
    Bridge bridge;
    Integer velocity = 90;
    Stopwatch stopwatch;
    Integer roadLength = 1000; // meters, bridge takes 60% from the road, other 20% are equally distributed

    public Car(Bridge bridge, int consecutiveNumber){

        boolean isEven = consecutiveNumber % 2 == 0;
        String stringDirection = isEven ? "<-----     |" : "    ------>|";

        this.name = stringDirection + "Car "+ consecutiveNumber;
        this.direction = isEven ? Direction.LEFT : Direction.RIGHT;
        this.bridge = bridge;

        this.velocity = 1000 * velocity / 3600; // convert to m/s
        stopwatch = new Stopwatch();
        stopwatch.start();
    }

    public double getPosition(){ // return position on road im meters
        double distance = getCarDrivingTime() * velocity;
        return distance*5;
    }

    public long getCarDrivingTime(){ // calculate car travel time in seconds
        return stopwatch.getTime();
    }

    @Override
    public void run() {

        try {
            // travel on road
            for (int i = 0; i < 5; i++) {
                System.out.println("Travelling on road...");
                System.out.println("position : " +getPosition() + "m");
                Thread.sleep(1000);
            }

            // gets on bridge
            bridge.takeBridge(this);

            // travel on bridge
            for (int i = 0; i < 5; i++) {
                System.out.printf("%s is travelling on bridge...\n", name);
                System.out.println("position : " +getPosition()+ "m");
                Thread.sleep(1000);
            }

            // leave bridge
            bridge.leaveBridge(this);

            // finish road
                System.out.println("Leaving the road and reaching destination...");
                Thread.sleep(1000);
                System.out.println("position : " +getPosition()+ "m");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
