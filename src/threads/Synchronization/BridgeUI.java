package threads.Synchronization;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.*;

public class BridgeUI {
    static Bridge1 b = new Bridge1(3);
    public static void main(String arg[]){
        for(int i = 0; i < 20; i++){
            Vehicle1 v =new Vehicle1(Math.random()>0.5?true:false, b);
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException ex) {}
            v.start();
        }
    }

}

class Gui extends JFrame{
    int w=500,h=300;
    ArrayList<Vehicle1> left = new ArrayList<Vehicle1>(20),
            on = new ArrayList<Vehicle1>(20),
            right = new ArrayList<Vehicle1>(20);
    class MyPanel extends JPanel{
        public void paintComponent(Graphics g){
            super.paintComponent(g) ;
            w = getWidth();
            h = getHeight();
            g.drawLine (w/3, h/2, 2*w/3, h/2) ;
            g.drawLine(w/6, 0, w/3, h/2);
            g.drawLine(w/6, h, w/3, h/2);
            g.drawLine(2*w/3, h/2, 5*w/6, 0);
            g.drawLine(2*w/3, h/2, 5*w/6, h);
            for(int i=0;i<left.size();i++) {
                g.drawString("<"+left.get(i).ln, 5*w/6+22*(i%3), 10+i/3*20);
            }
            for(int i=0;i<right.size();i++) {
                g.drawString(right.get(i).ln+">", 5+22*(i%3), 10+i/3*20);
            }
            for(int i=0;i<on.size();i++) {
                Vehicle1 v = on.get(i);
                if(v.lr)
                    g.drawString("<"+v.ln, w/3+5+22*i, h/2-10);
                else
                    g.drawString(v.ln+">", w/3+5+22*i, h/2-10);
            }
        }
    }
    MyPanel p;
    Gui(){
        p=new MyPanel();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(50, 50, w, h);
        add(p);
        setVisible(true);
    }
}

class Bridge1 {
    private int nVh,cnt_cons, max_cons;
    private boolean closed;
    private Gui gui;
    Bridge1(int max_cons){
        nVh = cnt_cons=0;
        closed = false;
        this.max_cons=max_cons;
        gui = new Gui();
    }
    ////////  for GUI  ----------------------------------
    synchronized public void addGV(Vehicle1 v){  //gui new Vehicle1
        if(v.lr) {
            gui.left.add(v);
        }
        else {
            gui.right.add(v);
        }
        gui.p.repaint();
    }
    synchronized public void onBGV(Vehicle1 v){  //gui on the bridge
        if(v.lr) {
            gui.left.remove(v);
        }
        else {
            gui.right.remove(v);
        }
        gui.on.add(v);
        gui.p.repaint();
    }

    synchronized public void lvBGV(Vehicle1 v){  //gui leave the bridge
        gui.on.remove(v);
        gui.p.repaint();
    }
    ///////// Gui  ---------------------------------------------


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
        try {
            Thread.sleep(1000);
        }
        catch(InterruptedException ie) {}
        if (lr) nVh--;
        else nVh++;
        System.out.println(Thread.currentThread().getName()+" on the bridge");
        onBGV((Vehicle1)Thread.currentThread());
        cnt_cons++;
        if(cnt_cons>=max_cons)closed =true;
        if(closed)System.out.println("The bridge is closed");
    }
    synchronized public void leaveB(boolean lr ){
        if (nVh>0) nVh--;
        else nVh++;
        System.out.println("\t\t"+Thread.currentThread().getName()+" leave the bridge");
        lvBGV((Vehicle1)Thread.currentThread());
        if(nVh==0) {
            cnt_cons=0;
            closed = false;
            System.out.println("The bridge is open");
        }
        notifyAll();
    }
}

class Vehicle1 extends Thread{
    boolean lr;
    Bridge1 b;
    String name;
    static int num;
    int ln;                // for gui
    Vehicle1(boolean lr, Bridge1 b){
        this.lr=lr;
        this.b = b;
        name = "V "+ ++num + (lr?" left->":" <-right");
        ln=num;
        b.addGV(this);
        super.setName(name);
    }
    public void run(){
        b.takeB(lr);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e){}
        b.leaveB(lr);
    }
    public String toString() {
        return name;
    }
}
