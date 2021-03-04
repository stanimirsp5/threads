package JavaWeb.protocols.examples;


import java.net.*;
public class NSLookupApp {
    public static void main(String args[]) {
        try {
            if(args.length!=1){
                System.out.println("Usage: java NSLookupApp hostName");
                return;
            }
            InetAddress host = InetAddress.getByName(args[0]);
            String hostName = host.getHostName();
            System.out.println("Host name: "+hostName);
            System.out.println("IP address: "+host.getHostAddress());
        }
        catch(UnknownHostException ex) {
            System.out.println("Unknown host");
            return;
        }
    }
}