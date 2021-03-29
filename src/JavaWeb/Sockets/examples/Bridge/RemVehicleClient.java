package JavaWeb.Sockets.examples.Bridge;

import java.net.*;
import java.io.*;
public class RemVehicleClient {
    private static final int PORT = 8080;
    private static String name;
    public static void main(String[] args)throws IOException {
        String server = null;
        InetAddress addr =  InetAddress.getByName(server);
        System.out.println("addr = " + addr);
        Socket socket = new Socket(addr, PORT);
        //Socket s=new Socket(); //s.connect(new  InetSocketAddress(host,port),timeout);
        // Guard everything in a try-finally to make
        // sure that the socket is closed:
        try {
            System.out.println("socket = " + socket);
            BufferedReader in =
                    new BufferedReader(
                            new InputStreamReader(socket.getInputStream()));
            BufferedReader sin =  new BufferedReader(
                    new InputStreamReader(System.in));

            // Output is automatically flushed
            // by PrintWriter:
            PrintWriter out =   new PrintWriter(
                    new BufferedWriter(  new OutputStreamWriter(
                            socket.getOutputStream())),true);
            System.out.print("Your name please:");
            name = sin.readLine();
            out.println("REMOTE CLIENT: "+name);
            for(;;) {
                System.out.print("input L(left->right) or R(right->left) [empty for finish] :");
                String s = sin.readLine();
                if(s.length()==0) break;
                if(!(s.equalsIgnoreCase("L"))&&!(s.equalsIgnoreCase("R"))) {
                    System.out.println("L or R or 'Enter' please");
                    continue;
                }

                out.println(s.equalsIgnoreCase("L")?"Left":"Right");
                String servM;
                do {
                    servM=in.readLine();    // message from server
                    System.out.println(servM);
                }while(!servM.startsWith("leave"));  //variable number of messages

            }
            out.println("END");
        }
        finally {
            System.out.println("closing...");
            socket.close();
        }
    }
}
