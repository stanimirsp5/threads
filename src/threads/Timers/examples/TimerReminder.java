package threads.Timers.examples;


import java.util.Timer;
import java.util.TimerTask;


public class TimerReminder {
    Timer timer;
    public TimerReminder(int seconds) {
        timer = new Timer();
        timer.schedule(new RemindTask(), seconds*1000);
    }
    class RemindTask extends TimerTask {
        public void run() {
            System.out.println("Time's up!");
            timer.cancel(); //Terminate the timer thread
        }
    }
    public static void main(String args[]) {
        System.out.format("About to schedule task.%n");
        new TimerReminder(4);
        System.out.println("Task scheduled.");
    }
}