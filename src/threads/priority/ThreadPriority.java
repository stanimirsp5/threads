package threads.priority;

import java.util.*;    // for Date
import java.text.*;    //for DateFormat and SimpleDateFormat
public class ThreadPriority extends Thread {
    private int countDown = 5;
    private String name;
    private static Date d = new Date( );
    private static DateFormat df = new SimpleDateFormat("HH:mm:ss:SSS");
    private volatile double db=0; // no optimization
    public ThreadPriority(String nameP) {
        name = nameP;
        System.out.println("\t\tMaking " + name);
    }
    public void run() {
        d.setTime(System.currentTimeMillis( ));
        System.out.println(name +  "start at " + df.format(d));
        for( ;countDown>0; countDown--) {
            System.out.println("Thread " + name + "(" + countDown + ")"+
                    " priority -> " + getPriority() );
            // An expensive, interruptible operation:
            for(int i = 1; i < 100000; i++)
                db = db + (Math.PI + Math.E) / (double)i;
        }
        System.out.println("Thread " + name + " end");
    }
    public static void main(String[] args) {
        d.setTime(System.currentTimeMillis( ));
        System.out.println("\t\tmain start at " + df.format(d));
        String nameA[]={"Nick", "Marie", "George", "Isabelle", "Pierre"};
        for(int i = 0; i < 5; i++){
            new ThreadPriority(nameA[i]).start();
        }
        System.out.println("\t\tAll Threads Started");
    }
}