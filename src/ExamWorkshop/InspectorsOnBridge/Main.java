package ExamWorkshop.InspectorsOnBridge;

import ExamWorkshop.InspectorsOnBridge.Bridge.Bridge;
import ExamWorkshop.InspectorsOnBridge.Bridge.Car;
import ExamWorkshop.InspectorsOnBridge.Bridge.Direction;
import ExamWorkshop.InspectorsOnBridge.Bridge.StateContainer;
import ExamWorkshop.InspectorsOnBridge.Chat.Inspector;
import ExamWorkshop.InspectorsOnBridge.Chat.Server;
import ExamWorkshop.InspectorsOnBridge.Gui.MainGui;
import javafx.application.Application;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main{

    public static final int NUM_CARS = 100;
    public static final int NUM_THREADS = 2;
    public static final int NUM_INSPECTORS  = 2;
    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> Application.launch(MainGui.class, args)).start();
        Thread.sleep(2000);

        Bridge bridge = new Bridge();
        StateContainer.getInstance();
        StateContainer.setBridge(bridge);

        initServerAndClients();

        ExecutorService pool = Executors.newFixedThreadPool(NUM_THREADS);
        Car[] cars = new Car[NUM_CARS];

        for (int i = 0; i < NUM_CARS; i++) {
            Direction direction = i % 2 == 0 ? Direction.Left : Direction.Right;
            cars[i] = new Car(bridge, "Car " + i, direction, i);
        }

        for (int i = 0; i < NUM_CARS; i++) {
            pool.execute(cars[i]);
        }

        pool.shutdown();

    }

    public static void initServerAndClients() throws InterruptedException {

        Server server = new Server();
        new Thread(server::runServer).start();
        Thread.sleep(1000); // TODO async java

        for (int i = 0; i < NUM_INSPECTORS; i++) {
            Inspector inspector = new Inspector(i+1);
            new Thread(inspector::createInspector).start();
        }

//        Inspector inspector = new Inspector(1);
//        new Thread(inspector::createInspector).start();
    }
}
