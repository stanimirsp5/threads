package ExamWorkshop.InspectorsOnBridge.Chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server{
    public static final int PORT = 4444;

    public void runServer() {
        // list of all clients connections
        ArrayList<ServerThread> threadList = new ArrayList<>();

        try(
            ServerSocket serverSocket = new ServerSocket(PORT);
        ){
            while (true){
                // listen for creation of new client
                Socket socket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(socket, threadList);
                threadList.add(serverThread);
                new Thread(serverThread).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
