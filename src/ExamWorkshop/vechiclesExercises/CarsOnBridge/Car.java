package ExamWorkshop.vechiclesExercises.CarsOnBridge;

public class Car implements ICar,Runnable{
    String name;
    Direction direction;
    Bridge bridge;
    Integer velocity = 90;

    private double currentPosition = 0;

    public Car(Bridge bridge, int consecutiveNumber){

        boolean isEven = consecutiveNumber % 2 == 0;
        String stringDirection = isEven ? "<-----     |" : "    ------>|";

        this.name = stringDirection + "Car "+ consecutiveNumber;
        this.direction = isEven ? Direction.LEFT : Direction.RIGHT;
        this.bridge = bridge;

        this.velocity = 1000 * velocity / 3600; // convert to m/s
    }

    public double getPosition(){
        return currentPosition;
    }

    public double setPosition(){
        int LOOP_ITERATIONS = 10;
        int timeNeededToCoverDistance = (bridge.roadLength / velocity) / LOOP_ITERATIONS;

        double distance = currentPosition + (timeNeededToCoverDistance * velocity);
        currentPosition = distance;
        return distance;
    }

    @Override
    public void run() {

        try {
            // travel on road
            int DRIVING_TIME = 500;
            for (int i = 1; i <= 5; i++) {
                System.out.println("Travelling on road..." +i);
                System.out.println("position : " +setPosition() + "m");
                Thread.sleep(DRIVING_TIME);
            }
            //System.out.println("position : " +getPosition() + "m");

            // gets on bridge
//            bridge.takeBridge(this);
//
//            // travel on bridge
            for (int i = 1; i <= 5; i++) {
                System.out.printf("%s is travelling on bridge...\n", name);
                System.out.println("position : " +setPosition()+ "m");
                Thread.sleep(DRIVING_TIME);
            }
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
