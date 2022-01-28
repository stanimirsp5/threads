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
    public static void getAmbulances(){}

    @Override
    public void vehicleOnBridge(){
        ambulances.add(this);
        System.out.printf("%s is on the bridge \n", this.getName());
    }

    @Override
    public void leaveBridge(){
        ambulances.remove(this);
    }
}
