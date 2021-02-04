package threads.Synchronization;

// nVh < 0 left --
// nVh > 0 right ++
// Q: How to understand which type of threads are on the bridge (left or right)?
// A: If nVh (number of vehicles on the bridge) is positive number - right V are on the bridge and left must wait.

public class Bridge { // common resource
    private int nVh;
    Bridge(){
        nVh = 0;
    }
    synchronized public int brN(){
        return nVh;
    }
    synchronized public void takeB(boolean lr ){
        while((nVh>0)&& (lr==true)||
                (nVh<0) && (lr==false)){
            System.out.println("\t"+Thread.currentThread().getName()+" waiting");
            try{     wait();   }
            catch(InterruptedException e){
                System.err.println(e);
            }
        }
        if (lr) nVh--;
        else nVh++;
        System.out.println(Thread.currentThread().getName()+" on the bridge");
    }
    synchronized public void leaveB(boolean lr ){
        if (nVh>0) nVh--;
        else nVh++;
        System.out.println("\t\t"+Thread.currentThread().getName()+" leave the bridge");
        notifyAll();
    }
}

class Vehicle extends Thread { // threads
    boolean lr;
    Bridge b;
    String name;
    static int num;
    Vehicle(boolean lr, Bridge b){
        this.lr=lr;
        this.b = b;
        name = "V "+ ++num + (lr?" left->":" <-right");
        super.setName(name);
    }
    public void run(){
        b.takeB(lr);
        try {
            sleep((int)(Math.random()*200));
        } catch (InterruptedException e){}
        b.leaveB(lr);
    }
}

class Circ { // model
    public static void main(String arg[]){
        Bridge b = new Bridge();
        for(int i = 0; i < 20; i++){
            (new Vehicle(Math.random()>0.5?true:false, b)).start();
        }
    }
}