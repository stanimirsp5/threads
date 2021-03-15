package JavaWeb.Streams;
import java.io.*;

// Character streams are often "wrappers" for byte streams
// and it will read a file character by character
// not byte by byte like ByteStream
public class CharacterStream {
    public static void main(String[] args) throws IOException {
        // The most important difference is that CharacterStream uses FileReader and FileWriter for input and output
        // in place of FileInputStream and FileOutputStream unlike ByteStream

//        FileReader inputStream = null;
//        FileWriter outputStream = null;

        BufferedReader inputStream = null;
        BufferedWriter outputStream = null;

        try {
//            inputStream = new FileReader("src/JavaWeb/Streams/xanadu.txt");
//            outputStream = new FileWriter("src/JavaWeb/Streams/characteroutput.txt");
            inputStream = new BufferedReader(new FileReader("xanadu.txt"));
            outputStream = new BufferedWriter(new FileWriter("characteroutput.txt"));
            int c; // the int variable holds a character value in its last 16 bits
            while ((c = inputStream.read()) != -1) {
                outputStream.write(c);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

}
