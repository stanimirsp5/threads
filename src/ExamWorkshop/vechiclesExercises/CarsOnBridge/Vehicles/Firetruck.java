package ExamWorkshop.vechiclesExercises.CarsOnBridge.Vehicles;

import ExamWorkshop.vechiclesExercises.CarsOnBridge.Bridge;
import org.hamcrest.Factory;

import java.lang.annotation.Documented;

public class Firetruck extends Vehicle{

    private Vehicle car;

    public Firetruck(Bridge bridge, int consecutiveNumber, int velocity, VehicleType vehicleType) {
        super(bridge, consecutiveNumber, velocity, vehicleType);
    }

    /**
     * Check if there is car on the bridge with concrete firetruck.
     * For firetruck only one car is allowed
     * It must be synchronized so only one thread to access that info.
     * @return boolean
     */
    @ThreadSafe
    public synchronized boolean hasCarWithFiretruck(){
        return car != null;
    }

    /**
     * Firetruck keeps reference to car with it on the bridge.
     * Add to car's name firetruck name
     * @param car to be referenced to the concrete firetruck
     */
    public void addCarToFiretruck(Vehicle car){
        String addonName = String.format("%s ( %s )",car.getName(), this.getName());
        car.setName(addonName);
        this.car = car;
    }

    public void removeCarFromFiretruck(){
        this.car = null;
    }

    /**
     * Check if there is firetruck on the bridge
     * @return true if there are any or false if ain't
     */
    @ThreadSafe
    public synchronized static boolean hasFiretruck(){
        return firetrucks.size() > 0;
    }

    /**
     * Get firetruck on the bridge without car escorting it
     * @return Vehicle.Firetruck (Always returns firetruck. First if there is firetruck without car otherwise last firetruck in collection)
     */
    @ThreadSafe
    public synchronized static Firetruck getFiretruck(){
        for (Firetruck firetruck : firetrucks) {
            if(!firetruck.hasCarWithFiretruck()){
                return firetruck;
            }
        }
        return firetrucks.get(firetrucks.size()-1); // return default
    }

    @Override
    public void vehicleOnBridge(){
        firetrucks.add(this);
        System.out.printf("%s is on the bridge. (%d m) \n", this.getName(), this.roadThread.getPosition());
    }

    @Override
    public void leaveBridge(){
        removeCarFromFiretruck();
        firetrucks.remove(this);
    }
}
