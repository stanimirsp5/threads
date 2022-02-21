package ExamWorkshop.VehiclesOnBridge.Vehicles.VehicleTypes;

import ExamWorkshop.VehiclesOnBridge.Bridge.Bridge;
import ExamWorkshop.VehiclesOnBridge.Vehicles.Vehicle;

public class Car extends Vehicle {

    public String carWaitingMessage;

    public Car(Bridge bridge, int consecutiveNumber, int velocity, VehicleType vehicleType) {
        super(bridge, consecutiveNumber, velocity, vehicleType);
    }
}
