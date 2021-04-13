package JavaWeb.Sockets.exersices.trueChat;

import java.io.*;
import java.net.Socket;

public class Client {
    public static final int PORT = 4444;
    public static final String IP = "127.0.0.1";

    public static void main(String[] args) {

        try(
                Socket client = new Socket(IP, PORT);
                PrintWriter outputToServer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()),true);
                BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
                BufferedReader serverInput = new BufferedReader(new InputStreamReader(client.getInputStream()));


        ){
            String inputLine;

            while (true){
                inputLine = userInput.readLine();
                outputToServer.println(inputLine);
                String msg = serverInput.readLine();
                System.out.println(msg);
            }


        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}
