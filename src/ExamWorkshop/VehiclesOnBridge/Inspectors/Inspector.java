package ExamWorkshop.VehiclesOnBridge.Inspectors;

import ExamWorkshop.VehiclesOnBridge.Helpers.ExceptionLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Inspector implements Runnable{
    public int port = 6666;
    public int number;
    public ChatUi chatUi;
    private PrintWriter outputStream;

    public Inspector(int number){
        this.number = number;
    }

    public void runClient() throws IOException {
        chatUi = new ChatUi(this);
        chatUi.start();

        Socket socket = new Socket("127.0.0.1", port);
        new Thread(new ReaderThread(socket,this)).start();
        // send to clients via server
        outputStream = new PrintWriter(socket.getOutputStream(), true);

    }

    public void send(String message) throws IOException{
        outputStream.println(number +": " + message); // write user input to server for other clients
    }

    @Override
    public void run() {
        try {
            runClient();
        } catch (IOException e) {
            ExceptionLogger.log(e);
        }
    }

    static class ReaderThread implements Runnable{
        Socket socket;
        Inspector inspector;

        /**
         * Listen for server output and prints it on the console.
         * @param socket
         */
        public ReaderThread(Socket socket, Inspector inspector){
            this.socket = socket;
            this.inspector = inspector;
        }

        @Override
        public void run() {
            try(
                BufferedReader sin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ) {
                while (true){
                    inspector.chatUi.printToClients(sin.readLine()); // waits for response from server // write input from other clients in console
                }
            } catch (IOException e) {
                ExceptionLogger.log(e);
            }
        }
    }
}
