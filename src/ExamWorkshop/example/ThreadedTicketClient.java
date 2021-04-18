package ExamWorkshop.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ThreadedTicketClient extends Thread implements Runnable {
    String hostname = "127.0.0.1";
    String threadname = "X";
    TicketServer sc;

    public ThreadedTicketClient(TicketServer sc, String hostname, String threadname) {
        this.sc = sc;
        this.hostname = hostname;
        this.threadname = threadname;
    }

    public void run() {
        System.out.flush();
        try {
            Socket echoSocket = new Socket(hostname, TicketServer.PORT);
            PrintWriter out = new PrintWriter(echoSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            out.println(threadname);
            echoSocket.close();
            out.close();
            in.close();
            stdIn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
