package ExamWorkshop.vechiclesExercises.Streams.Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {
    public static final int PORT = 6666;
    public static void main(String[] args) throws IOException {
        runClient();
    }

    private static void runClient() throws IOException{

        try(
            Socket s = new Socket("127.0.0.1",PORT);
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            BufferedReader uin = new BufferedReader(new InputStreamReader(System.in));

        ){
            String userInput;

            while ((userInput = uin.readLine()) != null){

                out.println(userInput); // write user input to other clients

                System.out.println("response " + in.readLine());// waits for response from server // write input from other clients in console

            }


        }


    }

}
