package ExamWorkshop.vechiclesExercises;
import java.util.concurrent.TimeUnit;
public class CarWithDistance {
// how to connect road length and car travel
    public static void main(String[] args) throws InterruptedException {
        Car car = new Car(90);
        Thread thread = new Thread(car);
        Thread.sleep(500);
//        Car car2 = new Car();
//        Thread thread2 = new Thread(car2);
//
        thread.start();
    }
}
class Car implements Runnable{
    private int velocity; // km/h
    private long time; // car travel time
    Stopwatch stopwatch;

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
}

class Stopwatch{
    long start;
    long end;

    long pauseStart;
    long pauseEnd;

    public void start(){
        start = System.nanoTime();
    }

    public void end(){
        end = System.nanoTime();
    }

    public void pause(){
        pauseStart = System.nanoTime();
    }

    public void resume(){
        pauseEnd = System.nanoTime();
    }

    public long getTime(){
        end();
        long elapsedTime = end - start;
        long elapsedPauseTime = pauseEnd - pauseStart;
        pauseEnd -= elapsedPauseTime;
        long time = TimeUnit.SECONDS.convert(elapsedTime, TimeUnit.NANOSECONDS);
        return time;
    }
}