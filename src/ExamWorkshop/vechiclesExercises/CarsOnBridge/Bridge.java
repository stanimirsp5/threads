package ExamWorkshop.vechiclesExercises.CarsOnBridge;

public class Bridge implements IBridge{

    public Direction direction;
    public boolean isBridgeClosed;
    public int carsOnTheBridge;
    public int roadLength;

    public Bridge(int roadLength){
        this.roadLength = roadLength;
        direction = Direction.NONE;
        isBridgeClosed = false;
    }

    public synchronized void takeBridge(Car car) throws InterruptedException {

        while (direction.equals(Direction.LEFT) && car.direction.equals(Direction.RIGHT) ||
                direction.equals(Direction.RIGHT) && car.direction.equals(Direction.LEFT) ||
            isBridgeClosed
        ){
            System.out.println("Distance from bridge "+ car.getPosition());
            System.out.printf("%s %s is waiting \n", car.name,  direction.name() );
            wait();
        }
        if(carsOnTheBridge == 0){
            direction = car.direction;
        }
        carsOnTheBridge++;
        System.out.printf("%s %s is on the bridge \n", car.name,  direction.name() );
       // Thread.sleep(1000);
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

