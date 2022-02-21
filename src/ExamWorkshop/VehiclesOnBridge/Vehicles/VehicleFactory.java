package ExamWorkshop.VehiclesOnBridge.Vehicles;

import ExamWorkshop.VehiclesOnBridge.Bridge.Bridge;
import ExamWorkshop.VehiclesOnBridge.Vehicles.VehicleTypes.Ambulance;
import ExamWorkshop.VehiclesOnBridge.Vehicles.VehicleTypes.Car;
import ExamWorkshop.VehiclesOnBridge.Vehicles.VehicleTypes.Firetruck;
import ExamWorkshop.VehiclesOnBridge.Vehicles.VehicleTypes.VehicleType;

public class VehicleFactory {
    /**
     * Create vehicle type.
     * Firetruck - if consecutiveNumber is divisible by 3 without remainder.
     * Ambulance - if consecutiveNumber is divisible by 4 without remainder.
     * Car - in all others cases.
     * @param bridge reference to the bridge on which the vehicle moves
     * @param consecutiveNumber the sequence of the vehicle in the loop
     * @return type of vehicle
     */
    public static Vehicle createVehicle(Bridge bridge, int consecutiveNumber){
        int velocity = 100;
        if (consecutiveNumber % 3 == 0) {
            return new Firetruck(bridge, consecutiveNumber, velocity, VehicleType.FIRETRUCK);
        }else if(consecutiveNumber % 4 == 0) {
            return new Ambulance(bridge, consecutiveNumber, velocity, VehicleType.AMBULANCE);
        }else{
            return new Car(bridge, consecutiveNumber, velocity, VehicleType.CAR);
        }
    }
}
