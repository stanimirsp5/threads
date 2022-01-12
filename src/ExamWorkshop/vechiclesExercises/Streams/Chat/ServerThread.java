package ExamWorkshop.vechiclesExercises.Streams.Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Here server listen for user input and send it to all clients
 */
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
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            String fromClient = null;
            while ((fromClient = in.readLine()) != null){
                for (Socket client : clients) {
                    PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                    out.println(fromClient);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
