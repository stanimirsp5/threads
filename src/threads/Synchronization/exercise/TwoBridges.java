package threads.Synchronization.exercise;

public class TwoBridges {
    public static final int NUM_THREADS = 4;

    public static void main(String[] args){
        MainBridge leftSideBridge = new MainBridge();
        MainBridge rightSideBridge = new MainBridge();

        for (int i = 0; i < NUM_THREADS; i++) {
            Direction direction = Math.random() > 0.5 ? Direction.LEFT : Direction.RIGHT;
            new Car(leftSideBridge,rightSideBridge,direction);
        }

    }
}

class Car implements Runnable{
    MainBridge leftB, rightB;
    Direction direction;

    public Car(MainBridge leftB, MainBridge rightB, Direction direction) {
        this.leftB = leftB;
        this.rightB = rightB;
        this.direction = direction;
    }
// need way to indicate whe one thread is left one bridge so it can take on another
    @Override
    public void run() {
        if (direction == Direction.LEFT) {
            leftB.takeBridge();
        } else {
            rightB.takeBridge();
        }

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (direction == Direction.LEFT) {
            leftB.leaveBridge();
        } else {
            rightB.leaveBridge();
        }

    }
}

class MainBridge{
    public void takeBridge(){

    }

    public void leaveBridge(){

    }

}
enum CarDirection{
    LEFT,
    RIGHT
}
