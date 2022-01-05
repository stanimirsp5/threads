package ExamWorkshop.vechiclesExercises;

import java.util.concurrent.TimeUnit;

public class CarWithDistance {

    public static void main(String[] args) throws InterruptedException {
        Car car = new Car();
        Thread thread = new Thread(car);
        Thread.sleep(5000);
        Car car2 = new Car();
        Thread thread2 = new Thread(car2);

        thread.start();

        thread2.start();

        System.out.println("end");
    }





}

class Car implements Runnable{

    private int velocity = 50;
    private long time; // car travel time

    public Car(){
        //long millis = System.currentTimeMillis();
        //long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);
        long nano     = System.nanoTime();
        //int elapsedTime = (int)((millis / 1000) % 60);
        //long seconds = TimeUnit.NANOSECONDS.toSeconds(nano);
        time = nano;
       // stopwatch();
    }

    public double getPosition(){ // return position on road im meters
        //int distance = time;
        long end = System.nanoTime();

        long elapsedTime = end - time;
       // double elapsedTimeInSecond = (double) elapsedTime / 1_000_000_000;
        long convert = TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
        return convert;
    }

    public void stopwatch() {

        long start = System.nanoTime();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.nanoTime();

        long elapsedTime = end - start;

        System.out.println(elapsedTime);

        // 1 second = 1_000_000_000 nano seconds
        double elapsedTimeInSecond = (double) elapsedTime / 1_000_000_000;

        System.out.println(elapsedTimeInSecond + " seconds");

        // TimeUnit
        long convert = TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);

        System.out.println(convert + " seconds");

    }

    @Override
    public void run() {
        System.out.println("position : " +getPosition());
    }
}
