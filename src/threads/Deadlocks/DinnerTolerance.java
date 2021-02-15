package threads.Deadlocks;

public class DinnerTolerance {
    public static void main(String arg[]){
        int number=3;
        MyPhilosopher ph[]= new MyPhilosopher[number];
        MyFork fk[]= new MyFork[number];
        for(int i=0;i<number;i++){
            fk[i]= new MyFork("fork "+i);
        }
        for(int i=0; i<number;i++){
            ph[i] = new MyPhilosopher("P"+(i+1),fk[i],fk[(i+1)%number]);
            ph[i].start();
        }
    }
}

class MyFork{
    private boolean taken;
    String name;
    MyFork(String name){
        taken = false;
        this.name=name;
    }
    synchronized void take(MyPhilosopher p)throws TakenExc{
        if(taken){
            throw new TakenExc(name+" is taken");
        }
        System.out.println(p+" take "+name);
        taken = true;
    }
    synchronized void pose(){
        taken = false;
        //notify();
    }
    boolean canTake(){
        if (!taken)return true;
        else return false;
    }
    public String toString(){
        return name;
    }
}

class MyPhilosopher extends Thread{
    String name;
    MyFork leftFork, rightFork;
    MyPhilosopher(String name,MyFork left, MyFork right){
        this.name=name+"("+left.name+","+right.name+")";
        super.setName(name);
        leftFork=left;
        rightFork=right;
    }
    public void run(){
        for(int i=0;i<2;i++){
            do {
                try {
                    leftFork.take(this);
                }catch (TakenExc te) {
                    try {
                        sleep((int)(Math.random()*600));
                    } catch (InterruptedException e){}
                    continue;
                }
                System.out.println(name+" look for the second fork ");
                try {
                    sleep((int)(Math.random()*2));
                } catch (InterruptedException e){}

                try {
                    rightFork.take(this);
                }catch (TakenExc te) {
                    leftFork.pose();
                    System.out.println(name+" pose "+leftFork+" : waiting");
                    try {
                        sleep((int)(Math.random()*600));
                    } catch (InterruptedException e){}
                    continue;
                }
                break;
            }while(true);
            System.out.println(name+" eating ");
            try {
                sleep((int)(Math.random()*1000));
            } catch (InterruptedException e){}
            System.out.println(name+" poses the forks - thinking");
            leftFork.pose();
            rightFork.pose();
            try {
                sleep((int)(Math.random()*1000));
            } catch (InterruptedException e1){}
        }
        System.out.println(name+" stop dinning");
    }
    public String toString(){
        return name;
    }
}

class TakenExc extends Exception{
    TakenExc(String name){
        System.out.println(name);
    }
}