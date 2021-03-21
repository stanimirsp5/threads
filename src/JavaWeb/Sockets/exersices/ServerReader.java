package JavaWeb.Sockets.exersices;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
// https://www.edureka.co/blog/socket-programming-in-java/#:~:text=Socket%20programming%20in%20Java%20is,a%20client%20and%20a%20server.
public class ServerReader {
    public static final int PORT = 5000;
    public static void main(String[] args) throws IOException {
        runServer();

    }

    public static void runServer() throws IOException {
        System.out.println("Server started");

        //getOutputStream() method is used to send the output through the socket.
        try(

                ServerSocket server = new ServerSocket(PORT);
                Socket socket = server.accept(); //  used to accept the incoming request to the socket.
                // takes input from the client socket
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter output = (new PrintWriter(socket.getOutputStream()));
                BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        ){
            String line = "";
            while ((line = input.readLine()) != null)
            {
                //line2 =
                System.out.println("server " +line);
                output.println(userInput.readLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
