package threads.Timers.examples;


import java.util.Timer;
import java.util.TimerTask;

/**
 * Schedule a task that executes once every second.
 */

public class Beep {
    Timer timer;

    public Beep() {
        timer = new Timer();
        timer.schedule(new RemindTask(),
                0,        //initial delay
                1*1000);  //subsequent rate
    }
    class RemindTask extends TimerTask {
        int numWarningBeeps = 3;

        public void run() {
            if (numWarningBeeps > 0) {
                System.out.println("Beep!");
                numWarningBeeps--;
            } else {
                System.out.println("Time's up!");
                timer.cancel();
            }
        }
    }
    public static void main(String args[]) {
        System.out.println("About to schedule task.");
        new Beep();
        System.out.println("Task scheduled.");
    }
}