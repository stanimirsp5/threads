package ExamWorkshop.example;

import java.text.SimpleDateFormat;
import java.util.Date;



public class Ticket {
    private String production;
    private String theatre;
    public String seat;
    private String date;
    private String time;
    private Theatre home;
    public Ticket(Theatre home)
    {
        this.home = home;
        Date today = new Date();
        SimpleDateFormat simpleDate = new SimpleDateFormat ("E yyyy.MM.dd");
        date = simpleDate.format(today);
        time = "7:00PM";
        production = "Pusheen: The Saga";
        theatre = "Bates Recital Hall";
    };
    public void print()
    {
        System.out.println("------------------------------------------------");
        System.out.println("--"+production+ " Only at " + theatre + "--");
        System.out.println("--" + "Be in seat " + seat+ " on " + date + " at " +time + "-");
        System.out.println("------------------------------------------------\n\n" );

    }
    public void selectSeat()
    {
        seat = home.findBestSeat();
    }
    public void setSeat(String data)
    {
        seat = data;
    }

}
class TicketClient {
    ThreadedTicketClient tc;
    String result = "dummy";
    String hostName = "";
    String threadName = "";

    TicketClient(String hostname, String threadname) {
       // tc = new ThreadedTicketClient(this, hostname, threadname);
        hostName = hostname;
        threadName = threadname;
    }

    TicketClient(String name) {
        this("localhost", name);
    }

    TicketClient() {
        this("localhost", "unnamed client");
    }

    void requestTicket() {
        tc.run();
    }

    void sleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


