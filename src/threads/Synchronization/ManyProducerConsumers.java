package threads.Synchronization;

public class ManyProducerConsumers {
    public static void main( String args[] ){
        IntShare h =new IntShare();
        for(int i=0;i<3;i++){
            (new SetShared( h )).start();
        }
        try {Thread.sleep( (int) ( Math.random() * 100 ) );}
        catch( InterruptedException e ) {
            System.err.println( e.toString() );
        }
        for(int i=0;i<2;i++){
            (new GetShared(h)).start();
        }
    }
}

class IntShare {
    private int intShared = -1;
    private int numP =0;
    private boolean writable= true;
    public synchronized void incP(){        numP++;    }
    public synchronized void decP(){        numP--;    }
    public synchronized void setShared( int val ){
        while(!writable) {
            System.out.println("\t"+Thread.currentThread().getName()+" waiting");
            try{     wait();   }
            catch(InterruptedException e){
                System.err.println(e);
            }
        }
        System.out.println( Thread.currentThread().getName() +
                " set intShared: " + val );
        intShared = val;
        writable = false;
        notifyAll();
    }
    public synchronized int getShared() throws NoP{
        while(writable) {
            if(numP==0)  throw new NoP()  ;
            System.out.println("\t"+Thread.currentThread().getName()+" waiting");
            try{     wait(1000);  }
            catch(InterruptedException e){
                System.err.println(e);
            }
        }
        System.out.println( Thread.currentThread().getName() +
                " get intShared " + intShared );
        writable = true;
        notifyAll();
        return intShared;
    }
}

class SetShared extends Thread {
    private IntShare pGarde;
    public SetShared( IntShare h ){
        super( "SetShared " +(int)(Math.random()*1000));
        pGarde = h;
    }
    public void run(){
        pGarde.incP();
        System.out.println( getName() + " starting"  );
        for ( int counter = 1; counter <= 4; counter++ ) {
            try {Thread.sleep( (int) ( Math.random() * 300 ) );}
            catch( InterruptedException e ) { System.err.println( e.toString() );   }
            pGarde.setShared( counter );
        }
        pGarde.decP();
        System.out.println( getName() + " finished"  );
    }
}

class GetShared extends Thread {
    private IntShare cGarde;
    public GetShared( IntShare h ){
        super( "GetShared "+(int)(Math.random()*1000) );
        cGarde = h;
    }
    public void run(){
        System.out.println( getName() + " starting"  );
        int val;
        do {
            try {Thread.sleep( (int) ( Math.random() * 100 ) );}
            catch( InterruptedException e ) {
                System.err.println( e.toString() );
            }
            try{
                val = cGarde.getShared();
            }catch (NoP exc){
                break;
            }
            System.out.println("\t\t"+this.getName()+" get value "+val);
        } while ( true );
        System.out.println( getName() + " finished");
    }
}

class NoP extends Exception{
    NoP(){
        System.out.println("Exception NoP thrown");
    }
}
