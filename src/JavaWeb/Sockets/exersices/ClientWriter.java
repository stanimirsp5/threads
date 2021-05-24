package JavaWeb.Sockets.exersices;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
// https://www.edureka.co/blog/socket-programming-in-java/#:~:text=Socket%20programming%20in%20Java%20is,a%20client%20and%20a%20server.

public class ClientWriter {
    public static final int PORT = 5000;
    public static final String IP = "192.168.100.17";// "192.168.0.18";

    public static void main(String[] args) throws IOException {
        runClient();
    }

    public static void runClient() {
        System.out.println("Client started");
        try (
                Socket socket = new Socket(IP, PORT);

                BufferedReader input = new BufferedReader(new InputStreamReader(System.in)); // takes input from terminal
                PrintWriter output = new PrintWriter(socket.getOutputStream(),true); // sends input to server. Send the output through the socket.
                BufferedReader serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            String line = "";

            while ((line = input.readLine()) != null) {
               // System.out.println((line = input.readLine()) != null);
                output.println(line);

                System.out.println("Client response from server " + serverInput.readLine());
            }
//            while (!line.equals("Over")) {
//                line = input.readLine();
//                output.write(line);
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}