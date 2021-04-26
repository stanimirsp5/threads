package ExamWorkshop.InspectorsOnBridge.Bridge;

public class Bridge {

    Direction bridgeDirection = Direction.None;
    int carsOnBridge = 0;

   synchronized public void takeBridge(String name,Direction carDirection){
        if (bridgeDirection == Direction.None) bridgeDirection = carDirection; // set carDirection when bridge is empty

        if(bridgeDirection != carDirection){
            System.out.println(name + " with direction " + carDirection.name() + " waiting");
            try {
                wait();
                bridgeDirection = carDirection;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(bridgeDirection == carDirection) {
            System.out.println(name + " is on the " + carDirection.name() + " side ");
            carsOnBridge++;
        }

    }

    synchronized public void leaveBridge(String name,Direction carDirection){
        System.out.println(name + " left the bridge. It's on the " + carDirection.name() + " side of the bridge");
        carsOnBridge--;
        if(carsOnBridge <= 0){
            bridgeDirection = Direction.None;
            notifyAll();
        }
    }

}
