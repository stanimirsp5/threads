package JavaWeb.Sockets.examples.Bridge;

import java.io.PrintWriter;

public class Bridge {
    private int nVh, countSeq, maxCount;
    private boolean open;
    Bridge(int maxCount){
        nVh = countSeq = 0;
        open = true;
        this.maxCount=maxCount;
    }
    synchronized public int brN(){
        return nVh;
    }
    synchronized public void takeB(boolean lr,PrintWriter out ){
        while((nVh>0)&& (lr==true)||
                (nVh<0) && (lr==false)|| !open){
            System.out.println("\t"+Thread.currentThread().getName()+" waiting");
            if(out != null) {
                out.print(" waiting");     // to the remote client
                if(!open)out.println("\tthe bridge is closed\n");
                else out.println("\tvehicles in oposite direction\n");
            }
            try{     wait();   }
            catch(InterruptedException e){
                System.err.println(e);
            }
        }
        countSeq++;
        if (lr) nVh--;
        else nVh++;
        System.out.println(Thread.currentThread().getName()+" on the bridge in the same direction: "+countSeq);
        if(out != null) {
            out.println(" on the bridge");     // to the remote client
        }
        if (countSeq==maxCount){open = false; System.out.println("The bridge is closed");}

        try {
            Thread.sleep(500);
        }
        catch(InterruptedException ie) {};
    }

    synchronized public void leaveB(boolean lr,PrintWriter out ){
        if (nVh>0) nVh--;
        else nVh++;
        System.out.println("\t\t"+Thread.currentThread().getName()+" leave the bridge");
        if(out != null) {
            out.println("leave the bridge");     // to the remote client
        }
        if(nVh==0){countSeq=0;open = true; System.out.println("The bridge is open");}
        notifyAll();
    }
}
