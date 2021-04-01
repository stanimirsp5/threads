package Networking.ChatServerDifferentRooms;

import java.io.*;
import java.net.*;
class ServeOneClient extends Thread {
    private Socket socket;
    private BufferedReader in;
    Clients clientWt;
    private PrintWriter out;
    private int room=-1;
    public ServeOneClient(Socket s,Clients[] clientAr)  throws IOException {
        System.out.println("new client apeared");
        socket = s;
        in = new BufferedReader(
                new InputStreamReader(  socket.getInputStream()));
        out = new PrintWriter( new BufferedWriter( new OutputStreamWriter(
                socket.getOutputStream()  )   ),    true);
        room = Integer.parseInt(in.readLine());
        System.out.println("room:"+room);
        clientWt=clientAr[room];
        clientWt.addElement((out ));
        System.out.println("clientWt.size room: " +room+" ->" +clientWt.size());
        start();    // Calls run()
    }
    public void run() {
        try {
            while (true) {
                String str = in.readLine();
                if (str.equals("END")) break;
                System.out.println("Echoing: " + str);
                System.out.println("clientWt.size room: " +room+" ->" +clientWt.size());
                for(int i =0;i< clientWt.size();i++){
                    clientWt.elementAt(i).println(str);
                }
            }
            System.out.println("closing...");
        } catch (IOException e) {  }
        finally {
            try {
                socket.close();
            } catch(IOException e) {}
        }
        clientWt.removeElement(out);
        System.out.println("clientWt.size room: " +room+" ->" +clientWt.size());
    }
}
class Chat {
    static final int PORT = 9080;
    public static void main(String[] args) throws IOException {
        ServerSocket s = new ServerSocket(PORT);
        System.out.println("Chat Server Started");
        Clients clientAr[]= new Clients[6];
        for(int i=0;i<clientAr.length;i++){
            clientAr[i]=new Clients();
        }
        try {
            while(true) {
                // Blocks until a connection occurs:
                Socket socket = s.accept();
                try {
                    new ServeOneClient(socket,clientAr);
                } catch(IOException e) {
                    // If it fails, close the socket,
                    // otherwise the thread will close it:
                    socket.close();
                }
            }
        } finally {
            s.close();
        }
    }
}