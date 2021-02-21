package threads.Examples.Cocktail;
// glasses
// seats
// 1. take glass and pour wine
// 2. find free seat
// 3. drink
// 4. free seat and pour wine in same glass
// 5. find seat
// 6. drink
// 7. free seat and wine
// 8. leave bar

public class Bar {
    public static final int SEATS_CAPACITY = 3;
    public static final int NUM_GLASSES = 5;
    public static final int NUM_DRINKERS = 10;

    public static void main(String[] args) {
        Seat seat = new Seat(SEATS_CAPACITY);
        Glass glass = new Glass(NUM_GLASSES);

        for (int i = 0; i < NUM_DRINKERS; i++) {
            new Drinker("Drinker " + i, glass,seat).start();
        }

    }
}

class Drinker extends Thread{
    String name;
    Glass glass;
    Seat seat;

    public Drinker(String name, Glass glass, Seat seat) {
        this.name = name;
        this.glass = glass;
        this.seat = seat;
    }

    @Override
    public void run(){

        System.out.println(name + "entered bar");

        try {
            System.out.println(name + " try to take glass");

            sleep(100);

            glass.takeGlass(name);

            System.out.println(name + " try to find a seat");

            sleep(100);

            seat.takeSeat(name);

            System.out.println(name+" drinking");

            seat.leaveSeat(name);

            System.out.println(name + " try to find a seat for second time");

            seat.takeSeat(name);

            System.out.println(name+" drinking for second time");

            seat.leaveSeat(name);

            glass.poseGlass(name);

            System.out.println(name+" left the bar");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class Glass{
    int cupNumber;

    public Glass(int cupNumber) {
        this.cupNumber = cupNumber;
    }

    public synchronized void takeGlass(String name){
        if(cupNumber <= 0){
            System.out.println(name + " waiting for glass");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(name + " take glass");
        cupNumber--;
    }

    public synchronized void poseGlass(String name){
        System.out.println(name + " pose the glass");
        cupNumber++;
        notifyAll();
    }
}

class Seat{
    int seatCapacity;

    public Seat(int seatCapacity) {
        this.seatCapacity = seatCapacity;
    }

    public synchronized void takeSeat(String name){
        if(seatCapacity <= 0){
            System.out.println(name + " waiting for seat");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(name + " sat down");
        seatCapacity--;
    }

    public synchronized void leaveSeat(String name){
        System.out.println(name + " left the seat");
        seatCapacity++;
        notifyAll();
    }

}