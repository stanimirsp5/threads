package ExamWorkshop.InspectorsOnBridge.Chat;

import ExamWorkshop.InspectorsOnBridge.Gui.ChatWindowFactory;
import ExamWorkshop.InspectorsOnBridge.Gui.MainGui;

import java.io.*;
import java.net.Socket;

public class Inspector{
    public static final int PORT = 4444;
    public static final String IP = "127.0.0.1";
    private static BufferedReader serverInput;

//    public void createInspector() {
    public static void main(String[] args) {
        ChatWindowFactory chatWindow = new ChatWindowFactory();
        chatWindow.initChatWindow(1);
        // output -> send to server, input -> receive from server
        try(
            Socket client = new Socket(IP,PORT);
            PrintWriter outputToServer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true);
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        ) {

            serverInput = new BufferedReader(new InputStreamReader(client.getInputStream()));

            new ClientListener().start();

            while (true){
                String inputLine = userInput.readLine();
                outputToServer.println(inputLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // thread that listen and reads constantly response from server
    static class ClientListener extends Thread{

        @Override
        public void run() {
            while (true){
                try {
                    String serverOutput = serverInput.readLine();
                    System.out.println("client: " + serverOutput);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}



