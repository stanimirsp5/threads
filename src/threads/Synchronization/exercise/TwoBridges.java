package threads.Synchronization.exercise;

public class TwoBridges {
    public static final int NUM_THREADS = 40;

    public static void main(String[] args){
        MainBridge leftSideBridge = new MainBridge();
        MainBridge rightSideBridge = new MainBridge();

        for (int i = 0; i < NUM_THREADS; i++) {
            CarDirection direction = Math.random() > 0.5 ? CarDirection.LEFT : CarDirection.RIGHT;
            new Car(leftSideBridge,rightSideBridge,direction);//.start();
        }

    }
}

class Car extends Thread{
    MainBridge leftB, rightB;
    CarDirection direction;
    int bridgeNum = 0;
    String name;
    public Car(MainBridge leftB, MainBridge rightB, CarDirection direction) {
        this.leftB = leftB;
        this.rightB = rightB;
        this.direction = direction;
        name = direction.name() + " " + Thread.currentThread().getName();
        this.start(); //difference?
        //super.setName(name);
    }

    @Override
    public void run() {
        name = direction.name() + " " + Thread.currentThread().getName();

        if (direction == CarDirection.LEFT) {
            bridgeNum = 1;

            leftB.takeBridge(bridgeNum,name, direction);
        } else {
            bridgeNum = 2;

            rightB.takeBridge(bridgeNum,name, direction);
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (direction == CarDirection.LEFT) {

            leftB.leaveBridge(bridgeNum,name, direction);
        } else {

            rightB.leaveBridge(bridgeNum,name, direction);
        }

        // second bridge

        if (direction == CarDirection.LEFT) {
            bridgeNum = 2;

            rightB.takeBridge(bridgeNum,name, direction);
        } else {
            bridgeNum = 1;

            leftB.takeBridge(bridgeNum,name, direction);
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (direction == CarDirection.LEFT) {
            rightB.leaveBridge(bridgeNum,name, direction);
        } else {
            leftB.leaveBridge(bridgeNum,name, direction);
        }
    }
}
//r > 0 > l
class MainBridge{
    int carsOnTheBridge;
    synchronized public void takeBridge(int bridgeNum,String name, CarDirection direction){
        while ( direction == CarDirection.LEFT && carsOnTheBridge > 0 ||
                direction == CarDirection.RIGHT && carsOnTheBridge < 0){
            try {
                System.out.println( name + " is waiting for bridge " + bridgeNum );

                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(direction == CarDirection.LEFT){
            carsOnTheBridge--;
        }else {
            carsOnTheBridge++;
        }
        System.out.println( name + " take bridge " + bridgeNum );
    }

    synchronized public void leaveBridge(int bridgeNum,String name, CarDirection direction){
        
        System.out.println( name + " left bridge " + bridgeNum );
        notifyAll();
        if(direction == CarDirection.LEFT){
            carsOnTheBridge++;
        }else {
            carsOnTheBridge--;
        }
    }

}
enum CarDirection{
    LEFT,
    RIGHT
}
