package ExamWorkshop.vechiclesExercises.CarsOnBridge.Vehicles;

import ExamWorkshop.vechiclesExercises.CarsOnBridge.Bridge;
import ExamWorkshop.vechiclesExercises.CarsOnBridge.Direction;

public abstract class Vehicle implements IVehicle,Runnable{
    String name;
    Direction direction;
    Bridge bridge;
    Integer velocity;
    VehicleType vehicleType;

    private Boolean isLeavingBridge;
    private double currentPosition = 0;

    public Vehicle(Bridge bridge, int consecutiveNumber, int velocity , VehicleType vehicleType){

        boolean isEven = consecutiveNumber % 2 == 0;
        String stringDirection = isEven ? "<-----     |" : "    ------>|";

        this.name = stringDirection + "Car "+ consecutiveNumber;
        this.direction = isEven ? Direction.LEFT : Direction.RIGHT;
        this.bridge = bridge;
        this.velocity = 1000 * velocity / 3600; // convert to m/s
        this.vehicleType = vehicleType;
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
    public boolean isLeavingBridge(){
        return isLeavingBridge;
    }
    public void setLeavingBridge(boolean isLeavingBridge){
        this.isLeavingBridge = isLeavingBridge;
    }
    public Direction getDirection(){
        return direction;
    }
    public void setDirectionn(Direction direction){
        this.direction = direction;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public VehicleType getType(){
      return vehicleType;
    }

    @Override
    public void run() {

        try {
            // travel on road
//            int DRIVING_TIME = 500;
//            for (int i = 1; i <= 5; i++) {
//                System.out.println("Travelling on road..." +i);
//                System.out.println("position : " +setPosition() + "m");
//                Thread.sleep(DRIVING_TIME);
//            }
//
//            // gets on bridge
//            bridge.takeBridge(this);
//
//            // travel on bridge
//            for (int i = 1; i <= 5; i++) {
//                System.out.printf("%s is travelling on bridge...\n", name);
//                System.out.println("position : " +setPosition()+ "m");
//                Thread.sleep(DRIVING_TIME);
//            }
//
//            // leave bridge
//            bridge.leaveBridge(this);
//
//            // finish road
//            System.out.println("Leaving the road and reaching destination...");
//            System.out.println("Final position : " +getPosition()+ "m");

            // start new thread to measure speed
             bridge.takeRoad(this);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
