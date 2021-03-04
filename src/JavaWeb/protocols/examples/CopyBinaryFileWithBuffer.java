package JavaWeb.protocols.examples;

import java.io.*;
import java.net.*;

public class CopyBinaryFileWithBuffer {
    public static void main(String arg[]) throws MalformedURLException, IOException{
        byte buf[]=new byte[1024];
        int len;
        URL url = new URL("https://upload.wikimedia.org/wikipedia/commons/thumb/d/d9/"+
                "Big_Bear_Valley%2C_California.jpg/1200px-Big_Bear_Valley%2C_California.jpg");
        InputStream fi= url.openStream();
        FileOutputStream fo= new FileOutputStream("picture1.jpg");
        System.out.println("srarting ...");
        while((len= fi.read(buf))!=-1)fo.write(buf,0,len);
        fi.close();
        fo.close();
        System.out.println("created");
    }
}
