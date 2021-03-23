package JavaWeb.Sockets.exersices.chatProgramMultipleClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread {
    private Socket socket = null;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run(){
        try(
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(),true);
        ){

            String inputFromClient;
            while (true){
                inputFromClient = input.readLine();

                output.println(inputFromClient);
                System.out.println("Server received " + inputFromClient);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
