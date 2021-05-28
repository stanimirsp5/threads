package zObserverPattern.exercise.observerWithInterface;

public class Inspector2 implements Observer {
    @Override
    public void update(boolean isBridgeClosed) {
        System.out.println("Inspector2 bridge is: "+isBridgeClosed);
    }
}
