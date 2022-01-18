package ExamWorkshop.vechiclesExercises.CarsOnBridge;
//  >......1>...........<2
// car 1 with direction from left to right - right
// bridge is direction right
// car2 is direction left (from right to left)
public class Bridge {

    public Direction direction;
    public boolean isBridgeClosed;
    public int carsOnTheBridge;

    public Bridge(){
        direction = Direction.NONE;
        isBridgeClosed = false;
    }

    public synchronized void takeBridge(Car car) throws InterruptedException {
        // only cars at same direction can be on the bridge
        // others wait
        while (direction.equals(Direction.LEFT) && car.direction.equals(Direction.RIGHT) ||
                direction.equals(Direction.RIGHT) && car.direction.equals(Direction.LEFT) ||
            isBridgeClosed
        ){
            System.out.printf("%s %s is waiting \n", car.name,  direction.name() );
            wait();
        }
        if(carsOnTheBridge == 0){
            direction = car.direction;
        }
        carsOnTheBridge++;
        System.out.printf("%s %s is on the bridge \n", car.name,  direction.name() );

    }

    public synchronized void leaveBridge(Car car){
        System.out.printf("%s %s left the bridge \n", car.name,  direction.name() );
        carsOnTheBridge--;
        if(carsOnTheBridge == 0){
            direction = Direction.NONE;
            notifyAll();
        }
        System.out.println("position : " + car.getPosition());
    }


}

