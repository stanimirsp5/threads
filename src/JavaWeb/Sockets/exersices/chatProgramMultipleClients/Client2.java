package JavaWeb.Sockets.exersices.chatProgramMultipleClients;

import java.io.*;
import java.net.Socket;

public class Client2 {
    public static final int PORT = 4444;
    public static final String IP = "127.0.0.1";// "127.0.0.1"; 192.168.100.17

    public static void main(String[] args) {

        try(

                Socket socket = new Socket(IP, PORT);

                BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
                PrintWriter writeToServer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true); // flush to send data immediately
                // If you are writing through a network connection, you may want to call flush() so that the data gets sent through the network immediately.
                BufferedReader messageFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        ){
            String readUserInput;
            while (true){
                readUserInput = userInput.readLine();
                writeToServer.println(readUserInput);

                System.out.println(messageFromServer.readLine());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
