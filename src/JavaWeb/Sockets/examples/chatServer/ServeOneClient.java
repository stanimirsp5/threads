package JavaWeb.Sockets.examples.chatServer;

import java.io.*;
import java.net.*;

class ServeOneClient extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    Clients clt;
    public ServeOneClient(Socket s,Clients clt)  throws IOException {
        socket = s;
        this.clt =clt;
        in = new BufferedReader(
                new InputStreamReader(
                        socket.getInputStream()));
        // Enable auto-flush:
        out = new PrintWriter( new BufferedWriter(
                new OutputStreamWriter(
                        socket.getOutputStream()
                )
        ),
                true);
        // If any of the above calls throw an
        // exception, the caller is responsible for
        // closing the socket. Otherwise the thread
        // will close it.
        clt.addC(out);
        start();    // Calls run()
    }
    public void run() {
        try {
            while (true) {
                String str = in.readLine();
                if (str.equals("END")) break;
                System.out.println(str);
                clt.sendC(str);
            }

        } catch (IOException e) {  }
        finally {
            try {
                clt.rmvC(out);
                System.out.println("disconect a client. Total number "+clt.nCl());
                socket.close();
            } catch(IOException e) {}
        }
    }
}