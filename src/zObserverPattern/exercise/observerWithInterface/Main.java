package zObserverPattern.exercise.observerWithInterface;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Subject subject = new Subject();
        ConcreteObserver concreteObserver = new ConcreteObserver();
        Inspector2 inspector = new Inspector2();
        subject.attachObserver(concreteObserver);
        subject.attachObserver(inspector);

        subject.setTraffic(true);
        System.out.println("traffic changed");
        Thread.sleep(3000);

        subject.setTraffic(false);
        System.out.println("traffic changed");
        Thread.sleep(3000);

        subject.setTraffic(true);
        System.out.println("traffic changed");
    }
}
