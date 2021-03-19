package JavaWeb.Sockets.exersices;
//https://www.javatpoint.com/java-serversocket-accept-method
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ServerAppAcceptMethod1 {
    public static void main(String[] args)throws IOException {
        int num,temp;
        String s;
        ServerSocket s1=new ServerSocket(1408);
        Socket ss=s1.accept();
        Scanner sc=new Scanner(ss.getInputStream());
        s=ss.toString();
        num =sc.nextInt();
        temp=num*num;
        PrintStream p=new PrintStream(ss.getOutputStream());
        p.println(temp);
        System.out.println("Server started.. ");

    }
}