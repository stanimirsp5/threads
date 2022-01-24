package ExamWorkshop.vechiclesExercises.CarsOnBridge;

import ExamWorkshop.vechiclesExercises.CarsOnBridge.Vehicles.Vehicle;
import ExamWorkshop.vechiclesExercises.CarsOnBridge.Vehicles.VehicleType;

public class Bridge implements IBridge{

    public Direction direction;
    public boolean isBridgeClosed;
    public boolean isAmbulanceOnBridge;
    public Vehicle firetruckOnBridge;
    public boolean hasOneCarWithFiretruck;
    public int carsOnTheBridge;
    public int roadLength;

    public Bridge(int roadLength){
        this.roadLength = roadLength;
        direction = Direction.NONE;
        isBridgeClosed = false;
    }

    public void takeRoad(Vehicle vehicle) throws InterruptedException {

        while(!vehicle.isLeavingBridge()){
            if(vehicle.getType() == VehicleType.AMBULANCE){
                isAmbulanceOnBridge = true;
                takeBridge(vehicle);
            }
            else if(vehicle.getType() == VehicleType.FIRETRUCK){
                firetruckOnBridge = vehicle;
                takeBridge(vehicle);
            }
            else if(vehicle.getType() == VehicleType.CAR && !isAmbulanceOnBridge){
                if(firetruckOnBridge != null) {
                    takeBridge(vehicle);
                }else if(!hasOneCarWithFiretruck && vehicle.getPosition() < 400) { // if firetruck on bridge allow only one car at same direction
                    takeBridge(vehicle);
                }
            }
            Thread.sleep(200);
        }

    }

    public synchronized void takeBridge(Vehicle vehicle) throws InterruptedException {

        while (direction.equals(Direction.LEFT) && vehicle.getDirection().equals(Direction.RIGHT) ||
                direction.equals(Direction.RIGHT) && vehicle.getDirection().equals(Direction.LEFT)
        ){
            if(isBridgeClosed){
                wait();
            }
            if(vehicle.getType() == VehicleType.AMBULANCE){
                continue;
            }
            if(vehicle.getType() == VehicleType.FIRETRUCK){
                continue;
            }
//            if(isFiretruckOnBridge && vehicle.getPosition() > 400){
//                hasOneCar = true;
//                continue;
//            }
            System.out.println("Distance from bridge "+ vehicle.getPosition());
            System.out.printf("%s %s is waiting \n", vehicle.getName(),  direction.name() );
            wait();
        }
        if(carsOnTheBridge == 0){
            direction = vehicle.getDirection();
        }
        carsOnTheBridge++;
        System.out.printf("%s %s is on the bridge \n", vehicle.getName(),  direction.name() );
        vehicle.setLeavingBridge(true);
       // Thread.sleep(1000);
    }

    public synchronized void leaveBridge(Vehicle vehicle){
        System.out.printf("%s %s left the bridge \n", vehicle.getName(),  direction.name() );
        carsOnTheBridge--;
        if(carsOnTheBridge == 0){
            direction = Direction.NONE;
            notifyAll();
        }
        System.out.println("position : " + vehicle.getPosition());
    }


}

