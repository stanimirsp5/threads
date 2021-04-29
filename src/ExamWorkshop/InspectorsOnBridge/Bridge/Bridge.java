package ExamWorkshop.InspectorsOnBridge.Bridge;

public class Bridge {

    Direction bridgeDirection = Direction.None;
    int carsOnBridge = 0;

   synchronized public void takeBridge(String name,Direction carDirection){
        if (bridgeDirection == Direction.None) bridgeDirection = carDirection; // set carDirection when bridge is empty

        while(bridgeDirection != carDirection || carsOnBridge >= 3){ // wait when too many cars on the bridge, wait when there are opposite direction cars on the bridge
            System.out.println(name + " - " + carDirection.name() + " cars " + carsOnBridge + " waiting");
            try {
                wait();
                //bridgeDirection = carDirection;
                if (bridgeDirection == Direction.None) bridgeDirection = carDirection;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(bridgeDirection == carDirection) {
            carsOnBridge++;
            System.out.println(name + " is on the " + carDirection.name() + " side " + " cars on bridge " + carsOnBridge);
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
