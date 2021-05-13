package ExamWorkshop.InspectorsOnBridge;

import ExamWorkshop.InspectorsOnBridge.Bridge.Bridge;
import ExamWorkshop.InspectorsOnBridge.Bridge.Car;
import ExamWorkshop.InspectorsOnBridge.Bridge.Direction;
import ExamWorkshop.InspectorsOnBridge.Chat.Inspector;
import ExamWorkshop.InspectorsOnBridge.Chat.Server;
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

    public static final int NUM_CARS = 60;
    public static final int NUM_THREADS = 2;
    public static void main(String[] args) throws InterruptedException {

        //Application.launch(MainGui.class, args);
        //new Thread(() -> Application.launch(MainGui.class, args)).start();

          new Thread(() -> Server.main(args)).start();
          new Thread(() -> Inspector.main(args)).start();
          new Thread(() -> Inspector.main(args)).start();

//        Bridge bridge = new Bridge();
//
//        ExecutorService pool = Executors.newFixedThreadPool(NUM_THREADS);
//        Car[] cars = new Car[NUM_CARS];
//        for (int i = 0; i < NUM_CARS; i++) {
//            Direction direction = i % 2 == 0 ? Direction.Left : Direction.Right;
//            cars[i] = new Car(bridge, "Car " + i, direction, i);
//        }
//
//        for (int i = 0; i < NUM_CARS; i++) {
//            pool.execute(cars[i]);
//        }
//
//        pool.shutdown();

    }
}
