package ExamWorkshop.InspectorsOnBridge.Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class ServerThread implements Runnable{
    Socket socket;
    ArrayList<ServerThread> threadsList;
    public static InspectorWorkProtocol iwp;

    public ServerThread(Socket socket, ArrayList<ServerThread> threadsList) {
        this.socket = socket;
        this.threadsList = threadsList;
    }

    @Override
    public void run() {

        try(
                BufferedReader clientInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ){

            iwp = new InspectorWorkProtocol();
            String msg = "";
            while (true){
               String inputLine = clientInput.readLine();
                String inspectorNumber = inputLine.split(Pattern.quote("|"))[0];
                String message = inputLine.split(Pattern.quote("|"))[1];
               msg = iwp.processInput(message, inspectorNumber);

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
