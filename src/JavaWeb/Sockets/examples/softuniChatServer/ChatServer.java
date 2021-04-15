package JavaWeb.Sockets.examples.softuniChatServer;

import java.io.*;
import java.net.*;
import java.util.Vector;

public class ChatServer {
    public static void main(String[] args)
            throws IOException {
        ServerSocket serverSocket = new ServerSocket(5555);
        System.out.println("Chat server started on port " +
                serverSocket.getLocalPort());

        ServerMsgDispatcher dispatcher =
                new ServerMsgDispatcher();
        dispatcher.start();

        while (true) {
            Socket clientSocket = serverSocket.accept();
            ClientListener2 clientListener =
                    new ClientListener2(clientSocket, dispatcher);
            dispatcher.addClient(clientSocket);
            clientListener.start();
        }
    }
}

class ClientListener2 extends Thread {
    private Socket mSocket;
    private ServerMsgDispatcher mDispatcher;
    private BufferedReader mSocketReader;

    public ClientListener2(Socket aSocket, ServerMsgDispatcher aServerMsgDispatcher) throws IOException {
        mSocket = aSocket;
        mSocketReader = new BufferedReader(
                new InputStreamReader(
                        mSocket.getInputStream()));
        mDispatcher = aServerMsgDispatcher;
    }

    public void run() {
        try {
            while (!isInterrupted()) {
                String msg = mSocketReader.readLine();
                if (msg == null)
                    break;
                mDispatcher.dispatchMsg(mSocket, msg);
            }
        } catch (IOException ioex) {
            System.err.println("Error communicating " +
                    "with some of the clients.");
        }
        mDispatcher.deleteClient(mSocket);
    }
}

class ServerMsgDispatcher extends Thread {
    private Vector mClients = new Vector();
    private Vector mMsgQueue = new Vector();

    public synchronized void addClient(Socket aClientSocket) {
        mClients.add(aClientSocket);
    }

    public synchronized void deleteClient(Socket aClientSock) {
        int i = mClients.indexOf(aClientSock);
        if (i != -1) {
            mClients.removeElementAt(i);
            try {
                aClientSock.close();
            } catch (IOException ioe) {
                // Probably the socket already is closed
            }
        }
    }

    public synchronized void dispatchMsg(Socket aSocket, String aMsg) {
        String IP = aSocket.getInetAddress().getHostAddress();
        String port = "" + aSocket.getPort();
        aMsg = IP + ":" + port + " : " + aMsg + "\n\r";
        mMsgQueue.add(aMsg);
        notify();
    }

    private synchronized String getNextMsgFromQueue() throws InterruptedException {
        while (mMsgQueue.size() == 0)
            wait();
        String msg = (String) mMsgQueue.get(0);
        mMsgQueue.removeElementAt(0);
        return msg;
    }

    private synchronized void sendMsgToAllClients(String aMsg) {
        for (int i=0; i<mClients.size(); i++) {
            Socket socket = (Socket) mClients.get(i);
            try {
                OutputStream out = socket.getOutputStream();
                out.write(aMsg.getBytes());
                out.flush();
            } catch (IOException ioe) {
                deleteClient(socket);
            }
        }
    }

    public void run() {
        try {
            while (true) {
                String msg = getNextMsgFromQueue();
                sendMsgToAllClients(msg);
            }
        } catch (InterruptedException ie) {
            // Thread interrupted. Do nothing
        }
    }
}