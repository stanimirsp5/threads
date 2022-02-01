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

        if(vehicle.getType() == VehicleType.AMBULANCE){
            while (!vehicle.roadThread.isCloseToBridge()) {
                takeBridge(vehicle);
            }
            return;
        }
        else if(vehicle.getType() == VehicleType.FIRETRUCK){
            while (!vehicle.roadThread.isCloseToBridge()) {
                takeBridge(vehicle);
            }
            takeBridge(vehicle);
            return;
        }

        while(true){
            if(!Ambulance.hasAmbulance()){ // if no ambulance on bridge - OK
                if(!Firetruck.hasFiretruck()) { // if no firetruck on bridge - OK
                    takeBridge(vehicle);
                    return;
                }
                synchronized (this) {
                    if(Firetruck.hasFiretruck() && !Firetruck.getFiretruck().hasCarWithFiretruck() && // if firetruck on bridge, but no other cars
                             vehicle.roadThread.isCloseToBridge() && // and car is less than 400m close to the bridge
                             Firetruck.getFiretruck().getDirection() == vehicle.getDirection() // and car is same direction as firetruck - OK
                    ) {
                        Firetruck firetruck = Firetruck.getFiretruck();
                        firetruck.addCarToFiretruck(vehicle);
                        takeBridge(vehicle);
                        return;
                    }
                }
            }
            //if(vehicle.isLeavingBridge()) break;
            Thread.sleep(1000); // car waits to get on the bridge
        }

    }

    public synchronized void takeBridge(Vehicle vehicle) throws InterruptedException {

//        while (bridgeDirection.equals(Direction.LEFT) && vehicle.getDirection().equals(Direction.RIGHT) || // if br direction is left and vh direction is right - wait
//                bridgeDirection.equals(Direction.RIGHT) && vehicle.getDirection().equals(Direction.LEFT) || // if br direction is right and vh direction is left - wait
//                carsOnTheBridge >= BRIDGE_CAPACITY
//        ){
        while ((bridgeDirection != Direction.NONE &&
                    bridgeDirection != vehicle.getDirection()) ||
            carsOnTheBridge >= BRIDGE_CAPACITY
        ){
            printWaitingMessage(vehicle);

            wait();
        }
        vehicle.roadThread.notify();
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
            System.out.printf("%s is waiting. Opposite direction. \n", vehicle.getName());
        }else if(carsOnTheBridge >= BRIDGE_CAPACITY){
            System.out.printf("%s is waiting. Bridge is full (%d/%d) \n", vehicle.getName(), carsOnTheBridge, BRIDGE_CAPACITY);
        }
    }

    public synchronized void leaveBridge(Vehicle vehicle){

        vehicle.roadThread.notify();
        vehicle.leaveBridge();
        System.out.printf("%s left the bridge \n", vehicle.getName());
        carsOnTheBridge--;
        if(carsOnTheBridge == 0){
            bridgeDirection = Direction.NONE;
            notifyAll();
        }
        //System.out.println("position : " + vehicle.getPosition());
    }

}

