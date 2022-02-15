package ExamWorkshop.vechiclesExercises.CarsOnBridge.Inspectors;

import ExamWorkshop.InspectorsOnBridge.Chat.InspectorWorkProtocol;
import ExamWorkshop.InspectorsOnBridge.Chat.ProtocolStates;
import ExamWorkshop.InspectorsOnBridge.Gui.ChatWindowFactory;
import javafx.application.Platform;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Inspector implements Runnable{
    public static int port = 6666;
    public static int inspectorNumber= 1;
    private ChatUi chatUi;

//    public static void main(String[] args) throws IOException {
//        System.out.println("Enter inspector name:");
//        Scanner userIn = new Scanner(System.in);
//        name = "Inspector #" + userIn.nextLine();
//
//        runClient();
//    }

    public void runClient() throws IOException {
        chatUi = new ChatUi(this);

        Platform.startup(() -> {
            chatUi.initChatWindow(inspectorNumber);
        });

        try(
                Socket s = new Socket("127.0.0.1", port);
                //receive from server
                BufferedReader uin = new BufferedReader(new InputStreamReader(System.in));
                // send to clients via server
                PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        ){
            new Thread(new ReaderThread(s)).start();
            String userInput;

            while (true){
                //System.out.print(name+": ");
                userInput = uin.readLine();
                out.println(inspectorNumber +": " + userInput); // write user input to server for other clients
            }

        }
    }

    @Override
    public void run() {
        try {
            runClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ReaderThread implements Runnable{
        Socket socket;

        public ReaderThread(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            try(
                BufferedReader sin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ) {
                while (true){
                    System.out.println(sin.readLine());// waits for response from server // write input from other clients in console
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
