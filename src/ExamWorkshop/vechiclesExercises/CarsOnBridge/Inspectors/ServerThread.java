package ExamWorkshop.vechiclesExercises.CarsOnBridge.Inspectors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread implements Runnable{
    public Socket socket;
    public ArrayList<Socket> clients;

    public ServerThread(Socket socket, ArrayList<Socket> clients){
        this.socket = socket;
        this.clients = clients;

    }

    @Override
    public void run() {
        try(
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            String fromClient;
            InspectorProtocol protocol = new InspectorProtocol();

            while ((fromClient = in.readLine()) != null){
               String message = protocol.processInput(fromClient);
                for(Socket client : clients){
                    //if(client == socket) continue;
                    PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                    out.println(message);
                }
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
