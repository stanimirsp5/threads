package ExamWorkshop.InspectorsOnBridge.Bridge;

public class Bridge {

    Direction directionOfMovement = Direction.None;
    int carsOnBridge = 0;

    public void takeBridge(String name,Direction direction){
        if (directionOfMovement == Direction.None) directionOfMovement = direction; // set direction when bridge is empty

        if(directionOfMovement == direction){
            System.out.println(name + " is on the " + direction.name() + " side ");
            carsOnBridge++;
        }else {
            System.out.println(name + " with direction " + direction.name() + " waiting");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void leaveBridge(String name,Direction direction){
        System.out.println(name + " left the bridge. It's on the " + direction.name() + " side of the bridge");
        carsOnBridge--;
        if(carsOnBridge >= 0){
            notifyAll();
        }
    }

}
