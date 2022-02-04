package ExamWorkshop.vechiclesExercises.CarsOnBridge;

import ExamWorkshop.vechiclesExercises.CarsOnBridge.Vehicles.Firetruck;
import ExamWorkshop.vechiclesExercises.CarsOnBridge.Vehicles.IVehicle;
import ExamWorkshop.vechiclesExercises.CarsOnBridge.Vehicles.VehicleFactory;
import ExamWorkshop.vechiclesExercises.CarsOnBridge.Vehicles.VehicleType;

public class Main {
    final static int NUM_CARS = 10;
    final static int ROAD_LENGTH = 1000;

    public static void main(String[] args){
        Bridge bridge = new Bridge(ROAD_LENGTH);
        for(int i = 1; i <= NUM_CARS; i++){

            IVehicle vehicle = VehicleFactory.createVehicle(bridge,i, 50);
            new Thread((Runnable) vehicle).start();
//            Thread v = new Thread((Runnable) vehicle);
//            vehicle.setThread(v);
//            v.start();
        }


    }

    // create bridge

    // create and run cars (threads)


}
