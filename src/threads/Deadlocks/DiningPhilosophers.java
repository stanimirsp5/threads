package threads.Deadlocks;
import java.util.*;
import java.util.concurrent.*;
import static java.util.concurrent.TimeUnit.*;
// problem invented by Edsger Dijkstra
public class DiningPhilosophers {
    private StickHolder[] sticks;
    private Philosophizer[] philosophers;
    public DiningPhilosophers(int n) {
        sticks = new StickHolder[n];
        Arrays.setAll(sticks, i -> new StickHolder());
        philosophers = new Philosophizer[n];
        Arrays.setAll(philosophers, i ->
                new Philosophizer(i,
                        sticks[i], sticks[(i + 1) % n]));    // [1]
        // Fix by reversing stick order:
        // philosophers[1] =                     // [2]
        //   new Philosophizer(0, sticks[0], sticks[1]);
        Arrays.stream(philosophers)
                .forEach(CompletableFuture::runAsync); // [3]
    }
    public static void main(String[] args) {
        // Returns right away:
        new DiningPhilosophers(5);               // [4]
        // Keeps main() from exiting:
        ScheduledExecutorService sched =
                Executors.newScheduledThreadPool(1);
        sched.schedule( () -> {
            System.out.println("Shutdown");
            sched.shutdown();
        }, 3, SECONDS);
    }
}
class StickHolder {
    private static class Chopstick {}
    private Chopstick stick = new Chopstick();
    private BlockingQueue<Chopstick> holder =
            new ArrayBlockingQueue<>(1);
    public StickHolder() { putDown(); }
    public void pickUp() {
        try {
            holder.take(); // Blocks if unavailable
        } catch(InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void putDown() {
        try {
            holder.put(stick);
        } catch(InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
class Philosophizer implements Runnable {
    private final int seat;
    private final StickHolder left, right;
    public Philosophizer(int seat,
                       StickHolder left, StickHolder right) {
        this.seat = seat;
        this.left = left;
        this.right = right;
    }
    @Override
    public String toString() {
        return "P" + seat;
    }
    @Override
    public void run() {
        while(true) {
            // System.out.println("Thinking");   // [1]
            right.pickUp();
            left.pickUp();
            System.out.println(this + " eating");
            right.putDown();
            left.putDown();
        }
    }
}