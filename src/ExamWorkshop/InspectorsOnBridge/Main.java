package ExamWorkshop.InspectorsOnBridge;

import ExamWorkshop.InspectorsOnBridge.Bridge.Bridge;
import ExamWorkshop.InspectorsOnBridge.Bridge.Car;
import ExamWorkshop.InspectorsOnBridge.Bridge.Direction;
import ExamWorkshop.InspectorsOnBridge.Gui.BridgeGui;
import ExamWorkshop.InspectorsOnBridge.Gui.CarGui;
import ExamWorkshop.InspectorsOnBridge.Gui.MainGui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.security.cert.PolicyNode;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main{

    public static final int NUM_CARS = 20;
    public static final int NUM_THREADS = 10;
    public static void main(String[] args) throws InterruptedException {

        //Application.launch(MainGui.class, args);
        new Thread(() -> Application.launch(MainGui.class, args)).start();
//        Thread.sleep(2000);
//
        Bridge bridge = new Bridge();
//        CarGui carGui = new CarGui(Direction.Right,"test", 0);
//        Thread.sleep(5000);
//        carGui.runCar();
//
        //ExecutorService pool = Executors.newFixedThreadPool(NUM_THREADS);
        Car[] cars = new Car[NUM_CARS];
        for (int i = 0; i < NUM_CARS; i++) {
            Direction direction = i % 2 == 0 ? Direction.Left : Direction.Right;
            cars[i] = new Car(bridge, "Car " + i, direction, i);
        }

        for (int i = 0; i < NUM_CARS; i++) {
//            pool.execute(cars[i]);
            new Thread(cars[i]).start();
        }

        //pool.shutdown();

    }
}

//while (true) {
//            for (int i = 0; i < NUM_CARS; i++) {
//        pool.execute(cars[i]);
//        }
// System.out.println("====== sleep for 5 seconds ========");
//            Thread.sleep(5000);
//        }
