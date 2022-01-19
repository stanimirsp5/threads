package ExamWorkshop.vechiclesExercises.CarsOnBridge;

import ExamWorkshop.vechiclesExercises.Stopwatch.Stopwatch;

public class Car implements Runnable{

    String name;
    Direction direction;
    Bridge bridge;
    Integer velocity = 90;
    Stopwatch stopwatch;
    double oldV = 0;
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
        //long t = getCarDrivingTime(); // boost timing to get right result
        // find needed time to cover road distance
        // d = 1000m, v = 90km/h or 25 m/s, t = d/v
        // time = 1000/25 = 40seconds needed to cover distance
        // program has 5 iterations in loop so 40/5 = 8s. If you drive with 90km/h (25m/s) you need 8s to cover 200m
        double distance = oldV + (8 * velocity);
        oldV = distance;
        return distance;
    }

    public long getCarDrivingTime(){ // calculate car travel time in seconds
        return stopwatch.getTimeInSeconds();
    }

    @Override
    public void run() {

        try {
            // travel on road
            for (int i = 1; i <= 5; i++) {
                System.out.println("Travelling on road..." +i);
                System.out.println("position : " +getPosition() + "m");
                System.out.println("getCarDrivingTime() : " +getCarDrivingTime());
                Thread.sleep(500);
            }
            //System.out.println("position : " +getPosition() + "m");

            // gets on bridge
//            bridge.takeBridge(this);
//
//            // travel on bridge
//            for (int i = 0; i < 5; i++) {
//                System.out.printf("%s is travelling on bridge...\n", name);
//                System.out.println("position : " +getPosition()+ "m");
//                Thread.sleep(500);
//            }
//
//            // leave bridge
//            bridge.leaveBridge(this);
//
//            // finish road
//                System.out.println("Leaving the road and reaching destination...");
//                Thread.sleep(500);
//                System.out.println("position : " +getPosition()+ "m");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
