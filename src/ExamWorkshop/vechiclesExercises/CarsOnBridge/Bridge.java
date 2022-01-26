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
        }
        else if(vehicle.getType() == VehicleType.FIRETRUCK){
            takeBridge(vehicle);
        }

        while(!vehicle.isLeavingBridge()){

             if(!Ambulance.hasAmbulance()){ // if no ambulance on bridge - OK
                 Firetruck firetruck = Firetruck.getFiretruck();

                 if(!Firetruck.hasFiretruck()) { // if no firetruck on bridge - OK
                    takeBridge(vehicle);
                }
                // and car is same direction as firetruck - OK
                else if(!firetruck.hasCarWithFiretruck() && // if firetruck on bridge, but no other cars
                      //  vehicle.getPosition() < 400 && // and car is less than 400m close to the bridge
                        firetruck.getDirection() == vehicle.getDirection()
                ) {
                    firetruck.setHasCarWithFiretruck(true);
                    takeBridge(vehicle);
                }
            }
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

            System.out.println("Distance from bridge "+ vehicle.getPosition());
            System.out.printf("%s %s is waiting \n", vehicle.getName(),  bridgeDirection.name() );
            wait();
        }
        if(carsOnTheBridge == 0){
            bridgeDirection = vehicle.getDirection();
        }
        carsOnTheBridge++;
        System.out.printf("%s %s is on the bridge \n", vehicle.getName(),  bridgeDirection.name() );


        vehicle.setLeavingBridge(true); // must be here for car to exit from while loop in takeRoad()
        //Thread.sleep(1000);

    }

    public synchronized void leaveBridge(Vehicle vehicle){
        // remove car from firetruck
        vehicle.leaveBridge();
        System.out.printf("%s %s left the bridge \n", vehicle.getName(),  bridgeDirection.name() );
        carsOnTheBridge--;
        if(carsOnTheBridge == 0){
            bridgeDirection = Direction.NONE;
            notifyAll();
        }
        System.out.println("position : " + vehicle.getPosition());
    }


}

