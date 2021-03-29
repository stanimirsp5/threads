package JavaWeb.Sockets.examples.Bridge;


//for local in constructor's parameter:  name=out=null
import java.io.PrintWriter;

public class Vehicle extends Thread{
    private boolean lr;
    private Bridge b;
    private String name;
    private static int num;
    private PrintWriter out;
    Vehicle(String name,boolean lr, Bridge b, PrintWriter out){
        this.lr=lr;
        this.b = b;
        this.out=out;
        if(name==null)this.name = "V "+ ++num + (lr?" left->":" <-right");  //local vehicle
        else this.name = "\07\t\t"+name + (lr?" left->":" <-right"); //remote vehicle
        super.setName(this.name);
        if(num%10==1)System.out.println("\n\n\n starting new serie "+num/10+"\n\n");
        System.out.println("\t\t\t\tCreating new Vehicle "+this.name);
        if(num%10==0)System.out.println("\t\t\tend of serie "+num/10);
    }
    public void run(){
        try {
            sleep((int)(Math.random()*600));
        } catch (InterruptedException e){}
        b.takeB(lr,out);         //direction, remote or local
        try {
            sleep(3000);
        } catch (InterruptedException e){}
        b.leaveB(lr,out);
    }
}