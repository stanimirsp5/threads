package JavaWeb.Streams.exercises;

import java.io.*;

public class StreamTest {
    public static void main(String[] args) throws IOException {

        //printWriter();
        bufferedWriter();
        bufferedReader();
    }

    public static void bufferedWriter() throws IOException {
        PrintWriter printWriter = new PrintWriter("F:\\Stanimir-work\\Projects\\threads\\src\\JavaWeb\\Streams\\exercises\\testout.txt"); // new File("D:\\testout.txt")
        BufferedWriter br = new BufferedWriter(printWriter);
        br.write("buffered writer test");
        br.close();
    }
    public static void bufferedReader() throws IOException {
        FileReader fileReader = new FileReader("F:\\Stanimir-work\\Projects\\threads\\src\\JavaWeb\\Streams\\exercises\\testout.txt"); // new File("D:\\testout.txt")
        // BufferedReader enables us to minimize the number of I/O operations by reading chunks of characters and storing them in an internal buffer.
        // While the buffer has data, the reader will read from it instead of directly from the underlying stream.
        BufferedReader br = new BufferedReader(fileReader);
        br.read();
        br.close();
    }
    public static void printWriter(){

        PrintWriter printWriter = new PrintWriter(System.out); // new File("D:\\testout.txt")
        // BufferedInputStream reads the data in the buffer as bytes by using InputStream.
        // BufferedReader reads the text but not as bytes and BufferedReader is efficient reading of characters,arrays and lines.
        printWriter.write("Test print writer");
        printWriter.flush();
        printWriter.close();
    }

}
