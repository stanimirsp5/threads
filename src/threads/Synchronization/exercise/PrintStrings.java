package threads.Synchronization.exercise;

public class PrintStrings {

    public static void main(String[] args){
        Printer p = new Printer();
        //String[] messages = {"Hello there ", " general, Kenobi", "Luke, I am ", " your father", "May the force"," be with you!"};
        new ThreadPrinter("Hello there ", " general, Kenobi",p);
        new ThreadPrinter("Luke, I am ", " your father",p);
        new ThreadPrinter("May the force"," be with you!",p);
        //Thread thread = new Thread(printer);
        //thread.start();
    }



}

class Printer{

//    public synchronized void print(String str1,String str2) throws InterruptedException { // lock method
    public void print(String str1,String str2) throws InterruptedException {
        System.out.print(str1);
        Thread.sleep(50);
        System.out.println(str2);
    }

}

class ThreadPrinter extends Thread{
    String msg1, msg2;
    Printer p;//=new Printer();

    public ThreadPrinter(String msg1,String msg2,Printer p) {
        this.msg1 = msg1;
        this.msg2 = msg2;
        this.p = p;
        this.start();
    }

    @Override
    public void run() {
        try {

            //synchronized (p){ // lock object
                p.print(msg1,msg2);
            //}
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


//class ThreadPrinter extends Thread{
//    String[] messages;
//    Printer p;// = new Printer();
//
//    public ThreadPrinter(String[] messages,Printer p) {
//        this.messages = messages;
//        this.p = p;
//        this.start();
//    }
//
//    @Override
//    public void run() {
//
//        for (int i = 1; i < messages.length ; i+=2) {
//            try {
//                String test1 = messages[i-1];
//                String test2 = messages[i];
//                p.print(test1,test2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
