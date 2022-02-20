package ExamWorkshop.CarsOnBridge.Vehicles.VehicleTypes;

import ExamWorkshop.CarsOnBridge.Bridge.Bridge;
import ExamWorkshop.CarsOnBridge.Vehicles.Vehicle;

public class Car extends Vehicle {

    public String carWaitingMessage;

    public Car(Bridge bridge, int consecutiveNumber, int velocity, VehicleType vehicleType) {
        super(bridge, consecutiveNumber, velocity, vehicleType);
    }
}
