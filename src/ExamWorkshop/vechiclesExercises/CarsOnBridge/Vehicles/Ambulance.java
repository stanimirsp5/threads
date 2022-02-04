package ExamWorkshop.vechiclesExercises.CarsOnBridge.Vehicles;

import ExamWorkshop.vechiclesExercises.CarsOnBridge.Bridge;

import java.util.ArrayList;

public class Ambulance extends Vehicle{
    public static ArrayList<Ambulance> ambulances = new ArrayList<>();

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
        System.out.printf("%s is on the bridge. (%d m) \n", this.getName(), this.movementThread.getPosition());
    }

    @Override
    public void leaveBridge(){
        ambulances.remove(this);
    }

}
