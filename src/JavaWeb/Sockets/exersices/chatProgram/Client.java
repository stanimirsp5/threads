package JavaWeb.Sockets.exersices.chatProgram;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static final int PORT = 4444;
    public static final String IP = "192.168.100.17";// "127.0.0.1";

    public static void main(String[] args) {

        try(

            Socket socket = new Socket(IP,PORT);

            BufferedReader systemInput = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader inputFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writeToServer = (new PrintWriter(socket.getOutputStream(), true));

        ){
            System.out.println("Client started");
            String userInput;
            while ((userInput = systemInput.readLine())!= null){
                writeToServer.println(userInput);
                //System.out.println("userInput " + userInput);
                System.out.println("userInput " + inputFromServer.readLine());

            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
