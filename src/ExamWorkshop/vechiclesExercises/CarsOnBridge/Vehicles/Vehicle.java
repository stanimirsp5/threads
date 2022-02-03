package ExamWorkshop.vechiclesExercises.CarsOnBridge.Vehicles;

import ExamWorkshop.vechiclesExercises.CarsOnBridge.Bridge;
import ExamWorkshop.vechiclesExercises.CarsOnBridge.Direction;

import java.util.ArrayList;

public abstract class Vehicle implements IVehicle,Runnable{
    private String name;
    private Direction direction;
    Bridge bridge;
    Integer velocity;
    VehicleType vehicleType;
    public RoadThread roadThread;
    public boolean isRoadWaiting;

    private Boolean isLeavingBridge = false;
    public static ArrayList<Ambulance> ambulances = new ArrayList<>();
    public static ArrayList<Firetruck> firetrucks = new ArrayList<>(); // todo concurrency

    public Vehicle(Bridge bridge, int consecutiveNumber, int velocity , VehicleType vehicleType){

        boolean isEven = consecutiveNumber % 2 == 0;
        String stringDirection = isEven ? "<-----     |" : "    ------>|";

        this.name = String.format("%s %s %d",stringDirection, vehicleType.name(), consecutiveNumber);
        if (isEven) {
            setDirection(Direction.LEFT);
        } else {
            setDirection(Direction.RIGHT);
        }
        this.bridge = bridge;
        this.velocity = velocity;//1000 * velocity / 3600; // convert to m/s
        this.vehicleType = vehicleType;
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
       // System.out.println("Not here");
    }
    public void vehicleOnBridge(){
        System.out.printf("%s is on the bridge. (%d m) \n", this.getName(), this.roadThread.getPosition());
    }


    @Override
    public void run() {
        Thread.currentThread().setName("Vehicle Thread: " + this.getName());

        try {
            roadThread = new RoadThread(velocity, bridge.roadLength, this);
            new Thread(roadThread).start();

            bridge.takeRoad(this);
            System.out.printf("%s travelling on the bridge. (%d m) \n",this.getName(), this.roadThread.getPosition());
            while (!roadThread.isReadyToLeaveBridge() || !this.isRoadWaiting){
                Thread.sleep(200);
            }
            bridge.leaveBridge(this);
            while (!roadThread.hasLeftRoad()){
                Thread.sleep(200);
            }
            bridge.leaveRoad(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
