package threads.Examples.SwimPool;

public class MySwimmingPool {

    public static void main(String[] args) {
        Skep skep = new Skep(3);
        Wardrobe wardrobe = new Wardrobe(2);
        for (int i = 0; i < 10; i++) {
            new MySwimmer(wardrobe,skep, "swimmer " + i).start();
        }
    }
}

class MySwimmer extends Thread{
    Wardrobe wardrobe;
    Skep skep;
    String name;

    public MySwimmer(Wardrobe wardrobe, Skep skep, String name) {
        this.wardrobe = wardrobe;
        this.skep = skep;
        this.name = name;
    }

    @Override
    public void run(){

        skep.takeSkep(name);

        wardrobe.enterWardrobe(name);

        wardrobe.leaveWardrobe(name);

        System.out.println(name + " ^ is swimming");

        wardrobe.enterWardrobe(name);

        wardrobe.leaveWardrobe(name);

        skep.returnSkep(name);

        System.out.println(name + " _ finished swimming");

    }
}

class Wardrobe{
    int capacity;

    public Wardrobe(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void enterWardrobe(String name){
        if(capacity == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        capacity--;
        System.out.println(name + " entered wardrobe. Capacity left " + capacity);
    }
    public synchronized void leaveWardrobe(String name){
        capacity++;
        System.out.println(name + " left wardrobe. Capacity is " + capacity);
    }

}

class Skep{
    int availableUnits;

    public Skep(int availableUnits) {
        this.availableUnits = availableUnits;
    }

    public synchronized void takeSkep(String name){
        if(availableUnits == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        availableUnits--;
        System.out.println(name + " took skep. Skeps left " + availableUnits);
    }
    public synchronized void returnSkep(String name){
        availableUnits++;
        System.out.println(name + " return skep. Skeps are " + availableUnits);
    }
}
