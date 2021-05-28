package zObserverPattern.exercise.observerWithInterface;

public class ConcreteObserver implements Observer {


    @Override
    public void update(boolean isBridgeClosed) {
        System.out.println("Inspector1 bridge is: "+isBridgeClosed);
    }
}
