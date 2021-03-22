package JavaWeb.Sockets.exersices.chatProgram;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int PORT = 4444;
    public static void main(String[] args) {

        try(
                ServerSocket serverSocket = new ServerSocket(PORT);
                Socket client = serverSocket.accept();

                BufferedReader fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter toClient = new PrintWriter(client.getOutputStream(),true);

        ){
            System.out.println("Server started");
            String inputFromClient;
            while ((inputFromClient = fromClient.readLine()) != null) {
                //System.out.println("Server " + inputFromClient);
                toClient.println("Response from server " + inputFromClient);
            }
        }catch (IOException err){
            System.out.println(err);
        }


    }
}
