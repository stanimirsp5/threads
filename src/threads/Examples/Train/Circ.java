package threads.Examples.Train;

public class Circ {
    public static void main(String arg[]){
        Monitor mt = new Monitor(5);
        Train train = new Train(mt);
        train.start();
        for(int i=0;i<20;i++){
            (new Pass(mt,Math.random()>0.5?true:false)).start();
        }
    }
}

class Monitor {
    private boolean onA, onB; // is train stopped on concrete station
    private int passAB,passBA, passMax; // number of passengers traveling in the same direction
    public Monitor(int passMax ){
        onA=true;
        System.out.println("Train on A");
        onB = false;
        passAB=passBA=0;
        this.passMax=passMax;
    }
    public synchronized void leaveA(){
        onA=false;
        System.out.println("\t\t\t\tTrain travelling A ->B");
    }
    public synchronized void arriveB(){
        System.out.println("\t\t\t\tTrain arrive B");
        onB=true;
        notifyAll();
    }
    public synchronized void leaveB(){
        onB = false;
        System.out.println("\t\t\t\tTrain travelling B ->A");
    }
    public synchronized void arriveA(){
        System.out.println("\t\t\t\tTrain arrive A");
        onA=true;
        notifyAll();
    }
    public synchronized void taketA(){
        while(!onA||((passAB+passBA)>=passMax)){
            System.out.println("\t\t"+Thread.currentThread().getName()+" waiting train");
            try{     wait();   }
            catch(InterruptedException e){
                System.err.println(e);
            }
        }
        System.out.print(Thread.currentThread().getName()+" get the train;");
        passAB++;
        if(passAB+passBA >= passMax) System.out.print(" train is full");
        System.out.println("\t"+passAB+" passengers in the train traveling A-> B");
    }
    public synchronized void taketB(){
        while(!onB||((passAB+passBA)>=passMax)){
            System.out.println("\t\t"+Thread.currentThread().getName()+" waiting train");
            try{     wait();   }
            catch(InterruptedException e){
                System.err.println(e);
            }
        }
        System.out.print(Thread.currentThread().getName()+" get the train;");
        passBA++;
        if(passAB+passBA >= passMax) System.out.print(" train is full");
        System.out.println("\t"+passBA+" passengers in the train traveling B-> A");
    }
    public synchronized void leavetA(){
        while(!onA){
            System.out.println("\t"+Thread.currentThread().getName()+" synchronized");
            try{     wait();   }
            catch(InterruptedException e){
                System.err.println(e);
            }
        }
        System.out.print(Thread.currentThread().getName()+" leaving the train and going home; ");
        if(passBA>0)passBA--;
        System.out.println("\t"+passBA+" passengers B-> A still in the train");
        notifyAll();
    }
    public synchronized void leavetB(){
        while(!onB){
            System.out.println(Thread.currentThread().getName()+" traveling in the train");
            try{     wait();   }
            catch(InterruptedException e){
                System.err.println(e);
            }
        }
        System.out.print(Thread.currentThread().getName()+" leaving the train and going home; ");
        if(passAB>0)passAB--;
        System.out.println("\t"+passAB+" passengers  A-> B still in the train");
        notifyAll();
    }
}

// Passenger
class Pass extends Thread{
    boolean aToB; // direction of travelling (included in passenger name)
    static int num=1;
    Monitor mt;
    String name;
    Pass(Monitor mt,boolean aToB){
        name = "pass"+num++ +(aToB?"(A to B)":"(B to A)");
        super.setName(name);
        this.mt=mt;
        this.aToB = aToB;
    }
    public void run(){
        if(aToB){
            mt.taketA();
            mt.leavetB();
        }
        else{
            mt.taketB();
            mt.leavetA();
        }
    }
}
// The train passes three times the distance from A to B and back
class Train extends Thread{
    Monitor mt;
    public Train(Monitor mt){
        this.mt=mt;
    }
    public void run(){
        for(int i=0;i<3;i++){
            try{
                sleep(1000);
            }
            catch(InterruptedException e){}
            mt.leaveA();
            try{
                sleep(2000);
            }
            catch(InterruptedException e){}
            mt.arriveB();
            try{
                sleep(1000);
            }
            catch(InterruptedException e){}
            mt.leaveB();
            try{
                sleep(500);
            }
            catch(InterruptedException e){}
            mt.arriveA();
        }
    }
}