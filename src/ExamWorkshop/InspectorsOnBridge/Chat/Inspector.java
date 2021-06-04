package ExamWorkshop.InspectorsOnBridge.Chat;

import ExamWorkshop.InspectorsOnBridge.Bridge.Bridge;
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
    private BufferedReader serverInput;
    private PrintWriter outputToServer;
    private ChatWindowFactory chatWindowFactory;
    int inspectorNumber;
    int currentInspector;
    public Inspector(int inspectorNumber) {
        this.inspectorNumber = inspectorNumber;
    }

    public void createInspector() {
        chatWindowFactory = new ChatWindowFactory(this);

        Platform.runLater(() -> {
            chatWindowFactory.initChatWindow(inspectorNumber);
        });

        // output -> send to server, input -> receive from server
        try{
            Socket client = new Socket(IP,PORT);
            //write to server
            outputToServer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true);

            //reads from server
            serverInput = new BufferedReader(new InputStreamReader(client.getInputStream()));


            new ClientListener().start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String msg){
        msg = "Inspector #" + inspectorNumber + "|" + msg;

        ProtocolStates state = new InspectorWorkProtocol().getCurrentState();

        if(state == ProtocolStates.FREECHAT) {
            currentInspector = inspectorNumber;
        }
        outputToServer.println(msg);
    }

    // thread that listen and reads constantly response from server
    class ClientListener extends Thread{

        @Override
        public void run() {
            while (true){
                try {
                    String serverOutput = serverInput.readLine();
                    try {
                        Thread.sleep(400);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    ProtocolStates state = new InspectorWorkProtocol().getCurrentState();

                    if(currentInspector == inspectorNumber) {
                        //  disable only when in "Inspector chat" mode
                        if(state == ProtocolStates.INSPECTORCHAT) {
                            chatWindowFactory.disable(true);
                        }else {
                            chatWindowFactory.disable(false);
                            currentInspector = 0;
                        }
                    }

                    // print msg to all text areas
                    chatWindowFactory.writeToTextArea(serverOutput);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}



