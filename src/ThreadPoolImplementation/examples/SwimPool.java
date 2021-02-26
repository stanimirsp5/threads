package ThreadPoolImplementation.examples;

public class SwimPool {

}

class Swimmer implements Runnable{
    PoolManagement poolManagement;
    String name;

    public Swimmer(PoolManagement poolManagement, String name) {
        this.poolManagement = poolManagement;
        this.name = name;
        poolManagement.swimmerName = name;
    }

    @Override
    public void run() {

        System.out.println(name + "enters pool");
        poolManagement.takeBasket();

        poolManagement.enterWardrobe();

        poolManagement.leaveWardrobe();

        System.out.println(name+"swimming");

        poolManagement.enterWardrobe();

        poolManagement.leaveWardrobe();

        poolManagement.loseBasket();

        System.out.println(name + "leave pool");
    }
}

class PoolManagement{
    String swimmerName;
    int basketCount = 3;
    int wardrobeCount = 2;

    public synchronized void takeBasket(){

    }

    public synchronized void loseBasket(){

    }

    public synchronized void enterWardrobe(){

    }
    public synchronized void leaveWardrobe(){

    }
}