package JavaWeb.protocols.examples;

import java.io.*;
import java.net.*;

public class ReadURL {
    public static void main(String[] a){
        try{
            URL url = new URL("https://nbu.bg");
            BufferedReader br = new BufferedReader(new
                    InputStreamReader(url.openStream()));
            String line;
            while((line=br.readLine()) !=null) {
                System.out.println(line);
            }
            br.close();
        }catch(MalformedURLException me) {
            System.out.println("MalformedURLException: "+ me);
        }catch (IOException ioe){
            System.out.println("IOException: "+ ioe);
        }
    }
}
