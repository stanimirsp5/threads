package ExamWorkshop.InspectorsOnBridge.Bridge;

public class Car implements Runnable{
    Bridge bridge;
    String name;
    Direction direction;

    public Car(Bridge bridge, String name, Direction direction) {
        this.bridge = bridge;
        this.name = name;
        this.direction = direction;
    }

    @Override
    public void run() {

        bridge.takeBridge(name,direction);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        bridge.leaveBridge(name,direction);

    }
}


