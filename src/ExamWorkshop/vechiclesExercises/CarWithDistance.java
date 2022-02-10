package ExamWorkshop.vechiclesExercises;


import ExamWorkshop.vechiclesExercises.CarsOnBridge.Bridge;
import ExamWorkshop.vechiclesExercises.Stopwatch.Stopwatch;

public class CarWithDistance {
// how to connect road length and car travel
    public static void main(String[] args) throws InterruptedException {
        Bridge br = Bridge.getInstance();
//        Car car = new Car(90);
//        Thread thread = new Thread(car);
//        Thread.sleep(500);
////        Car car2 = new Car();
////        Thread thread2 = new Thread(car2);
////
//        thread.start();
    }
}
class Car implements Runnable{
    private int velocity; // km/h
    private long time; // car travel time
    Stopwatch stopwatch;
    private int ROAD_DISTANCE = 1000; // meters, bridge takes 60% from the road, other 20% are equally distributed

    public Car(int velocity){
        this.velocity = 1000 * velocity / 3600; // convert to m/s
        stopwatch = new Stopwatch();
        stopwatch.start();
    }

    public double getPosition(){ // return position on road im meters
        double distance = getCarTime() * velocity;
        return distance;
    }

    public long getCarTime(){ // calculate car travel time in seconds
       return stopwatch.getTime();
    }

    @Override
    public void run() {
        System.out.println("position : " +getPosition());
        while (getPosition() < 500){
            try {
                Thread.sleep(500);
                //wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("position : " +getPosition());
        }
        System.out.println(getCarTime());
    }

    public static class Test {
    }
}