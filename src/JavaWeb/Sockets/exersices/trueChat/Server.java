package JavaWeb.Sockets.exersices.trueChat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
//https://gyawaliamit.medium.com/multi-client-chat-server-using-sockets-and-threads-in-java-2d0b64cad4a7
public class Server {
    public static final int PORT = 4444;

    public static void main(String[] args) {

        // list to add all the clients thread
        ArrayList<ServerThread> threadList = new ArrayList<>();
        try(
                ServerSocket serverSocket = new ServerSocket(PORT);
        ){
            while (true){
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
