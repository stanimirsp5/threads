package ExamWorkshop.InspectorsOnBridge.Chat;

import ExamWorkshop.InspectorsOnBridge.Gui.ChatWindowFactory;
import ExamWorkshop.InspectorsOnBridge.Gui.MainGui;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;

public class Inspector{
    public static final int PORT = 4444;
    public static final String IP = "127.0.0.1";
    private static BufferedReader serverInput;
    int inspectorNumber;

    public Inspector(int inspectorNumber) {
        this.inspectorNumber = inspectorNumber;
    }

    public void createInspector() {
    //public static void main(String[] args) {
        ChatWindowFactory chatWindowFactory = new ChatWindowFactory();

        Platform.runLater(() -> {
            chatWindowFactory.initChatWindow(inspectorNumber);
        });

        // output -> send to server, input -> receive from server
        try(
            Socket client = new Socket(IP,PORT);
            PrintWriter outputToServer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true);
            //BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in)); // get from UI  tf1.getText()
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
                    // print msg to all text areas
                    // textArea.appendText(newLine + tf1.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}



