package threads.Examples.Train;

public class TrainTravel {
    public static final int SHIP_MAX_CAPACITY = 5;
    public static void main(String[] args){
        MonitorSpace monitor = new MonitorSpace(SHIP_MAX_CAPACITY);
        SpaceShip spaceShip = new SpaceShip(monitor);

        new Thread(spaceShip).start();

        for (int i = 0; i < 20; i++) {
            Position jediPosition = i%2==0? Position.PlanetA : Position.PlanetB;
            Jedi jedi = new Jedi(i+ " " + jediPosition.name(),monitor, jediPosition);
            new Thread(jedi).start();
        }

    }
}
enum Position{
    PlanetB,
    PlanetA,
    BetweenPlanets
}
class SpaceShip implements Runnable{
    MonitorSpace monitorSpace;

    public SpaceShip(MonitorSpace monitorSpace) {
        this.monitorSpace = monitorSpace;
    }

    @Override
    public void run() {
        for (int i = 0; i < 2; i++) {
            //monitorSpace.arriveAtPlanetA(Position.PlanetA);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            monitorSpace.leavePlanetA(Position.BetweenPlanets);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            monitorSpace.arriveAtPlanetB(Position.PlanetB);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            monitorSpace.leavePlanetB(Position.BetweenPlanets);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            monitorSpace.arriveAtPlanetA(Position.PlanetA);
        }
    }
}

class Jedi implements Runnable{
    String name;
    MonitorSpace monitorSpace;
    Position position;

    public Jedi(String name, MonitorSpace monitorSpace, Position position) {
        this.name = name;
        this.monitorSpace = monitorSpace;
        this.position = position;
    }

    @Override
    public void run() {

        try {

            if( position == Position.PlanetA) {
                monitorSpace.jediTakeShipFromPlanetA(name);

                monitorSpace.jediLeaveShipInPlanetB(name);
            }else {
                monitorSpace.jediTakeShipFromPlanetB(name);

                monitorSpace.jediLeaveShipInPlanetA(name);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MonitorSpace{
    int totalJediOnShip;
    int maxCapacity;
    Position shipPosition;
    private boolean onA, onB;

    public MonitorSpace(int maxCapacity){
        this.maxCapacity = maxCapacity;
        this.onA = true;
        this.onB = false;

    }

    public synchronized void leavePlanetA(Position direction){
        shipPosition = direction;
        onA = false;
        System.out.println("--> train left planet A. Position is "+ shipPosition.name());
    }

    public synchronized void arriveAtPlanetA(Position direction){
        shipPosition = direction;
        onA = true;
        System.out.println("|-- train arrived planet A");

    }

    public synchronized void leavePlanetB(Position direction){
        shipPosition = direction;
        onB = false;
        System.out.println("<-- train left planet B. Position is "+ shipPosition.name());

    }

    public synchronized void arriveAtPlanetB(Position direction){
        shipPosition = direction;
        onB = true;
        System.out.println("--| train arrived planet B");

    }

    public synchronized void jediLeaveShipInPlanetA(String name) throws InterruptedException {
//        while(shipPosition != Position.PlanetA){
        while(!onA){
            System.out.printf("jedi %s can't leave the ship A, because ship is on Planet B \n", name);
            wait();
        }
        totalJediOnShip--;
        System.out.printf("_ jedi %s leave ship A\n", name);
        notifyAll();
    }

    public synchronized void jediLeaveShipInPlanetB(String name) throws InterruptedException {
//        while(shipPosition != Position.PlanetB){
        while(!onB){
            System.out.printf("jedi %s can't leave the ship B, because ship is on Planet A\n", name);
            wait();
        }
        totalJediOnShip--;

        System.out.printf("__ jedi %s leave ship B\n", name);
        notifyAll();
    }

    public synchronized void jediTakeShipFromPlanetA(String name) throws InterruptedException {
        if(maxCapacity == totalJediOnShip){
            System.out.printf("Ship A -> B is full %d (capacity). Jedi %s is waiting\n", totalJediOnShip, name);
            wait();
//        }else if(shipPosition != Position.PlanetA){
        }else if(!onA){ // TODO fix logic?
            System.out.printf("Ship is on planet B, must be on A. Jedi %s is waiting\n", totalJediOnShip, name);
            wait();
        }
        totalJediOnShip++;
        System.out.printf("^ jedi %s take ship A\n", name);

    }

    public synchronized void jediTakeShipFromPlanetB(String name) throws InterruptedException {
        if(maxCapacity == totalJediOnShip){
            System.out.printf("Ship B -> A is full %d (capacity). Jedi %s is waiting\n",totalJediOnShip, name);
            wait();
//        }else if(shipPosition != Position.PlanetB){
        }else if(!onB){
            System.out.printf("Ship is on planet A, must be on B. Jedi %s is waiting\n", totalJediOnShip, name);
            wait();
        }
        totalJediOnShip++;
        System.out.printf("^^ jedi %s take ship B\n", name);

    }
}
