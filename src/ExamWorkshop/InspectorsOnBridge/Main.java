package ExamWorkshop.InspectorsOnBridge;

import ExamWorkshop.InspectorsOnBridge.Bridge.Bridge;
import ExamWorkshop.InspectorsOnBridge.Bridge.Car;
import ExamWorkshop.InspectorsOnBridge.Bridge.Direction;

public class Main {
    public static void main(String[] args){

        Bridge bridge = new Bridge();

        for (int i = 0; i < 6; i++) {
            Direction direction = i % 2 == 0 ? Direction.Left : Direction.Right;
            new Thread(new Car(bridge, "Car " + i, direction)).start();

        }

    }


}
