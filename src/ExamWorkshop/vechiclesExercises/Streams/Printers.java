package ExamWorkshop.vechiclesExercises.Streams;

import java.io.*;

public class Printers {
    public static void main(String[] args) throws IOException {
        readData();

    }
    //writer -writing to character streams.
    //reader - reading character streams. BufferedReader reads in characters

    //output stream - write data to a destination
    //input stream - read data from a source. BufferedInputStream reads in bytes
    public static void writeData() throws IOException {
        FileWriter fo = new FileWriter("/Users/stanimir/Projects/Multithreading/src/ExamWorkshop/vechiclesExercises/Streams/generatedData.txt");
        BufferedWriter buffer = new BufferedWriter(fo);
        buffer.write("Welcome to javaTpoint.");
        buffer.close();
    }
    public static void readData() throws IOException {
        FileReader fr = new FileReader("/Users/stanimir/Projects/Multithreading/src/ExamWorkshop/vechiclesExercises/Streams/generatedData.txt");
        BufferedReader buffer = new BufferedReader(fr);

        int i;
        while ((i = buffer.read()) != -1 ){
            System.out.print((char) i);
        }

        buffer.close();
        fr.close();
    }
    public static void readDataStream() throws IOException {
        FileInputStream fr = new FileInputStream("/Users/stanimir/Projects/Multithreading/src/ExamWorkshop/vechiclesExercises/Streams/generatedData.txt");
        BufferedInputStream buffer = new BufferedInputStream(fr);

        int i;
        while ((i = buffer.read()) != -1 ){
            System.out.print((char) i);
        }

        buffer.close();
        fr.close();
    }
}
