package ExamWorkshop.CarsOnBridge.Vehicles;

import ExamWorkshop.CarsOnBridge.Bridge.Bridge;
import ExamWorkshop.CarsOnBridge.Bridge.Direction;
import ExamWorkshop.CarsOnBridge.Helpers.ExceptionLogger;
import ExamWorkshop.CarsOnBridge.Vehicles.VehicleTypes.VehicleType;

public abstract class Vehicle implements IVehicle,Runnable{
    private String name;
    private Direction direction;
    Bridge bridge;
    Integer velocity;
    VehicleType vehicleType;
    public MovementThread movementThread;
    /**
     * If true vehicle movement thread has reached some checkpoint across the road
     * and movement thread of the vehicle must wait vehicle
     */
    public boolean isMovementPaused; // if movement is paused the car is ready to take next section of the road
    public Thread thread;

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
    public void leaveBridge() {
        System.out.printf("%s left the bridge. (%d m) \n", this.getName(), this.movementThread.getPosition());
    }

    @Override
    public void run() {
        this.setThread(Thread.currentThread());
        Thread.currentThread().setName("Vehicle Thread: " + this.getName());
        //if(this.getType() == VehicleType.AMBULANCE) Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        //System.out.printf("%s is on the road.\n", this.getName());

        try {
            movementThread = new MovementThread(velocity, bridge.roadLength, this);
            new Thread(movementThread).start();

            bridge.takeRoad(this);
            while (!movementThread.isReadyToLeaveBridge() || !this.isMovementPaused){
                Thread.sleep(200);
            }
            bridge.leaveBridge(this);
            while (!movementThread.hasLeftRoad()){
                Thread.sleep(200);
            }
            bridge.leaveRoad(this);
        } catch (InterruptedException e) {
            ExceptionLogger.log(e);
        }
    }
}
