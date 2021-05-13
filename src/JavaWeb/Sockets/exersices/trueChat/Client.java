package JavaWeb.Sockets.exersices.trueChat;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static final int PORT = 4444;
    public static final String IP = "127.0.0.1";
    private static BufferedReader serverInput;
    private static String clientName;

    public static void main(String[] args) {

        //System.out.println("Enter client name");
        //Scanner in = new Scanner(System.in);
        clientName = Thread.currentThread().getName(); //in.nextLine();

        try(
            Socket client = new Socket(IP, PORT);
            PrintWriter outputToServer = new PrintWriter(new OutputStreamWriter(client.getOutputStream()),true);
            BufferedReader userInputStream = new BufferedReader(new InputStreamReader(System.in));
        ){

            serverInput = new BufferedReader(new InputStreamReader(client.getInputStream()));

            new Thread(new ClientListener()).start();

            while (true){
                String userInput = userInputStream.readLine();
                outputToServer.println(userInput);
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    // thread that reads constantly response from server
    static class ClientListener implements Runnable{

        @Override
        public void run() {
            while (true){
                try {
                    String serverOutput = serverInput.readLine();
                    System.out.println(serverOutput);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

