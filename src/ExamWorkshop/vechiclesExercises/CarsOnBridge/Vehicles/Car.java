package ExamWorkshop.vechiclesExercises.CarsOnBridge.Vehicles;

import ExamWorkshop.vechiclesExercises.CarsOnBridge.Bridge;

public class Car extends Vehicle{


    public Car(Bridge bridge, int consecutiveNumber, int velocity, VehicleType vehicleType) {
        super(bridge, consecutiveNumber, velocity, vehicleType);
    }
    @Override
    public void vehicleOnBridge(){
        System.out.printf("%s is on the bridge \n", this.getName());
    }

}
