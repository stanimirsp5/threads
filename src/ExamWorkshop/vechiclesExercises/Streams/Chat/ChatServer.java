package ExamWorkshop.vechiclesExercises.Streams.Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ChatServer {
    public static int port;

    public static void main(String[] args) throws IOException {
        System.out.println("Enter server port:");
        Scanner userInputSettings = new Scanner(System.in);
        port = userInputSettings.nextInt();

        runServer();
    }

    public static void runServer() throws IOException {
        ArrayList<Socket> clients = new ArrayList<>();

        ServerSocket ss = new ServerSocket(port);
        while (true){
            Socket s = ss.accept();
            System.out.println("Connected client on port: " + port);
            clients.add(s);

            new ServerThread(s, clients).start();
        }
    }
}
