package ExamWorkshop.vechiclesExercises.Streams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        MyServer s = new MyServer();
        s.run();
    }
}

class MyServer{
    final int portNumber = 6666;
    public void run() throws IOException {
        try (
                ServerSocket ss = new ServerSocket(portNumber);
                Socket s = ss.accept();//establishes connection and waits for the client
                PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        ) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                out.println(inputLine);
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port "
                    + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}

