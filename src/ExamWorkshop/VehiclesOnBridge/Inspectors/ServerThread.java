package ExamWorkshop.VehiclesOnBridge.Inspectors;

import ExamWorkshop.VehiclesOnBridge.Helpers.ExceptionLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread implements Runnable{
    public Socket socket;
    public InspectorProtocol protocol;
    public ArrayList<Socket> clients;

    /**
     * Read message from one client and resends it to all clients
     * @param socket
     * @param clients
     */
    public ServerThread(Socket socket, ArrayList<Socket> clients){
        this.socket = socket;
        this.clients = clients;
        protocol = new InspectorProtocol();

    }

    @Override
    public void run() {
        try(
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            String fromClient;


            while ((fromClient = in.readLine()) != null){
               String message = protocol.processInput(fromClient);
                for(Socket client : clients){
                    PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                    out.println(message);
                }
            }

        }catch (IOException e) {
            ExceptionLogger.log(e);
        }
    }
}
