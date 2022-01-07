package ExamWorkshop.vechiclesExercises;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        MyClient c = new MyClient();

        c.run();
    }
}

class MyClient{
    public void run() throws IOException {
        try (
        Socket s=new Socket("localhost",6666); //  IP address or hostname of the Server and a port number
        //Use readers and writers so that it can write Unicode characters over the socket.
        PrintWriter out = new PrintWriter(s.getOutputStream(), true); //  gets the socket's output stream and opens a PrintWriter on it
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream())); // gets the socket's input stream and opens a BufferedReader on it
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        ){
            String userInput;
            // reads a line of information from the BufferedReader connected to the socket.
            // The readLine method waits until the server echoes the information back to client
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                //When readline returns, client prints the information to the standard output.
                System.out.println("echo: " + in.readLine());
            }
        }
    }
}