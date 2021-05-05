package ExamWorkshop.InspectorsOnBridge;

import ExamWorkshop.InspectorsOnBridge.Bridge.Bridge;
import ExamWorkshop.InspectorsOnBridge.Bridge.Car;
import ExamWorkshop.InspectorsOnBridge.Bridge.Direction;
import ExamWorkshop.InspectorsOnBridge.Gui.MainGui;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static final int NUM_CARS = 10;
    public static final int NUM_THREADS = 10;
    public static void main(String[] args) throws InterruptedException {

        Bridge bridge = new Bridge();

        ExecutorService pool = Executors.newFixedThreadPool(NUM_THREADS);
        Car[] cars = new Car[NUM_CARS];
        for (int i = 0; i < NUM_CARS; i++) {
            Direction direction = i % 2 == 0 ? Direction.Left : Direction.Right;
            cars[i] = new Car(bridge, "Car " + i, direction);
        }

        //while (true) {
            for (int i = 0; i < NUM_CARS; i++) {
                pool.execute(cars[i]);
            }
           // System.out.println("====== sleep for 5 seconds ========");
//            Thread.sleep(5000);
//        }

        pool.shutdown();

    }


}
