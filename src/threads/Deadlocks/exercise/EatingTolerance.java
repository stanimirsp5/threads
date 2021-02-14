package threads.Deadlocks.exercise;

public class EatingTolerance {
    public static final int NUM_MICES = 3;
    public static final int NUM_PROGRAMMERS = 3;

    public static void main(String arg[]) {

        Mice[] mices = new Mice[NUM_MICES];
        for (int i = 0; i < NUM_MICES; i++) {
            mices[i] = new Mice(i%2==0?" left mice " + i:" right mice " + i);
        }

        for (int i = 0; i < NUM_PROGRAMMERS; i++) {

            new Thread(new Programmer("P " + i ,mices[i],mices[(i+1)%NUM_MICES])).start();
        }
    }
}

class Programmer implements Runnable{
    String name;
    Mice leftMice;
    Mice rightMice;

    public Programmer(String name, Mice leftMice, Mice rightMice) {
        this.name = name +"("+leftMice.name + rightMice.name+")";
        this.leftMice = leftMice;
        this.rightMice = rightMice;
    }

    @Override
    public void run() {

        while (true) {
            try {
                System.out.println("left only");
                leftMice.take(this);
            } catch (TakenExc takenExc) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                leftMice.pose(this);
                continue;
            }
            System.out.println("Programmer "+name+" look for "+rightMice.name);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                rightMice.take(this);
            } catch (TakenExc takenExc) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                rightMice.pose(this);
                continue;
            }

            System.out.println("Programmer "+name+" has two mices -- playing with "+ leftMice.name +" and "+rightMice.name);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Programmer "+name+" has finished play and returned mices");
            leftMice.pose(this);
            rightMice.pose(this);
            break;
        }
    }
}

class Mice {
    String name;
    boolean isMiceTaken;

    public Mice(String name){
        this.name = name;
    }

    synchronized public void take(Programmer p) throws TakenExc {
        if (isMiceTaken){
            throw new TakenExc("Error: "+name +" is busy " + p.name);
        }

        isMiceTaken = true;
        System.out.println(name+" is taken successfully by "+ p.name);
    }

    synchronized public void pose(Programmer p){
        isMiceTaken = false;
        System.out.println(name+" is pose by "+ p.name);
    }
}

class TakenExc extends Exception{
    TakenExc(String name){
        System.out.println(name);
    }
}