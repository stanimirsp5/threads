package ExamWorkshop.vechiclesExercises.CarsOnBridge.Vehicles;

import ExamWorkshop.vechiclesExercises.CarsOnBridge.Bridge;

public class VehicleFactory {
    /**
     * Create vehicle type.
     * Firetruck - if consecutiveNumber is divisible by 5 without remainder.
     * Ambulance - if consecutiveNumber is divisible by 7 without remainder.
     * Car - in all others cases.
     * @param bridge reference to the bridge on which the vehicle moves
     * @param consecutiveNumber the sequence of the vehicle in the loop
     * @param velocity car velocity
     * @return type of vehicle
     */
    public static Vehicle createVehicle(Bridge bridge, int consecutiveNumber, int velocity){

        if (consecutiveNumber % 3 == 0) {
            return new Firetruck(bridge, consecutiveNumber, velocity, VehicleType.FIRETRUCK);
        }else if(consecutiveNumber % 5 == 0) {
            return new Ambulance(bridge, consecutiveNumber, velocity, VehicleType.AMBULANCE);
        }else{
            return new Car(bridge, consecutiveNumber, velocity, VehicleType.CAR);
        }
    }
}
