package ExamWorkshop.CarsOnBridge.Inspectors;

import ExamWorkshop.CarsOnBridge.Helpers.ExceptionLogger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class InspectorChatServer implements Runnable{
    public static int port = 6666;

//    public static void main(String[] args) throws IOException {
//        runServer();
//    }

    public void runServer() throws IOException {
        ArrayList<Socket> clients = new ArrayList<>();

        ServerSocket ss = new ServerSocket(port);

        while (true){
            Socket s = ss.accept();
            System.out.println("client connected");
            clients.add(s);

            new Thread(new ServerThread(s,clients)).start();
        }
    }

    @Override
    public void run() {
        try {
            runServer();
        } catch (IOException e) {
            ExceptionLogger.log(e);
        }
    }
}
