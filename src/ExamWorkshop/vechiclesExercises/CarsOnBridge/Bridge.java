package ExamWorkshop.vechiclesExercises.CarsOnBridge;

import ExamWorkshop.vechiclesExercises.CarsOnBridge.Vehicles.*;

public class Bridge implements IBridge{

    public Direction bridgeDirection;
    public boolean isBridgeClosed;
    public int carsOnTheBridge;
    public int roadLength;

    public Bridge(int roadLength){
        this.roadLength = roadLength;
        bridgeDirection = Direction.NONE;
        isBridgeClosed = false;
    }

    public void takeRoad(Vehicle vehicle) throws InterruptedException {
        if(vehicle.getType() == VehicleType.AMBULANCE){
            takeBridge(vehicle);
            return;
        }
        else if(vehicle.getType() == VehicleType.FIRETRUCK){
            takeBridge(vehicle);
            return;
        }
        // TODO: all methods here to be thread safe
        while(true){
             if(!Ambulance.hasAmbulance()){ // if no ambulance on bridge - OK
                if(!Firetruck.hasFiretruck()) { // if no firetruck on bridge - OK
                    takeBridge(vehicle);
                    return;
                }
                else if(!Firetruck.getFiretruck().hasCarWithFiretruck() && // if firetruck on bridge, but no other cars
                      //  vehicle.getPosition() < 400 && // and car is less than 400m close to the bridge
                         Firetruck.getFiretruck().getDirection() == vehicle.getDirection() // and car is same direction as firetruck - OK
                ) {
                    synchronized (this) {
                        Firetruck firetruck = Firetruck.getFiretruck();
                        firetruck.addCarToFiretruck(vehicle);
                        takeBridge(vehicle);
                    }
                    return;
                }
            }
            //if(vehicle.isLeavingBridge()) break;
            Thread.sleep(200); // car waits to get on the bridge
        }

    }

    public synchronized void takeBridge(Vehicle vehicle) throws InterruptedException {

        while (bridgeDirection.equals(Direction.LEFT) && vehicle.getDirection().equals(Direction.RIGHT) ||
                bridgeDirection.equals(Direction.RIGHT) && vehicle.getDirection().equals(Direction.LEFT)
        ){
            if(isBridgeClosed){ // if not ambulance
                wait();
            }

           // System.out.println("Distance from bridge "+ vehicle.getPosition());
            System.out.printf("%s is waiting \n", vehicle.getName());
            wait();
        }
        if(carsOnTheBridge == 0){
            bridgeDirection = vehicle.getDirection();
        }
        carsOnTheBridge++;
        System.out.printf("%s is on the bridge \n", vehicle.getName());


        //vehicle.setLeavingBridge(true); // must be here for car to exit from while loop in takeRoad()
        //Thread.sleep(1000);

    }

    public synchronized void leaveBridge(Vehicle vehicle){
        // remove car from firetruck
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

