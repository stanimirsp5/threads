package JavaWeb.Sockets.exersices.trueChat;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread implements Runnable{
    Socket socket;
    ArrayList<ServerThread> threadList;

    public ServerThread(Socket socket, ArrayList<ServerThread> threads){
        this.socket = socket;
        this.threadList = threads;
    }

    @Override
    public void run() {

        try(

                BufferedReader clientInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        ) {

            TrueChatProtocol tcp = new TrueChatProtocol();
            String msg = tcp.processInput(null, null);
            printToAllClients(msg);

            while (true){
                String inputLine = clientInput.readLine();

                msg = tcp.processInput(inputLine, Thread.currentThread().getName());

                printToAllClients(msg);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void printToAllClients(String outputString){
        for (ServerThread st: threadList){
            //st.output.println(outputString);
            // get stream from other clients
            try {
                PrintWriter out = new PrintWriter(st.socket.getOutputStream(),true);
                out.println(outputString);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
