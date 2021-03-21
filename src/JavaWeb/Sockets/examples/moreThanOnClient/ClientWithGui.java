package JavaWeb.Sockets.examples.moreThanOnClient;

import java.net.*;
        import java.awt.*;
        import java.io.*;
        import java.awt.event.*;

public class ClientWithGui {
    private BufferedReader in;
    private PrintWriter out;
    private InetAddress addr;
    private Socket socket;
    private String name;
    private Gui g;
    ClientWithGui(Frame f){
        g = new Gui(f);
    }

    class Gui{
        TextArea serv;
        TextField cl;
        Gui (Frame f){
            f.setLayout(new BorderLayout());
            serv = new TextArea(20,10);
            serv.setEditable(false);
            cl = new TextField(30);
            f.add("Center",serv);
            f.add("South",cl);
            cl.addActionListener(new SrvL());
        }
        class SrvL implements ActionListener{
            public void actionPerformed(ActionEvent e){
                try{
                    String st=cl.getText();
                    send(st);
                    cl.setText("");
                    serv.append(receive()+"\n");
                }
                catch (Exception ex){
                    System.out.println("exception: "+ex);
                    System.out.println("closing...");
                    try{
                        socket.close();
                    }
                    catch (Exception expt){
                        System.out.println(expt);
                    }
                }
            }
        }
    }
    public void init(){
        try{
            String server = null;
            InetAddress addr = InetAddress.getByName(server);
            System.out.println("addr = " + addr);
            socket = new Socket(addr, 8080);
            System.out.println("socket = " + socket);
            BufferedReader sin = new BufferedReader(
                    new InputStreamReader(System.in));
            in =  new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            // Output is automatically flushed
            // by PrintWriter:
            out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())),true);
            System.out.print("Your name: ");
            name = sin.readLine();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("closing...");
            System.exit(4);
            try{
                socket.close();
            }
            catch(IOException ie){
                ie.printStackTrace();
                System.exit(3);
            }
        }

    }
    void send(String s){
        if(s.length()==0){
            out.println("END");
            System.out.println("closing...");
            try{
                socket.close();
            }
            catch (Exception expt){
                expt.printStackTrace();
            }
            System.exit(0);
        }
        else out.println(name+": "+s);

    }
    String receive(){
        String s="";
        try{
            s= in.readLine();
        }
        catch (Exception expt){
            expt.printStackTrace();
            System.exit(2);
        }
        return s;
    }

    public static void main(String[] args )throws IOException{
        Frame frame =new Frame();
        ClientWithGui cl = new ClientWithGui(frame);
        cl.init();
        frame.setTitle(cl.name);
        frame.setSize(500,300);
        frame.setVisible(true);
    }
}