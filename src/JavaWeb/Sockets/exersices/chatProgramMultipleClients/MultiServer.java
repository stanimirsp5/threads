package JavaWeb.Sockets.exersices.chatProgramMultipleClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class MultiServer {
    public static final int PORT = 4444;

    public static void main(String[] args) {
        ArrayList<ServerThread> threadList = new ArrayList<>();
        try(
                ServerSocket serverSocket = new ServerSocket(PORT);
        ){
            while (true){
                Socket client = serverSocket.accept();
                ServerThread serverThread = new ServerThread(client);
                //threadList.add(serverThread);
                serverThread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
