package JavaWeb.Sockets.exersices.chatProgramMultipleClients;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static final int PORT = 4444;
    public static final String IP = "192.168.100.17";// "127.0.0.1";

    public static void main(String[] args) {

        try(

                Socket socket = new Socket(IP, PORT);
                BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

                ){

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
