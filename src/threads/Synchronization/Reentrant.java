package threads.Synchronization;

public class Reentrant {
    public static void main(String a[]) throws InterruptedException {
        //        Reentrant r = new Reentrant();
        //        r.a();
        // try to access to nested synchronized methods
        Counter counter = new Counter();
        for (int i = 0; i < 5; i++) {
            Worker worker = new Worker(counter);
            Thread thread = new Thread(worker);
            thread.start();
        }
        Thread.currentThread().sleep(1000);
        System.out.println("Total "+counter.getNum());
    }
//    public synchronized void a() {
//        b();
//        System.out.println("here I am, in a()");
//    }
//    public void test() {
//        b();
//        System.out.println("here I am, in a()");
//    }
//    public synchronized void b() {
//        System.out.println("here I am, in b()");
//    }
}

class Worker implements Runnable{
    Counter counter;

    public Worker(Counter counter){
        this.counter = counter;
    }


    @Override
    public void run() {

        counter.add();
    }
}

class Counter{
    int number = 0;
    public synchronized void add(){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        number++;
        System.out.println(Thread.currentThread().getName() +" Add "+number);

        extract();
    }
    public synchronized void extract(){
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + " Extract "+number);
        add();
    }
    public int getNum(){
        return number;
    }
}