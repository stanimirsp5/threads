package JavaWeb.Sockets.examples.mailSend;

import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MailSend{
    private static String messageSys[]= new String[4],
            messageData[]=new String[5],hostName=null,
            sender = "<myName@myDomain.bg>";
    private static InetAddress mServer= null;
    public static void initStrings(){
        messageSys[0]="HELO "+hostName+"\r\n";
        messageSys[1]="MAIL FROM: "+sender+ "\r\n";
        messageSys[2]="RCPT TO: ";
        messageSys[3]="DATA\r\n";
        messageData[0]="From: "+sender+"\r\n";
        messageData[1]="To:";
        messageData[2]="Subject: ";
        messageData[3]= "";
        messageData[4]=".\r\n";
    }
    public static void init(){
        try{
            mServer=InetAddress.getByName("myMailServer.bg");
            hostName= InetAddress.getLocalHost().getHostName();
        }catch(Exception e){System.out.println("Exception"+e); }
        initStrings();
    }
    public static void putData(String arg[]){
        messageSys[2]+="<"+arg[0]+">\r\n";
        messageData[1]+=arg[0]+"\r\n";
        messageData[2]+= arg[1]+"\r\n";
        messageData[3]= arg[2]+"\r\n";

    }
    public static void send (){
        try{                                 // или c timeout 10000 ms:
            Socket s=null;		    // Socket s = new Socket();
            s = new Socket(mServer,25);  // s.connect(new InetSocketAddress(
            //    mserver,25),10000);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(s.getInputStream()));
            PrintWriter out = new PrintWriter(s.getOutputStream());
            System.out.println(in.readLine());
            for(int i=0; i<messageSys.length;i++){
                out.print(messageSys[i]);
                System.out.println(messageSys[i]);
                out.flush();
                System.out.println(in.readLine());
            }
            for(int i=0;i<messageData.length;i++){
                out.print(messageData[i]);
                System.out.println(messageData[i]);
                out.flush();
            }
            System.out.println(in.readLine());
            s.close();
        }
        catch(Exception e) {System.out.println(e); }
        initStrings();
    }
}


class Gui{
    JTextArea ta;
    JTextField sb,to;
    JLabel ls,lt;
    JButton send;
    Panel p= new Panel();
    Gui(JFrame f){
        ta = new JTextArea(10,20);
        send = new JButton("send");
        to = new JTextField("",20);
        lt= new JLabel("To:");
        sb = new JTextField("",20);
        ls = new JLabel("Subject:");
        send.addActionListener(new S());
        p.add(lt);
        p.add(to);
        p.add(ls);
        p.add(sb);
        f.add(p,BorderLayout.NORTH);
        f.add(ta, BorderLayout.CENTER);
        f.add(send, BorderLayout.SOUTH);
        MailSend.init();
    }
    class S implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String message[]= {to.getText(),sb.getText(),ta.getText()};
            MailSend.putData(message);
            MailSend.send();
        }
    }

    public static void main(String args[]){
        JFrame frame =new JFrame("Mail Sender");
        Gui gui = new Gui(frame);
        frame.setSize(600,400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}