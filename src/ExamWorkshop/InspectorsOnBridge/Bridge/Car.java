package ExamWorkshop.InspectorsOnBridge.Bridge;

import ExamWorkshop.InspectorsOnBridge.Gui.CarGui;

public class Car implements Runnable{
    Bridge bridge;
    String name;
    Direction direction;
    CarGui carGui;

    public Car(Bridge bridge, String name, Direction direction, int positionNumber) {
        this.bridge = bridge;
        this.name = name;
        this.direction = direction;
        carGui = new CarGui(direction, name, positionNumber);
    }
    public String getName (){
        return name;
    }
    @Override
    public void run() {

        carGui.initCar();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        bridge.takeBridge(name,direction,carGui);

        try {
            Thread.sleep(5500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        bridge.leaveBridge(name,direction);

    }
}


