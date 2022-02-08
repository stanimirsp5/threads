package ExamWorkshop.vechiclesExercises.CarsOnBridge.Vehicles;

import ExamWorkshop.vechiclesExercises.CarsOnBridge.Bridge;

import java.util.ArrayList;

public class Ambulance extends Vehicle implements ISpecialVehicle{
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

//    @ThreadSafe
//    public synchronized static boolean hasAmbulanceDrivingOnBridge(){
//        if(ambulances.size() == 0){
//            return false;
//        }else if(hasRunnableAmbulance()){
//            return true;
//        }
//    }

    @ThreadSafe
    public synchronized static boolean hasAmbulanceDrivingOnBridge(){
        for (Ambulance ambulance: ambulances) {
            if(ambulance.thread.getState() == Thread.State.RUNNABLE){
                return true;
            }
        }
        return false;
    }

    public void addSpecialVehicle(){
        ambulances.add(this);
    }

    @Override
    public void leaveBridge(){
        ambulances.remove(this);
        System.out.printf("%s left the bridge. (%d m)\n", this.getName(), this.movementThread.getPosition());
    }

}
