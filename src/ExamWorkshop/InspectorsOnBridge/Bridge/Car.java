package ExamWorkshop.InspectorsOnBridge.Bridge;

import ExamWorkshop.InspectorsOnBridge.Gui.CarGui;

public class Car implements Runnable{
    Bridge bridge;
    String name;
    Direction direction;
    CarGui carGui;

    public Car(Bridge bridge, String name, Direction direction) {
        this.bridge = bridge;
        this.name = name;
        this.direction = direction;
        carGui = new CarGui(direction);
    }
    public String getName (){
        return name;
    }
    @Override
    public void run() {

        carGui.initCar();

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        bridge.takeBridge(name,direction,carGui);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        bridge.leaveBridge(name,direction);

    }
}


