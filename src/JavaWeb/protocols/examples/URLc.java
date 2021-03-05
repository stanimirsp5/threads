package JavaWeb.protocols.examples;

import java.io.IOException;
import java.net.*;

public class URLc {
    public static void main(String[] arg) throws MalformedURLException, IOException {
        URL url = new URL("https://nbu.bg");
        URLConnection urlc = url.openConnection();
        System.out.println(urlc.getContentType());
    }

}
