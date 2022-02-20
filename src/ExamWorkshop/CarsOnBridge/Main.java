package ExamWorkshop.CarsOnBridge;

import ExamWorkshop.CarsOnBridge.Bridge.Bridge;
import ExamWorkshop.CarsOnBridge.Inspectors.Inspector;
import ExamWorkshop.CarsOnBridge.Inspectors.InspectorChatServer;
import ExamWorkshop.CarsOnBridge.Vehicles.*;

import java.io.IOException;

public class Main {
    final static int NUM_CARS = 10;
    final static int ROAD_LENGTH = 5000;

    public static void main(String[] args) throws InterruptedException, IOException {

        Bridge bridge = new Bridge(ROAD_LENGTH);
        runChatServer();


        for(int i = 1; i <= NUM_CARS; i++){
            IVehicle vehicle = VehicleFactory.createVehicle(bridge,i);
            new Thread((Runnable) vehicle).start();
            Thread.sleep(3000);
        }

    }

    public static void runChatServer(){
        InspectorChatServer server = new InspectorChatServer();
        new Thread(server).start();
        Inspector inspector = new Inspector(1);
        new Thread(inspector).start();
        Inspector inspector2 = new Inspector(2);
        new Thread(inspector2).start();
    }

    //        new Thread(new Firetruck(bridge, 11, 50, VehicleType.FIRETRUCK)).start();
    //        new Thread(new Car(bridge, 3, 50, VehicleType.CAR)).start();
    //        new Thread(new Car(bridge, 1, 50, VehicleType.CAR)).start();
    //        new Thread(new Car(bridge, 5, 50, VehicleType.CAR)).start();
    //        new Thread(new Firetruck(bridge, 12, 50, VehicleType.FIRETRUCK)).start();
    //        new Thread(new Ambulance(bridge, 13, 50, VehicleType.AMBULANCE)).start();
    //        new Thread(new Ambulance(bridge, 14, 50, VehicleType.AMBULANCE)).start();
    //        ChatServer chatServer = new ChatServer();
    //        Thread t = new Thread(chatServer);
    //        t.start();
    //        while (true) {
    //            System.out.println("close bridge: ");
    //            Scanner userIn = new Scanner(System.in);
    //            if(userIn.nextInt() == 2) break;
    //            bridge.closeBridge();
    //            Thread.sleep(1000);
    //        }
}
