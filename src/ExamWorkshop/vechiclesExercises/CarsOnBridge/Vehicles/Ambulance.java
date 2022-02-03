package ExamWorkshop.vechiclesExercises.CarsOnBridge.Vehicles;

import ExamWorkshop.vechiclesExercises.CarsOnBridge.Bridge;

public class Ambulance extends Vehicle{
    public Ambulance(Bridge bridge, int consecutiveNumber, int velocity, VehicleType vehicleType) {
        super(bridge, consecutiveNumber, velocity, vehicleType);

    }

    /**
     * Check if there is ambulance on the bridge
     * @return true if there are any or false if ain't
     */
    @ThreadSafe
    public synchronized static boolean hasAmbulance(){
        return ambulances.size() > 0;
    }

    @Override
    public void vehicleOnBridge(){
        ambulances.add(this);
        System.out.printf("%s is on the bridge. (%d m) \n", this.getName(), this.roadThread.getPosition());
    }

    @Override
    public void leaveBridge(){
        ambulances.remove(this);
    }

    @Override
    public void run() {
        Thread.currentThread().setName("Vehicle Thread: " + this.getName());

        try {
            roadThread = new RoadThread(velocity, bridge.roadLength, this);
            new Thread(roadThread).start();

            Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
            bridge.takeRoad(this);
            System.out.printf("%s travelling on the bridge. (%d m) \n",this.getName(), this.roadThread.getPosition());
            while (!roadThread.isReadyToLeaveBridge() || !this.isRoadWaiting) {
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
