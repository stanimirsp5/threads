package ExamWorkshop.InspectorsOnBridge.Chat;

import java.io.*;
import java.net.Socket;

public class Inspector{
    public static final int PORT = 4444;
    public static final String IP = "127.0.0.1";
    private static BufferedReader serverInput;

    public void createInspector() {
        // output -> send to server, input -> receive from server
        try(
            Socket client = new Socket(IP,PORT);
            PrintWriter outputToServer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        ) {

            serverInput = new BufferedReader(new InputStreamReader(client.getInputStream()));

            new ClientPrinter().start();

            while (true){
                String inputLine = userInput.readLine();
                outputToServer.println(inputLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientPrinter extends Thread{

        @Override
        public void run() {
            while (true){
                String outputSt = null;
                try {
                    outputSt = serverInput.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("client: " + outputSt);
            }
        }
    }
}



