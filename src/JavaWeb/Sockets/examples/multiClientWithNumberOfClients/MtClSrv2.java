package JavaWeb.Sockets.examples.multiClientWithNumberOfClients;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;

class Names{
    private   ArrayList<String>  names;
    public Names(){
        names = new ArrayList<String>(10);
    }
    public synchronized void add(String name){
        names.add(name);
    }
    public synchronized void rm(String name){
        names.remove(name);
    }
    public synchronized String get(){
        String rez="\t"+names.size()+" clients connected: ";
        Iterator<String> itr = names.iterator();
        while(itr.hasNext()) {
            rez+=(String)itr.next()+"; ";
        }
        return rez;
    }
}

class ServeOneClient extends Thread {
    private Names nm;
    private String name;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    public ServeOneClient(Socket s, Names nm)  throws IOException {
        socket = s;
        this.nm = nm;
        in = new BufferedReader(
                new InputStreamReader(
                        socket.getInputStream()));
        // Enable auto-flush:
        out = new PrintWriter( new BufferedWriter(
                new OutputStreamWriter(
                        socket.getOutputStream()
                )
        ),
                true);
        name = in.readLine();
        // If any of the above calls throw an
        // exception, the caller is responsible for
        // closing the socket. Otherwise the thread
        // will close it.
        start();    // Calls run()
    }
    public void run() {
        try {
            nm.add(name);
            System.out.println("connecting client "+name);
            while (true) {
                String str = in.readLine();
                if (str.equals("END")) break;
                System.out.println("Echoing: " + str+nm.get());
                out.println(str+nm.get());
            }
            System.out.println("closing client "+name);
        } catch (IOException e) {  }
        finally {
            nm.rm(name);
            try {
                socket.close();
            } catch(IOException e) {}
        }
    }
}

public class MtClSrv2 {
    static final int PORT = 8085;
    public static void main(String[] args) throws IOException {
        ServerSocket s = new ServerSocket(PORT);
        Names nm = new Names();
        System.out.println("Server Started");
        try {
            while(true) {
                // Blocks until a connection occurs:
                Socket socket = s.accept();
                try {
                    new ServeOneClient(socket,nm);
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
