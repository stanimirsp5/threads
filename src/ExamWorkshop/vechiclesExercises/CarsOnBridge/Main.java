package ExamWorkshop.vechiclesExercises.CarsOnBridge;

import ExamWorkshop.vechiclesExercises.CarsOnBridge.Inspectors.ChatUi;
import ExamWorkshop.vechiclesExercises.CarsOnBridge.Inspectors.Inspector;
import ExamWorkshop.vechiclesExercises.CarsOnBridge.Inspectors.InspectorChatServer;
import ExamWorkshop.vechiclesExercises.CarsOnBridge.Vehicles.*;

import java.io.IOException;

public class Main {
    final static int NUM_CARS = 18;
    final static int ROAD_LENGTH = 5000;
    final static int PORT = 6666;

    public static void main(String[] args) throws InterruptedException, IOException {
//        Bridge bridge = new Bridge(ROAD_LENGTH);
        InspectorChatServer server = new InspectorChatServer();
        new Thread(server).start();
//        //new Thread(server::runServer).start();
        Inspector inspector = new Inspector(1);
        new Thread(inspector).start();
        Inspector inspector2 = new Inspector(2);
        new Thread(inspector2).start();

//        ChatUi chat = new ChatUi();
//        chat.start();

//       Bridge bridge1 = Bridge.getInstance();
//        for(int i = 1; i <= NUM_CARS; i++){
//            IVehicle vehicle = VehicleFactory.createVehicle(bridge,i);
//            new Thread((Runnable) vehicle).start();
//            Thread.sleep(3000);
//        }
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
}
