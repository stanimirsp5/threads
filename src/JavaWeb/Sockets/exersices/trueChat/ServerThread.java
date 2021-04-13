package JavaWeb.Sockets.exersices.trueChat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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

            String inputLine;
            while (true){
                inputLine = clientInput.readLine();
                printToAllClients(inputLine);
                //output.println(inputLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void printToAllClients(String outputString){
        for (ServerThread st: threadList){
            try {
                PrintWriter output = new PrintWriter(st.socket.getOutputStream(),true);
                output.println(outputString);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
