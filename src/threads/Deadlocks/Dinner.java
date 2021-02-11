package threads.Deadlocks;

class Philosopher extends Thread{
    String name;
    Fork leftFork, rightFork;
    Philosopher(String name,Fork left, Fork right){
        this.name=name;
        super.setName(name);
        leftFork=left;
        rightFork=right;
    }
    public void run(){
        for(int i=0;i<2;i++){
            leftFork.take(this);
            System.out.println(name+" look for the second fork ");
            try {
                sleep((int)(Math.random()*2));
            } catch (InterruptedException e){}

            rightFork.take(this);
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
class Fork{
    private boolean taken;
    String name;
    Fork(String name){
        taken = false;
        this.name=name;
    }
    synchronized void take(Philosopher p){
        while(taken){
            try{     wait();   }
            catch(InterruptedException e){
                System.err.println(e);
            }
        }
        System.out.println(p+" take "+name);
        taken = true;
    }
    synchronized void pose(){
        taken = false;
        notify();
    }
    boolean canTake(){
        if (!taken)return true;
        else return false;
    }
    public String toString(){
        return name;
    }
}
class Dinner{
    public static void main(String arg[]){
        int number=5;
        Philosopher ph[]= new Philosopher[number];
        Fork fk[]= new Fork[number];
        for(int i=0;i<number;i++){
            fk[i]= new Fork("fork "+i);
        }
        for(int i=0; i<number;i++){
            ph[i] = new Philosopher("P"+(i+1),fk[i],fk[(i+1)%number]);
            ph[i].start();
        }
    }
}