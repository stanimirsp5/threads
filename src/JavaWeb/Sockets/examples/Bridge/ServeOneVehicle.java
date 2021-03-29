package JavaWeb.Sockets.examples.Bridge;


import java.io.*;
import java.net.*;

class ServeOneVehicle extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Bridge b;
    private String name="";
    public ServeOneVehicle(Socket s, Bridge b)  throws IOException {
        socket = s;
        in = new BufferedReader(
                new InputStreamReader(
                        socket.getInputStream()));
        out =   new PrintWriter(
                new BufferedWriter(  new OutputStreamWriter(
                        socket.getOutputStream())),true);
        this.b=b;
        System.out.println("\t\t\tnew remote connection");
        start();    // Calls run()
    }
    public void run() {
        String leftToRight;
        boolean lr;
        try {
            name = in.readLine();
            while (true) {
                leftToRight = in.readLine();
                if (leftToRight.equals("END")) break;
                if(leftToRight.equalsIgnoreCase("Left"))lr=true;
                else lr=false;
                (new Vehicle(name,lr, b,out)).start();
            }
            System.out.println("closing connection to "+name);
        } catch (IOException e) {  }
        finally {
            try {
                socket.close();
            } catch(IOException e) {}
        }
    }
}