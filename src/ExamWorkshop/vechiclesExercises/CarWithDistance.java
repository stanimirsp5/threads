package ExamWorkshop.vechiclesExercises;

import java.util.concurrent.TimeUnit;

public class CarWithDistance {

    public static void main(String[] args) throws InterruptedException {
//        Car car = new Car();
//        Thread thread = new Thread(car);
//        Thread.sleep(5000);
//        Car car2 = new Car();
//        Thread thread2 = new Thread(car2);
//
//        thread.start();
//
//        thread2.start();
//
        long start = System.nanoTime();
        //System.out.println(start);
        Thread.sleep(2000);
        long end = System.nanoTime();
        long elapsedTime = end - start;
        //System.out.println(end);
        System.out.println(elapsedTime);

       long t = TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
        System.out.println(t);
        //stopwatch(4000);
        //Thread.sleep(5000);
        //stopwatch(1000);

    }


}

class Car implements Runnable{

    private int velocity = 50; // m/s
    private long time; // car travel time
    Stopwatch stopwatch;

    public Car(){
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
    }
}

class Stopwatch{
    long start;
    long end;

//    public Stopwatch(){
//        start = System.nanoTime();
//    }
    public void start(){
        start = System.nanoTime();
    }

    public void end(){
        end = System.nanoTime();
    }

    public long getTime(){
        end();
        long elapsedTime = end - start;
        long time = TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
        return time;
    }
}