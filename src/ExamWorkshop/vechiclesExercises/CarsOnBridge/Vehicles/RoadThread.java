package ExamWorkshop.vechiclesExercises.CarsOnBridge.Vehicles;

//public class RoadThread extends Thread{
public class RoadThread implements Runnable{

    private final int totalRoadLength; // total length road + bridge in meters
    private final int roadLength; // road length in meters
    private final int bridgeLength; //60% from total road length
    private Vehicle vehicle;
    private final int velocity; // speed in meters per second
    private final double totalTimeOnBridge;
    private int vehiclePositionOnRoad;

    public RoadThread(int velocity, int totalRoadLength, Vehicle vehicle){
        this.velocity = (5/18) * velocity; // convert from km/h to m/s
        this.totalRoadLength = totalRoadLength;
        this.bridgeLength = (60/100) * totalRoadLength;
        this.roadLength = totalRoadLength - bridgeLength;

        this.vehicle = vehicle;
        totalTimeOnBridge = calculateOptimalDrivingTimeForVehicle();
    }

    /**
     * Check if vehicle is 400m near to bridge
     * @return true if 400 and less close to bridge and false if its too far from the bridge
     */
    public boolean isCloseToBridge(){
        return roadLength/2 - vehiclePositionOnRoad >= 400;
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
        int timeInSeconds = totalRoadLength / velocity; // time in seconds
        double optimalTime = (0.7/100) * timeInSeconds;
        return optimalTime;
    }

    private void carSetPriority(int priority){
        if(vehicle.getType() == VehicleType.CAR) {
            Thread t = new Thread(vehicle);// todo what this doing?
            String n = t.getName();
            t.setPriority(priority);

        }
    }

    @Override
    public void run(){
        long singleTime = (long) (totalTimeOnBridge/6);

        int halfRoad = roadLength/2;
        while (vehiclePositionOnRoad == totalRoadLength){
            try {
                if(vehiclePositionOnRoad < halfRoad){ // on road
                    setPosition();
                }else if (vehiclePositionOnRoad == halfRoad){ // wait to get on bridge
                    carSetPriority(6);
                    wait();
                    setPosition();
                }else if(vehiclePositionOnRoad < bridgeLength){ // on the bridge
                    carSetPriority(5);
                    setPosition();
                }else if(vehiclePositionOnRoad == bridgeLength){ // wait to leave the bridge
                    carSetPriority(6);
                    wait();
                    setPosition();
                }else { // leave bridge
                    setPosition();
                }
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
