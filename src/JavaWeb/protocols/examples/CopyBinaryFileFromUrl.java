package JavaWeb.protocols.examples;

import java.io.*;
import java.net.*;
// local copy of image from internet
public class CopyBinaryFileFromUrl {
    public static void main(String arg[]) {
        int abyte;
        try{
            URL url = new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/d/d9/"+
                    "Big_Bear_Valley%2C_California.jpg/1200px-Big_Bear_Valley%2C_California.jpg");
            InputStream fi= url.openStream();
            FileOutputStream fo= new FileOutputStream("picture.jpg");
            System.out.println("srarting ...");
            while((abyte= fi.read())!=-1)fo.write(abyte);
            fi.close();
            fo.close();
            System.out.println("created");

        }catch(MalformedURLException me) {
            System.out.println("MalformedURLException: "+ me);
        }catch (IOException ioe){
            System.out.println("IOException: "+ ioe);
        }

    }
}
