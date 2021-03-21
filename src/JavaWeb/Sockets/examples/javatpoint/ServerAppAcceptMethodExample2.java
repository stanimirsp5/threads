package JavaWeb.Sockets.examples.javatpoint;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ServerAppAcceptMethodExample2 {
    public static void main(String[] args)throws IOException {
        // TODO Auto-generated method stub
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
        System.out.println(" Client information: "+ s);
    }
}