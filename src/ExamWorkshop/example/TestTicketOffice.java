package ExamWorkshop.example;

import static org.junit.Assert.fail;

import java.util.ArrayDeque;

import org.junit.Test;

public class TestTicketOffice {

    public static int score = 0;

    @Test
    public void mainTest() {
        /*
         * Starts the Ticket Server
         */
        System.out.println("Running main test...");
        try {
            TicketServer.start(16792);
        } catch (Exception e) {
            fail();
        }
        Thread office1 = new Thread() {
            public void run() {
                System.out.println("Thread one started...");
                ArrayDeque<TicketServer> clientQueue = new ArrayDeque<TicketServer>();
                //random number of clients
                Integer number_clients = (int) (100 + (Math.random() * (900)));
                while(number_clients != 0)
                {
                    //push client number and box office information up with client constructor
                    //clientQueue.push(new TicketServer("Box Office 1: Client " + number_clients.toString()));

                    number_clients--;
                }

                while(!clientQueue.isEmpty())
                {
                    TicketServer currentClient = clientQueue.poll();
                    //currentClient.requestTicket();
                }
            }
        };
        /*
         * Starts a single box office thread, this thread creates a queue of Ticket Client objects that
         * request for tickets in order of entering the queue.
         */
        Thread office2 = new Thread() {
            public void run() {
                System.out.println("Thread two started...");
                ArrayDeque<TicketServer> clientQueue = new ArrayDeque<TicketServer>();
                //random number of clients
                Integer number_clients = (int) (100 + (Math.random() * (900)));
                while(number_clients != 0)
                {
                    //push client number and box office information up with client constructor
                    //clientQueue.push(new TicketServer("Box Office 2: Client " + number_clients.toString()));
                    number_clients--;
                }

                while(!clientQueue.isEmpty())
                {
                    TicketServer currentClient = clientQueue.poll();
                    //currentClient.requestTicket();
                }
            }
        };
        office1.start();
        office2.start();
        try {
            office1.join();
            office2.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
