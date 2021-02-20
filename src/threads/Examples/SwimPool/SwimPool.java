package threads.Examples.SwimPool;

public class SwimPool {
    public static void main(String[] args) {
        Cabine c=new Cabine(2);
        Basket b = new Basket(3);
        for(int i = 0; i<5;i++){
            (new Client(c,b)).start();
        }
    }
}

class Cabine {
    private int free;
    Cabine(int free){
        this.free = free;
    }
    synchronized void takeCabine(){
        while(free==0){
            System.out.println("there is no free cabine, "+Thread.currentThread().getName()+" waiting");
            try{     wait();   }
            catch(InterruptedException e){
                System.err.println(e);
            }
        }
        free--;
        System.out.println("the cabine is taken by "+Thread.currentThread().getName()+", there is "+free+" free cabines");
    }
    synchronized void releaseCabine(){
        free++;
        System.out.println("the cabine is released by "+Thread.currentThread().getName()+", there is "+free+" free cabines");
        notifyAll();
    }
}


class Basket {
    private  int free;
    Basket(int free){
        this.free = free;
    }
    synchronized void takeBasket(){
        while(free==0){
            System.out.println("there is no free basket,"+Thread.currentThread().getName()+" waiting");
            try{     wait();   }
            catch(InterruptedException e){
                System.err.println(e);
            }
        }
        free--;
        System.out.println("the basket is taken by"+Thread.currentThread().getName()+", there is "+free+" free baskets");
    }
    synchronized void releaseBasket(){
        free++;
        System.out.println("the basket is released by"+Thread.currentThread().getName()+", there is "+free+" free baskets");
        notifyAll();
    }
}

class Client extends Thread{
    String  name;
    static int n=0;
    Cabine c;
    Basket b;
    Client(Cabine c, Basket b){
        name = "Client "+ ++n;
        this.c=c;
        this.b = b;
        try {
            System.out.println(" creating new client:"+name);
            sleep((int)(Math.random()*50));
        } catch (InterruptedException e){}
        super.setName(name);
    }
    public void run(){
        try {
            System.out.println(this+" going to the swim pool");
            sleep((int)(Math.random()*50));
        } catch (InterruptedException e){}
        System.out.println(this+" try to take basket");
        b.takeBasket();
        try {
            System.out.println(this+" going to the cabine");
            sleep((int)(Math.random()*50));
        } catch (InterruptedException e){}
        System.out.println(this+" try to take cabine");
        c.takeCabine();
        try {
            System.out.println(this+" changing");
            sleep((int)(Math.random()*600));
        } catch (InterruptedException e){}
        System.out.println(this+" release cabin");
        c.releaseCabine();
        try {
            System.out.println(this+" swimimg");
            sleep((int)(Math.random()*2000));
        } catch (InterruptedException e){}
        System.out.println(this+" try to take cabine");
        c.takeCabine();
        try {
            System.out.println(this+" changing");
            sleep((int)(Math.random()*600));
        } catch (InterruptedException e){}
        System.out.println(this+" release cabin");
        c.releaseCabine();
        System.out.println(this+" release basket");
        b.releaseBasket();
    }
    public String toString(){
        return name;
    }
}