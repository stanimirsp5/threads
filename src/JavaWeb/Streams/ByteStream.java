package JavaWeb.Streams;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
public class ByteStream {
    public static void main(String[] args) throws IOException {

        FileInputStream in = null;
        FileOutputStream out  = null;

        try {
            in = new FileInputStream("src/JavaWeb/Streams/xanadu.txt");
            out = new FileOutputStream("src/JavaWeb/Streams/outagain.txt");
            int c; // the int variable holds a byte value in its last 8 bits

            while ((c = in.read()) != -1) {
                out.write(c);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}
