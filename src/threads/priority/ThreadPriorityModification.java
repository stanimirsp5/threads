package threads.priority;

import java.text.*;
import java.util.*;

//: ThreadPriorityModification.java
public class ThreadPriorityModification extends Thread {
    private int countDown = 5;
    private String name;
    private static Date dt = new Date( );
    private static DateFormat df = new SimpleDateFormat("HH:mm:ss:SSS");
    private volatile double d=0; // no optimization
    public ThreadPriorityModification(String name, int prior) {
        this.name = name;
        setPriority(prior);
        System.out.println("\t\tMaking " + name);
    }
    public void run() {
        dt.setTime(System.currentTimeMillis( ));
        System.out.println(name+"start at "+df.format(dt)+"pr -> "+getPriority());
        for( ;countDown>0; countDown--) {

            // An expensive, interruptible operation:
            for(int i = 1; i < 100000; i++)
                d = d + (Math.PI + Math.E) / (double)i;

            System.out.println("Thread " + name + "(" + countDown + ")"+
                    " priority -> " + getPriority() );
        }
        System.out.println("Thread " + name + " end");
    }
    public static void main(String[] args) {
        String nameA[]={"Nick", "Marie", "George", "Isabelle", "Pierre","Rose","Salome"};
        ThreadPriorityModification st[] = new ThreadPriorityModification[nameA.length];
        for(int i = 0; i < nameA.length; i++)
            st[i] = new ThreadPriorityModification(nameA[i],i<3?Thread.MAX_PRIORITY:
                    Thread.MIN_PRIORITY);
        for(int i = 3; i < nameA.length; i++)
            st[i] .start();
        System.out.println("\t\tThe Threads with low priority started");
        for(int i = 0; i < 3; i++)
            st[i] .start();
        System.out.println("\t\tAll Threads Started");
    }
} 	