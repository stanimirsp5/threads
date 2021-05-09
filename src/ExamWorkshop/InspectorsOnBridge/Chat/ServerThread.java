package ExamWorkshop.InspectorsOnBridge.Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread implements Runnable{
    Socket socket;
    ArrayList<ServerThread> threadsList;

    public ServerThread(Socket socket, ArrayList<ServerThread> threadsList) {
        this.socket = socket;
        this.threadsList = threadsList;
    }

    @Override
    public void run() {

        try(
                BufferedReader clientInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ){

            InspectorWorkProtocol iwp = new InspectorWorkProtocol();
            String msg = iwp.processInput(null,null);
            printToAllClients(msg);

            while (true){
                String inputLine = clientInput.readLine();

                msg = iwp.processInput(inputLine, Thread.currentThread().getName());

                printToAllClients(msg);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void printToAllClients(String outputString){
        for (ServerThread server: threadsList){
            // get stream from other clients
            try{
                PrintWriter out = new PrintWriter(server.socket.getOutputStream(), true);
                out.println(outputString);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
