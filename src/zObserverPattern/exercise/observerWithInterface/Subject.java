package zObserverPattern.exercise.observerWithInterface;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    // references to observers
    public boolean isBridgeClosed = false;
    List<Observer> observers = new ArrayList<>();

    public void setTraffic(boolean isClosed){
        this.isBridgeClosed = isClosed;
        notifyObservers();
    }

    public void attachObserver(Observer observer){
        observers.add(observer);
    }

    public void notifyObservers(){
        for (Observer observer : observers) {
            observer.update(isBridgeClosed);
        }
    }

}
