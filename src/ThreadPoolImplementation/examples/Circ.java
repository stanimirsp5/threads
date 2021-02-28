package ThreadPoolImplementation.examples;

import java.util.concurrent.*;
public class Circ {
    static Bridge b = new Bridge(3);
    public static void main(String arg[]){
        int coreThr=7;
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(
                coreThr, coreThr, 5000L, TimeUnit.MILLISECONDS,
                (new LinkedBlockingQueue<Runnable>( )));

        for(int i = 0; i < 20; i++){
            tpe.execute(new Vehicle(Math.random()>0.5?true:false, b));
            try {
                Thread.sleep((int)(Math.random()*50));
            }
            catch (InterruptedException ex) {}
        }
        tpe.shutdown();
    }

}

class Vehicle implements Runnable{
    boolean lr;
    Bridge b;
    String name;
    static int num;
    Vehicle(boolean lr, Bridge b){
        this.lr=lr;
        this.b = b;
        name = "V "+ ++num + (lr?" left->":" <-right");
    }
    public void run(){
        Thread.currentThread().setName(name);
        b.takeB(lr);
        try {
            Thread.sleep((int)(Math.random()*200));
        } catch (InterruptedException e){}
        b.leaveB(lr);
    }
    public String toString() {
        return name;
    }
}

class Bridge {
    private int nVh,cnt_cons, max_cons;
    private boolean closed;
    Bridge(int max_cons){
        nVh = cnt_cons=0;
        closed = false;
        this.max_cons=max_cons;
    }
    synchronized public int brN(){
        return nVh;
    }
    synchronized public void takeB(boolean lr ){
        while((nVh>0)&& (lr==true)||
                (nVh<0) && (lr==false)||closed){
            System.out.println("\t"+Thread.currentThread().getName()+" waiting");
            try{     wait();   }
            catch(InterruptedException e){
                System.err.println(e);
            }
        }
        if (lr) nVh--;
        else nVh++;
        System.out.println(Thread.currentThread().getName()+" on the bridge");
        cnt_cons++;
        if(cnt_cons>=max_cons)closed =true;
        if(closed)System.out.println("The bridge is closed");
    }
    synchronized public void leaveB(boolean lr ){
        if (nVh>0) nVh--;
        else nVh++;
        System.out.println("\t\t"+Thread.currentThread().getName()+" leave the bridge");
        if(nVh==0) {
            cnt_cons=0;
            closed = false;
            System.out.println("The bridge is open");
        }
        notifyAll();
    }
}