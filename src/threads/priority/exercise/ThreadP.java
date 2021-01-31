package threads.priority.exercise;

public class ThreadP implements Runnable {

    public static final int NUM_THREADS = 2;
    String name;
    static String[] names = {"To6ko","Kroki"};//,"Steko","Meko","Teko"};
    public static void main(String[] args){

        for (int i = 0; i < NUM_THREADS; i++) {
            ThreadP threadP = new ThreadP(names[i]);
            Thread thread = new Thread(threadP);
            if(i==1)thread.setPriority(Thread.MAX_PRIORITY);
            thread.start();

        }

    }

    public ThreadP(String name){
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 5; i > 0 ;i--) {
            System.out.println("Person "+ name + " started " + Thread.currentThread().getName() + " priority " +Thread.currentThread().getPriority() + " with count " + i);

        }
    }
}
