package ExamWorkshop.CarsOnBridge.Bridge;

import ExamWorkshop.CarsOnBridge.Vehicles.*;
import ExamWorkshop.CarsOnBridge.Vehicles.VehicleTypes.*;

public class Bridge implements IBridge {

    public Direction bridgeDirection;
    public static Movement movementOnBridge;
    public int carsOnTheBridge;
    public int roadLength;
    public final int BRIDGE_CAPACITY = 5;

    public static Bridge bridgeSingleInstance;

    public Bridge(int roadLength){
        this.roadLength = roadLength;
        bridgeDirection = Direction.NONE;
        bridgeSingleInstance = this;
    }

    public void takeRoad(Vehicle vehicle) throws InterruptedException {
        while(!vehicle.movementThread.isCloseToBridge(0) || // is far away from bridge - wait
                !vehicle.isMovementPaused // movement is not paused - wait
        ){
            Thread.sleep(200);
        }
        takeBridge(vehicle);
    }

    public synchronized void takeBridge(Vehicle vehicle) throws InterruptedException {
        while ((bridgeDirection != Direction.NONE &&
                bridgeDirection != vehicle.getDirection()) || // vehicle is at different direction as other cars on bridge - wait
                isCarWaitingToTakeBridge(vehicle) ||
                isBridgeFull() ||
                movementOnBridge == Movement.FULLYCLOSED
        ){
            printWaitingMessage(vehicle);
            wait();
        }
        if(vehicle.getType() == VehicleType.AMBULANCE ||
                vehicle.getType() == VehicleType.FIRETRUCK) {
            ((ISpecialVehicle) vehicle).addSpecialVehicle();
        }
        vehicle.isMovementPaused = false;
        System.out.printf("%s is on the bridge. (%d m) \n", vehicle.getName(), vehicle.movementThread.getPosition());

        if(carsOnTheBridge == 0){
            bridgeDirection = vehicle.getDirection();
        }
        carsOnTheBridge++;
    }

    /**
     * If movement on the bridge is half closed - car waits
     * If ambulance on the bridge - car waits
     * If firetruck with one car on the bridge - car waits
     * If only firetruck but the car is more than 400 away from the bridge - car waits
     * @param vehicle
     * @return
     */
    private boolean isCarWaitingToTakeBridge(Vehicle vehicle){
        if (vehicle.getType() != VehicleType.CAR) return false;
        Car car = (Car)vehicle;
        if (movementOnBridge == Movement.HALFCLOSED){
            return true;
        }
        // if it has ambulance on bridge - Wait
        if (Ambulance.hasAmbulance()) {
            car.carWaitingMessage = String.format("%s is waiting. Ambulance is on the bridge.", car.getName());
            return true;
        }
        // if it has firetruck on bridge, check if there is a car with firetruck
        if (Firetruck.hasFiretruck()) { // check for firetruck
            if (!Firetruck.getFiretruck().hasCarWithFiretruck() && // check for car
                    car.movementThread.isCloseToBridge(400) // car is less than 400m close to the bridge
            ) {
                Firetruck firetruck = Firetruck.getFiretruck();
                firetruck.addCarToFiretruck(car);
                return false;
            }else {
                car.carWaitingMessage = String.format("%s is waiting. Firetruck with one car are on the bridge.", car.getName());
                return true;
            }
        }
        return false;
    }

    private void printWaitingMessage(Vehicle vehicle){
        if(movementOnBridge == Movement.FULLYCLOSED){
            System.out.printf("%s is waiting. Bridge is closed by inspectors \n", vehicle.getName());
        }else if(movementOnBridge == Movement.HALFCLOSED && vehicle.getType() == VehicleType.CAR){
            System.out.printf("%s is waiting. Bridge is closed for cars \n", vehicle.getName());
        }else if(vehicle.getDirection() != bridgeDirection){
            System.out.printf("%s is waiting. Opposite direction. (%d m) \n", vehicle.getName(), vehicle.movementThread.getPosition());
        }else if(carsOnTheBridge >= BRIDGE_CAPACITY){
            System.out.printf("%s is waiting. Bridge is full (%d/%d) \n", vehicle.getName(), carsOnTheBridge, BRIDGE_CAPACITY);
        }else if(vehicle.getType() == VehicleType.CAR){
            Car car = (Car)vehicle;
            System.out.println(car.carWaitingMessage);
        }
    }

    private boolean isBridgeFull(){
        return carsOnTheBridge >= BRIDGE_CAPACITY;
    }

    /**
     * When vehicle successfully traveled across the bridge it must leave it.
     * @param vehicle
     */
    public synchronized void leaveBridge(Vehicle vehicle){
        vehicle.isMovementPaused = false;
        vehicle.leaveBridge();
        carsOnTheBridge--;
        if(carsOnTheBridge == 0){
            bridgeDirection = Direction.NONE;
            notifyAll();
        }
    }

    public synchronized void leaveRoad(Vehicle vehicle){
        // System.out.printf("%s left the road. (%d m) \n", vehicle.getName(), vehicle.movementThread.getPosition());
    }

    /**
     * Receive command from Client to close or open the bridge for vehicles
     */
    public synchronized void closeBridge(Movement movement){
        movementOnBridge = movement;

        if(movementOnBridge == Movement.OPEN){
            notifyAll();
            System.out.println("*Bridge is open*");
        }else if(movementOnBridge == Movement.HALFCLOSED){
            System.out.println("*Bridge is cosed for cars*");
        }else if(movementOnBridge == Movement.FULLYCLOSED){
            System.out.println("*Bridge is cosed for vehicles*");
        }
    }

    /**
     * Share one instance of Bridge class across application.
     * Works like singleton.
     * @return Bridge instance or null if Bridge isn't created yet
     */
    public static Bridge getInstance()
    {
        if (bridgeSingleInstance == null) return  null;
        return bridgeSingleInstance;
    }
}
