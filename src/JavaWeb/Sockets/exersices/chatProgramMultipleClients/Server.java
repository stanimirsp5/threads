package JavaWeb.Sockets.exersices.chatProgramMultipleClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {
    public static final int PORT = 4444;

    public static void main(String[] args) {

        try(
            ServerSocket serverSocket = new ServerSocket(PORT);
            Socket client = serverSocket.accept();

            BufferedReader readFromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter writeToClient = new PrintWriter(client.getOutputStream(), true);
        ){
            String clientInput;
            while ((clientInput = readFromClient.readLine()) !=null){

                writeToClient.println("Response from server: " + clientInput);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
