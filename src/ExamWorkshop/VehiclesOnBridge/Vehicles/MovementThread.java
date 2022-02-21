package ExamWorkshop.VehiclesOnBridge.Vehicles;

import ExamWorkshop.VehiclesOnBridge.Helpers.ExceptionLogger;
import ExamWorkshop.VehiclesOnBridge.Vehicles.VehicleTypes.VehicleType;

public class MovementThread implements Runnable{

    private final double totalRoadLength; // total length road + bridge in meters
    private final double roadLength; // road length in meters
    private final double bridgeLength; //60% from total road length 60/100=0.6
    private Vehicle vehicle;
    private final double velocity; // speed in meters per second formula- 5/18 * km/h, 5/18 = 13.88
    private int vehiclePositionOnRoad;

    /**
     * Shows vehicle position across the road in lifetime
     * @param velocity
     * @param totalRoadLength
     * @param vehicle vehicle to which it is attached the concrete movement thread
     */
    public MovementThread(double velocity, double totalRoadLength, Vehicle vehicle){
        this.velocity = velocity;
        this.totalRoadLength = totalRoadLength;
        this.bridgeLength = 0.6 * totalRoadLength;
        this.roadLength = totalRoadLength - bridgeLength;
        this.vehicle = vehicle;
    }

    /**
     * Check if vehicle is certain distance near to bridge
     * @return true if is and false if it's too far from the bridge
     */
    public boolean isCloseToBridge(int distanceToBridge){
        return roadLength/2 - vehiclePositionOnRoad <= distanceToBridge;
    }
    public boolean isReadyToLeaveBridge(){
        return vehiclePositionOnRoad >= bridgeLength;
    }
    public boolean hasLeftRoad(){
        return vehiclePositionOnRoad >= totalRoadLength;
    }
    public int getPosition(){
        return vehiclePositionOnRoad;
    }
    public void setPosition(){
        vehiclePositionOnRoad+=velocity;
    }
    private void carSetPriority(int priority){
        if(vehicle.getType() == VehicleType.CAR) {
            vehicle.thread.setPriority(priority);
        }
    }
    public synchronized void waitThread() throws InterruptedException {
        vehicle.isMovementPaused = true;
        while (vehicle.isMovementPaused){
            Thread.sleep(500);
        }
    }

    @Override
    public void run(){
        Thread.currentThread().setName("Road Thread: " + vehicle.getName());

        double halfRoad = roadLength/2;
            while (vehiclePositionOnRoad < totalRoadLength) {
                try {
                    if (vehiclePositionOnRoad < halfRoad) { // on road
                        setPosition();
                    } else if (vehiclePositionOnRoad == halfRoad) { // wait to get on bridge
                        carSetPriority(6);
                        waitThread();
                        setPosition();
                    } else if (vehiclePositionOnRoad < bridgeLength) { // on the bridge
                        carSetPriority(5);
                        setPosition();
                    } else if (vehiclePositionOnRoad == bridgeLength) { // wait to leave the bridge
                        carSetPriority(6);
                        waitThread();
                        setPosition();
                    } else { // leave bridge
                        setPosition();
                    }
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    ExceptionLogger.log(e);
                }
        }
    }
}
