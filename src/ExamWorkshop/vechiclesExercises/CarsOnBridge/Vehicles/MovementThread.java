package ExamWorkshop.vechiclesExercises.CarsOnBridge.Vehicles;

public class MovementThread implements Runnable{

    private final double totalRoadLength; // total length road + bridge in meters
    private final double roadLength; // road length in meters
    private final double bridgeLength; //60% from total road length 60/100=0.6
    private Vehicle vehicle;
    private final double velocity; // speed in meters per second formula- 5/18 * km/h, 5/18 = 13.88
    //private final double totalTimeOnBridge;
    private int vehiclePositionOnRoad;
    public static boolean isWaiting;
    public boolean isWaiting3;

    public MovementThread(double velocity, double totalRoadLength, Vehicle vehicle){
        this.velocity = 13.88 * velocity; // convert from km/h to m/s
        this.totalRoadLength = totalRoadLength;
        this.bridgeLength = 0.6 * totalRoadLength;
        this.roadLength = totalRoadLength - bridgeLength;

        this.vehicle = vehicle;
      //  totalTimeOnBridge = calculateOptimalDrivingTimeForVehicle();
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
        vehiclePositionOnRoad+=100;
    }

    /**
     * Calculate how many seconds will take for vehicle to travel
     * some distance. Because original time is too large and will take much more time
     * for program to complete. Time is significantly reduced to 0.7% from the original answer.
     * @return optimal driving time for vehicle
     */
    private double calculateOptimalDrivingTimeForVehicle(){
        double timeInSeconds = totalRoadLength / velocity; // time in seconds
        double optimalTime = (0.7/100) * timeInSeconds;
        return optimalTime;
    }

    private void carSetPriority(int priority){
        if(vehicle.getType() == VehicleType.CAR) {
            vehicle.thread.setPriority(priority);
        }
    }

    public synchronized void waitThread() throws InterruptedException {
        vehicle.isMovementPaused = true;
        while (vehicle.isMovementPaused){
            Thread.sleep(100);
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
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
    }
}
