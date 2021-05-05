package ExamWorkshop.InspectorsOnBridge.Bridge;

import ExamWorkshop.InspectorsOnBridge.Gui.CarGui;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Bridge {

    Direction bridgeDirection = Direction.None;
    int carsOnBridge = 0;
    boolean isBridgeClosed = false;

    synchronized public void takeBridge(String name, Direction carDirection, CarGui carGui){
        if (bridgeDirection == Direction.None) bridgeDirection = carDirection; // set carDirection when bridge is empty
        while(bridgeDirection != carDirection ||
                carsOnBridge >= 3 || // wait when too many cars on the bridge, wait when there are opposite direction cars on the bridge
                isBridgeClosed
        ){
            if(isBridgeClosed){
                System.out.println("Bridge is closed for inspection");
            }else {
                System.out.printf("   %s %s waiting (%d)\n", directionArrow(carDirection), name, carsOnBridge);
            }

            try {
                wait();
                if (bridgeDirection == Direction.None) bridgeDirection = carDirection;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        carsOnBridge++;

        carGui.runCar();
        System.out.printf("%s %s %s is on the bridge (%d)\n",Thread.currentThread().getName(),directionArrow(carDirection), name,carsOnBridge);
    }

    synchronized public void leaveBridge(String name,Direction carDirection){
        carsOnBridge--;
        System.out.printf("%s %s left the bridge (%d)\n",directionArrow(carDirection), name,carsOnBridge);

        if(carsOnBridge <= 0){
            bridgeDirection = Direction.None;
            notifyAll();
        }
    }

    private String directionArrow(Direction direction){
        String directionArrow = "";
        switch (direction){
            case Left:
                directionArrow = "<---";
                break;
            case Right:
                directionArrow = "--->";
                break;
            case None:
                directionArrow = "||";
                break;
        }
        return directionArrow;
    }

//    public void closeBridge(){
//        System.out.println("closeBridge: "  + isBridgeClosed);
//        CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS).execute(() -> {
//            isBridgeClosed = !isBridgeClosed;
//        });
//    }

}
