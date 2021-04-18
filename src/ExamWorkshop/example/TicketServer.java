package ExamWorkshop.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TicketServer {
    static int PORT = 2222;
    // no matter how many concurrent requests you get,
    // do not have more than three servers running concurrently
    final static int MAXPARALLELTHREADS = 3;

    public static void start(int portNumber) throws IOException {
        PORT = portNumber;
        Runnable serverThread = new ThreadedTicketServer();
        Thread t = new Thread(serverThread);
        t.start();
    }
}

class ThreadedTicketServer implements Runnable {

    String hostname = "127.0.0.1";
    String threadname = "X";
    String testcase;
    TicketClient sc;

    public void run() {
        ServerSocket serverSocket;
        Theatre theater = new Theatre();
        Boolean tickets_done = false;
        try {
            serverSocket = new ServerSocket(TicketServer.PORT);
            while(true)
            {
                Socket clientSocket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String inputLine = in.readLine();
                if( inputLine != null)
                {
                    Ticket ticket = new Ticket(theater);
                    ticket.selectSeat();
                    if (tickets_done == true)
                    {
                        clientSocket.close();
                    }
                    else if (ticket.seat == "NO TICKETS AVAILABLE")
                    {
                        System.out.println("No more tickets available, the theater has been sold out.");
                        tickets_done = true;
                        clientSocket.close();
                    }
                    else
                    {
                        System.out.println(inputLine + " reserved " + ticket.seat);
                        clientSocket.close();
                    }
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
