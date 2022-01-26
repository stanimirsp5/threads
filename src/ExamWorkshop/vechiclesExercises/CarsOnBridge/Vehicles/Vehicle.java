package ExamWorkshop.vechiclesExercises.CarsOnBridge.Vehicles;

import ExamWorkshop.vechiclesExercises.CarsOnBridge.Bridge;
import ExamWorkshop.vechiclesExercises.CarsOnBridge.Direction;

import java.util.ArrayList;

public abstract class Vehicle implements IVehicle,Runnable{
    String name;
    private Direction direction;
    Bridge bridge;
    Integer velocity;
    VehicleType vehicleType;

    private Boolean isLeavingBridge = false;
    private double currentPosition = 300;
    public static ArrayList<Ambulance> ambulances = new ArrayList<>();
    public static ArrayList<Firetruck> firetrucks = new ArrayList<>();

    public Vehicle(Bridge bridge, int consecutiveNumber, int velocity , VehicleType vehicleType){

        boolean isEven = consecutiveNumber % 2 == 0;
        String stringDirection = isEven ? "<-----     |" : "    ------>|";

        this.name = stringDirection + "Car "+ consecutiveNumber;
        if (isEven) {
            setDirection(Direction.LEFT);
        } else {
            setDirection(Direction.RIGHT);
        }
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
    public void setDirection(Direction direction){
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

    public void leaveBridge(){
        System.out.println("Not here");
    }


        @Override
    public void run() {

        try {

            switch (getType()){
                case AMBULANCE:
                    ambulances.add((Ambulance) this);
                    break;
                case FIRETRUCK:
                    firetrucks.add((Firetruck) this);
                    break;
            }

            bridge.takeRoad(this);
            Thread.sleep(1000);
            bridge.leaveBridge(this);

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
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
