package ExamWorkshop.InspectorsOnBridge.Chat;

import ExamWorkshop.InspectorsOnBridge.Gui.ChatWindowFactory;
import javafx.application.Platform;
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


            new ClientListener(this).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String msg){ // called from ChatWindowFactory by pressing the send button
        msg = "Inspector #" + inspectorNumber + "|" + msg;

        ProtocolStates state = new InspectorWorkProtocol().getCurrentState();

        if(state == ProtocolStates.FREECHAT) {
            currentInspector = inspectorNumber;
        }
        outputToServer.println(msg);
    }

    // thread that listen and reads constantly response from server
    static class ClientListener extends Thread {

        private final Inspector inspector;

        public ClientListener(Inspector inspector) {
            this.inspector = inspector;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    String serverOutput = inspector.serverInput.readLine();

                    try {
                        Thread.sleep(400);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    disableChatWindow(inspector);

                    // print msg to all text areas
                    inspector.chatWindowFactory.writeToTextArea(serverOutput);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void disableChatWindow(Inspector inspector){
            ProtocolStates state = new InspectorWorkProtocol().getCurrentState();

            if (inspector.currentInspector == inspector.inspectorNumber) {
                //  disable only when in "Inspector chat" mode
                if (state == ProtocolStates.INSPECTORCHAT) {
                    inspector.chatWindowFactory.disable(true);
                } else {
                    inspector.chatWindowFactory.disable(false);
                    inspector.currentInspector = 0;
                }
            }
        }
    }
}



