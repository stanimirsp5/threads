package ExamWorkshop.vechiclesExercises.CarsOnBridge;

public class Main {
    final static int NUM_CARS = 1;
    final static int ROAD_LENGTH = 1000;

    public static void main(String[] args){
        Bridge bridge = new Bridge(ROAD_LENGTH);

        for(int i = 0; i < NUM_CARS; i++){

            Car car = new Car(bridge,i);
            new Thread(car).start();
        }


    }

    // create bridge

    // create and run cars (threads)


}
