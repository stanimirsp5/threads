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
    public MovementThread movementThread;
    public boolean isMovementPaused;
    public Thread thread;
    private Boolean isLeavingBridge = false;

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
    public void setThread(Thread thread){
        this.thread = thread;
    }
    public VehicleType getType(){
      return vehicleType;
    }
    public void leaveBridge(){
       // System.out.println("Not here");
    }
    public void vehicleOnBridge(){
        System.out.printf("%s is on the bridge. (%d m) \n", this.getName(), this.movementThread.getPosition());
    }


    @Override
    public void run() {
        this.setThread(Thread.currentThread());
        Thread.currentThread().setName("Vehicle Thread: " + this.getName());
        if(this.getType() == VehicleType.AMBULANCE) Thread.currentThread().setPriority(Thread.MAX_PRIORITY);

        try {
            movementThread = new MovementThread(velocity, bridge.roadLength, this);
            new Thread(movementThread).start();

            bridge.takeRoad(this);
            System.out.printf("%s travelling on the bridge. (%d m) \n",this.getName(), this.movementThread.getPosition());
            while (!movementThread.isReadyToLeaveBridge() || !this.isMovementPaused){
                Thread.sleep(200);
            }
            bridge.leaveBridge(this);
            while (!movementThread.hasLeftRoad()){
                Thread.sleep(200);
            }
            bridge.leaveRoad(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
