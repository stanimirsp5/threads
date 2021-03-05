package JavaWeb.protocols.examples;
import java.net.MalformedURLException;
import java.net.URL;
// URL class contains methods for open and create Web pages. Doesn't have variables.
public class URLClassTest
{
    public static void main(String[] args)
            throws MalformedURLException {

        // creates a URL with string representation.
        URL url1 =
                new URL("https://ff.tu-sofia.bg"+
                        "/sites/default/files/documents_ff/FICHE_INSCRIPION-IC_002.pdf");

        // creates a URL with a protocol,host name,and path
        URL url2 = new URL("https", "ff.tu-sofia.bg",
                "/futurs-etudiants/");

        //create a URL with another URL and path
        URL url3 = new URL("https://ff.tu-sofia.bg");
        URL url4 = new URL(url3, "/futurs-etudiants/");


        // print the String representation of the URL.
        System.out.println(url1.toString());
        System.out.println(url2.toString());
        System.out.println();
        System.out.println("Different components of the URL");

        // Retrieve the protocol for the URL
        System.out.println("Protocol url1: " + url1.getProtocol());

        // Retrieve the host name of the URL
        System.out.println("Hostname url1:  " + url1.getHost());

        // Retrieve the default port
        System.out.println("Default port url1: " + url1.getDefaultPort());


        // Retrieve the path of URL
        System.out.println("Path url4: " + url4.getPath());

        // Retrieve the file name
        System.out.println("File url1: " + url1.getFile());
    }
}