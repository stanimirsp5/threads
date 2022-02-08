package ExamWorkshop.vechiclesExercises.CarsOnBridge.Inspectors;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer{
    public static int port = 6666;

    public static void main(String[] args) throws IOException {
        runServer();
    }

    public static void runServer() throws IOException {
        ArrayList<Socket> clients = new ArrayList<>();

        ServerSocket ss = new ServerSocket(port);

        while (true){
            Socket s = ss.accept();
            clients.add(s);

            new Thread(new ServerThread(s,clients)).start();
        }
    }
}
