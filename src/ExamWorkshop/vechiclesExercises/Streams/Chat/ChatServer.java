package ExamWorkshop.vechiclesExercises.Streams.Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
    public static final int PORT = 6666;

    public static void main(String[] args) throws IOException {
        runServer();
    }

    public static void runServer() throws IOException {
        ArrayList<Socket> clients = new ArrayList<>();

        ServerSocket ss = new ServerSocket(PORT);
        while (true){
            Socket s = ss.accept();
            clients.add(s);
            new ServerThread(s, clients).start();
        }
    }
}
