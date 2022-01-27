package ExamWorkshop.vechiclesExercises.CarsOnBridge.Vehicles;

import ExamWorkshop.vechiclesExercises.CarsOnBridge.Bridge;

public class Firetruck extends Vehicle{
    private boolean hasCarWithFiretruck;
    public Firetruck(Bridge bridge, int consecutiveNumber, int velocity, VehicleType vehicleType) {
        super(bridge, consecutiveNumber, velocity, vehicleType);
    }

    public boolean hasCarWithFiretruck(){
        return hasCarWithFiretruck;
    }
    public void setHasCarWithFiretruck(boolean hasCarWithFiretruck){
        this.hasCarWithFiretruck = hasCarWithFiretruck;
    }

    /**
     * Check if there is firetruck on the bridge
     * @return true if there are any or false if ain't
     */
    public static boolean hasFiretruck(){
        return firetrucks.size() > 0;
    }

    /**
     * Get firetruck on the bridge without car escorting it
     * @return Vehicle.Firetruck (Always returns firetruck. First if there is firetruck without car otherwise last firetruck in collection)
     */
    public static Firetruck getFiretruck(){
        for (Firetruck firetruck : firetrucks) {
            if(!firetruck.hasCarWithFiretruck()){
                return firetruck;
            }
        }
        return firetrucks.get(firetrucks.size()-1); // return default
    }

    @Override
    public void leaveBridge(){
        setHasCarWithFiretruck(false);
        firetrucks.remove(this);
    }
}
