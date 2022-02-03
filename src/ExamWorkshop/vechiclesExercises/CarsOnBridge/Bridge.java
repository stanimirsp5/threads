package ExamWorkshop.vechiclesExercises.CarsOnBridge;

import ExamWorkshop.vechiclesExercises.CarsOnBridge.Vehicles.*;

public class Bridge implements IBridge{

    public Direction bridgeDirection;
    public boolean isBridgeClosed;
    public int carsOnTheBridge;
    public int roadLength;
    public final int BRIDGE_CAPACITY = 3;

    public Bridge(int roadLength){
        this.roadLength = roadLength;
        bridgeDirection = Direction.NONE;
        isBridgeClosed = false;
    }

    public void takeRoad(Vehicle vehicle) throws InterruptedException {

        if(vehicle.getType() == VehicleType.AMBULANCE ||
            vehicle.getType() == VehicleType.FIRETRUCK){

            while (true) {
                if(vehicle.roadThread.isCloseToBridge(0) && vehicle.isRoadWaiting) {
                    takeBridge(vehicle);
                    return;
                }
                Thread.sleep(100);
            }
        }
//        else if(vehicle.getType() == VehicleType.FIRETRUCK){
//            while (!vehicle.roadThread.isCloseToBridge(0)) {
//                takeBridge(vehicle);
//            }
//            takeBridge(vehicle);
//            return;
//        }

        while(true){
                if (!Ambulance.hasAmbulance()) { // if no ambulance on bridge - OK
                    if (!Firetruck.hasFiretruck()) { // if no firetruck on bridge - OK
                        if(vehicle.roadThread.isCloseToBridge(0) && vehicle.isRoadWaiting) {
                            takeBridge(vehicle);
                            return;
                        }
                    }
                    synchronized (this) {
                        if (Firetruck.hasFiretruck() && !Firetruck.getFiretruck().hasCarWithFiretruck() && // if firetruck on bridge, but no other cars
                                vehicle.roadThread.isCloseToBridge(400) && // and car is less than 400m close to the bridge
                                Firetruck.getFiretruck().getDirection() == vehicle.getDirection() // and car is same direction as firetruck - OK
                        ) {
                            while (true) {
                                if(vehicle.roadThread.isCloseToBridge(0) && vehicle.isRoadWaiting) {
                                    Firetruck firetruck = Firetruck.getFiretruck();
                                    firetruck.addCarToFiretruck(vehicle);
                                    takeBridge(vehicle);
                                    return;
                                }
                                Thread.sleep(100);
                            }
                        }
                    }
                //if(vehicle.isLeavingBridge()) break;
                Thread.sleep(500); // car waits to get on the bridge
            }
        }

    }

    public synchronized void takeBridge(Vehicle vehicle) throws InterruptedException {

        while ((bridgeDirection != Direction.NONE &&
                    bridgeDirection != vehicle.getDirection()) ||
            carsOnTheBridge >= BRIDGE_CAPACITY
        ){
            printWaitingMessage(vehicle);

            wait();
        }
        vehicle.isRoadWaiting = false;

        vehicle.vehicleOnBridge();

        if(carsOnTheBridge == 0){
            bridgeDirection = vehicle.getDirection();
        }
        carsOnTheBridge++;
    }

    private void printWaitingMessage(Vehicle vehicle){
        if(isBridgeClosed){ // if not ambulance
            System.out.print("Bridge is closed by inspectors \n");
        }else if(vehicle.getDirection() != bridgeDirection){
            System.out.printf("%s is waiting. Opposite direction. (%d m) \n", vehicle.getName(), vehicle.roadThread.getPosition());
        }else if(carsOnTheBridge >= BRIDGE_CAPACITY){
            System.out.printf("%s is waiting. Bridge is full (%d/%d) \n", vehicle.getName(), carsOnTheBridge, BRIDGE_CAPACITY);
        }
    }

    public synchronized void leaveBridge(Vehicle vehicle){

        vehicle.isRoadWaiting = false;
        vehicle.leaveBridge();
        System.out.printf("%s left the bridge. (%d m) \n", vehicle.getName(), vehicle.roadThread.getPosition());
        carsOnTheBridge--;
        if(carsOnTheBridge == 0){
            bridgeDirection = Direction.NONE;
            notifyAll();
        }
    }

    public synchronized void leaveRoad(Vehicle vehicle){
        System.out.printf("%s left the road. (%d m) \n", vehicle.getName(), vehicle.roadThread.getPosition());
    }

}

