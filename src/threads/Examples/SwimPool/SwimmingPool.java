package threads.Examples.SwimPool;
// 1. swimmer came
// 2. take changing cabin and clothes basket
// 3. changes clothes
// 4. free cabin and take basket to wardrobe
// 5. swimming
// 6. take changing cabin while bring basket with him
// 7. change clothes
// 8. free cabin and the basket
// 9. leave the pool
public class SwimmingPool {
    public static final int WARDROBE_CAPACITY = 5;
    public static final int NUM_SWIMMERS = 20;
    public static void main(String[] args) {
           PoolComplex poolComplex = new PoolComplex(WARDROBE_CAPACITY);

            for (int i = 0; i < NUM_SWIMMERS; i++) {
                Swimmer swimmer = new Swimmer(poolComplex,i);
                swimmer.start();
            }

    }
}

class Swimmer extends Thread{
    PoolComplex poolComplex;
    String name;
    public Swimmer(PoolComplex poolComplex, int i) {

        this.poolComplex = poolComplex;
        this.name = "Swimmer "+i;
    }

    @Override
    public void run() {
        System.out.println(name + " try to enter cabin");

        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        poolComplex.enterCabin(name);
        System.out.println(name + " try to enter pool");

        try {
            sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        poolComplex.swim(name);

        System.out.println(name + " finished swimming and went to wardrobe");

        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        poolComplex.leaveCabin(name);
    }
}

class PoolComplex{
    int wardrobeCapacity;
    int numberOfSwimmersInCabin;
    public PoolComplex(int wardrobeCapacity) {
        this.wardrobeCapacity = wardrobeCapacity;
    }

    public synchronized void enterCabin(String name){
        if(numberOfSwimmersInCabin >= wardrobeCapacity){
            System.out.println(name + " is waiting");

            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        numberOfSwimmersInCabin++;
        System.out.println(name + " changing clothes");
    }

    public synchronized void leaveCabin(String name){

        numberOfSwimmersInCabin--;
        System.out.println(name + " changing clothes and leave");
        notifyAll();
    }

    public void swim(String name){
        System.out.println(name + " is swimming");
    }

}
