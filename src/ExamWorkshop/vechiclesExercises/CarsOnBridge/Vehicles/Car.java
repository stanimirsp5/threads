package ExamWorkshop.vechiclesExercises.CarsOnBridge.Vehicles;

import ExamWorkshop.vechiclesExercises.CarsOnBridge.Bridge;

public class Car extends Vehicle{

    public String carWaitingMessage;

    public Car(Bridge bridge, int consecutiveNumber, int velocity, VehicleType vehicleType) {
        super(bridge, consecutiveNumber, velocity, vehicleType);
    }
}
