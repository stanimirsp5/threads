package ExamWorkshop.vechiclesExercises.CarsOnBridge;

import ExamWorkshop.vechiclesExercises.CarsOnBridge.Vehicles.IVehicle;
import ExamWorkshop.vechiclesExercises.CarsOnBridge.Vehicles.VehicleFactory;

public class Main {
    final static int NUM_CARS = 3;
    final static int ROAD_LENGTH = 5000;

    public static void main(String[] args){
        Bridge bridge = new Bridge(ROAD_LENGTH);
        for(int i = 0; i < NUM_CARS; i++){

            IVehicle vehicle = VehicleFactory.createVehicle(bridge,i, 50);
            new Thread((Runnable) vehicle).start();
        }


    }

    // create bridge

    // create and run cars (threads)


}
