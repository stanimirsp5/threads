package ExamWorkshop.vechiclesExercises.Streams.Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread{
    public Socket socket;
    public ArrayList<Socket> clients;

    public ServerThread(Socket socket, ArrayList<Socket> clients)  {

        this.socket = socket;
        this.clients = clients;
    }

    @Override
    public void run() {
        try(
                //get input message from client
                // get output message to client
                PrintWriter out = new PrintWriter(socket.getOutputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        ) {
            String fromClient = null;
            while ((fromClient = in.readLine()) != null){
//                for (client : clients) {
//                    client.inp
//                }
                out.println(fromClient);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
