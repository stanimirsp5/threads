package JavaWeb.Sockets.examples.moreThanOnClient;

// Client with name
import java.net.*;
import java.io.*;

public class ClientWithName {
    public static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        // Passing null to getByName() produces the
        // special "Local Loopback" IP address, for
        // testing on one machine w/o a network:
        // Alternatively, you can use
        // the address or name:
        // InetAddress addr = InetAddress.getByName("127.0.0.1");
        // InetAddress addr = InetAddress.getByName("localhost");

        String server = null;
        InetAddress addr = InetAddress.getByName(server);
        System.out.println("addr = " + addr);
        Socket socket = new Socket(addr, PORT);
        // Guard everything in a try-finally to make
        // sure that the socket is closed:
        try {
            System.out.println("socket = " + socket);
            BufferedReader sin = new BufferedReader(
                    new InputStreamReader(System.in));
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            // Output is automatically flushed
            // by PrintWriter:
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())), true);
            System.out.print("Your name: ");
            String name = sin.readLine();
            for (int i = 0; i < 10; i++) {
                System.out.println("input a line [empty for finish] :");
                String s = sin.readLine();
                if (s.length() == 0) break;
                out.println(name + ":" + s);
                String str = in.readLine();
                System.out.println(str);
            }
            out.println("END");
        } finally {
            System.out.println("closing...");
            socket.close();
        }
    }
}