package threads.Synchronization;

public class Reentrant {
    public static void main(String a[]){
        Reentrant r = new Reentrant();
        r.a();
        // try to access to nested synchronized methods
    }
    public synchronized void a() {
        b();
        System.out.println("here I am, in a()");
    }
    public void test() {
        b();
        System.out.println("here I am, in a()");
    }
    public synchronized void b() {
        System.out.println("here I am, in b()");
    }
}