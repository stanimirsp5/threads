package threads.Deadlocks.exercise;

public class Eating {
    public static final int NUM_PROFESSORS = 5;
    public static final int NUM_SPOONS = 5;

    public static void main(String[] args){
        Spoon[] spoons = new Spoon[NUM_SPOONS];
        Professor[] professors = new Professor[NUM_PROFESSORS];

        for (int i = 0; i < NUM_SPOONS; i++) {
            spoons[i] = new Spoon("spoon " + i, professors[i]);
        }
        for (int i = 0; i < NUM_PROFESSORS; i++) {
            professors[i] = new Professor(spoons[i], spoons[(i+1)%NUM_PROFESSORS], "P " + i);// is calculate correct?
            new Thread(professors[i]).start();
        }
    }
}

class Professor implements Runnable{
    Spoon leftSpoon;
    Spoon rightSpoon;
    String name;

    public Professor(Spoon leftSpoon, Spoon rightSpoon, String name) {
        this.leftSpoon = leftSpoon;
        this.rightSpoon = rightSpoon;
        this.name = name;
    }

    @Override
    public void run() {
        leftSpoon.take(this);
        System.out.println(name + " try to took right spoon");

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        rightSpoon.take(this);

        System.out.println(name + " has two spoons. Now eating.");

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        leftSpoon.pose(this);
        rightSpoon.pose(this);

    }
}

class Spoon{
    String name;
    Professor professor;
    boolean isSpoonTaken;
    public Spoon(String name, Professor professor) {
        this.name = name;
        this.professor = professor;
    }

    synchronized public void take(Professor p) {
        while (isSpoonTaken){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(p.name + " took " + name);
        isSpoonTaken = true;
    }

    synchronized public void pose(Professor p) {
        notifyAll();
        System.out.println(p.name + " took " + name);
        isSpoonTaken = false;
    }
}