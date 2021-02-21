package threads.Examples.Cocktail;

public class App {
    public static void main(String[] arg){
        Glasses g = new Glasses(3);
        Places p = new Places(2);
        for(int i=0;i<6;i++){
            try{
                Thread.sleep((int)(Math.random()*10));
            }
            catch(InterruptedException ex){}
            new Pers(g,p).start();
        }
    }
}

class Glasses {
    private int number;
    public Glasses(int n){
        number = n;
    }
    synchronized public void take(){
        while(number ==0){
            System.out.println("\t\t\t"+Thread.currentThread().getName()+" waiting for glass");
            try{     wait();   }
            catch(InterruptedException e){
                System.err.println(e);
            }
        }
        number --;
        System.out.println("\t"+Thread.currentThread().getName()+" take a glass\t"+number + " glasses left");
    }
    synchronized public void release(){
        number++;
        System.out.println("\t\t"+Thread.currentThread().getName()+" release the glass\t"+number + " glasses left");
        notifyAll();
    }
}


class Places {
    private int number;
    public Places(int n){
        number =n;
    }
    synchronized public void take(){
        while(number ==0){
            System.out.println("\t\t\t"+Thread.currentThread().getName()+" wait for place");
            try{     wait();   }
            catch(InterruptedException e){
                System.err.println(e);
            }
        }
        number --;
        System.out.println("\t"+Thread.currentThread().getName()+" take a place\t"+number+" places left");
    }
    synchronized public void release(){
        number++;
        System.out.println("\t\t"+Thread.currentThread().getName()+" release the place\t"+number+" places left");
        notifyAll();
    }
}

class Pers extends Thread{
    Glasses g;
    Places p;
    String name;
    static int k=1;
    Pers(Glasses g,Places p){
        this.g=g;
        this.p = p;
        name = "Pers "+ k++;
        super.setName(name);
    }
    public void run(){
        g.take();
        System.out.println(name + " pours some wine ");
        try{
            sleep((int)(Math.random()*50));
        }
        catch(InterruptedException ex){}
        p.take();
        System.out.println(name + " is drinking ");
        try{
            sleep((int)(Math.random()*250));
        }
        catch(InterruptedException ex){}
        p.release();
        System.out.println(name + " pours some wine ");
        try{
            sleep((int)(Math.random()*50));
        }
        catch(InterruptedException ex){}
        p.take();
        System.out.println(name + " is drinking ");
        try{
            sleep((int)(Math.random()*250));
        }
        catch(InterruptedException ex){}
        p.release();
        g.release();
        System.out.println(name + " go out ");
    }
}