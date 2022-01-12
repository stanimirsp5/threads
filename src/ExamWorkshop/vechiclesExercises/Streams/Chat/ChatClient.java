package ExamWorkshop.vechiclesExercises.Streams.Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    public static int port;
    public static void main(String[] args) throws IOException {
        System.out.println("Enter client port:");
        Scanner userInputSettings = new Scanner(System.in);
        port = userInputSettings.nextInt();
        runClient();
    }

    private static void runClient() throws IOException{

        try(
            Socket s = new Socket("127.0.0.1",port);
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);

            BufferedReader uin = new BufferedReader(new InputStreamReader(System.in));

        ){
            new Thread(new ReaderThread(s)).start();
            String userInput;

            while (true){
                userInput = uin.readLine();
                if(userInput.equals("2"))break;
                out.println(userInput); // write user input to server for other clients

            }
        }
    }
    static class ReaderThread implements Runnable {
        Socket socket;
        public ReaderThread(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            try(
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ){
                while (true){
                    System.out.println("response " + in.readLine());// waits for response from server // write input from other clients in console
                }
            }catch (IOException e){
                e.printStackTrace();
            }


        }
    }
}
