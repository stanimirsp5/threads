package ExamWorkshop.vechiclesExercises.CarsOnBridge.Inspectors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Inspector{
    public static int port = 6666;
    public static String name;

    public static void main(String[] args) throws IOException {
        System.out.println("Enter inspector name:");
        Scanner userIn = new Scanner(System.in);
        name = "Inspector #" + userIn.nextLine();

        runClient();
    }

    public static void runClient() throws IOException {

        try(
                Socket s = new Socket("127.0.0.1", port);
                //receive from server
                BufferedReader uin = new BufferedReader(new InputStreamReader(System.in));
                // send to clients via server
                PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        ){
            new Thread(new ReaderThread(s)).start();
            String userInput;

            while (true){
                userInput = uin.readLine();
                if(userInput.equals("2")) break;
                out.println(name +": " + userInput); // write user input to server for other clients
            }

        }
    }

    static class ReaderThread implements Runnable{
        Socket socket;

        public ReaderThread(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            try(
                BufferedReader sin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ) {
                while (true){
                    System.out.println(sin.readLine());// waits for response from server // write input from other clients in console
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
