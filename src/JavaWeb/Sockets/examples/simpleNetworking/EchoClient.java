package JavaWeb.Sockets.examples.simpleNetworking;


/*
 * Copyright (c) 1995, 2013, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import java.io.*;
import java.net.*;

public class EchoClient {
    static final int DEFAULT_PORT = 1728;

    public static void main(String[] args) throws IOException {

//        if (args.length != 2) {
//            System.err.println(
//                    "Usage: java EchoClient <host name> <port number>");
//            System.exit(1);
//        }

       // String hostName = "192.168.100.6";//Air args[0]; //computer name or IP address
        String hostName = "192.168.100.17";//Pro args[0]; //computer name or IP address
        //String hostName = "192.168.0.18";// args[0]; //computer name or IP address
        int portNumber = DEFAULT_PORT;//Integer.parseInt(args[1]);

        try (
                Socket echoSocket = new Socket(hostName, portNumber);
                // To send data through the socket to the server, the EchoClient example needs to write to the PrintWriter.
                PrintWriter out =
                        new PrintWriter(echoSocket.getOutputStream(), true); // gets the socket output stream and opens printWriter on it
                BufferedReader in =
                        new BufferedReader(
                                new InputStreamReader(echoSocket.getInputStream())); // gets the socket input stream and opens BufferedReader on it
                //  The example uses readers and writers so that it can write Unicode characters over the socket.
                // To get the server's response, EchoClient reads from the BufferedReader object stdIn
                BufferedReader stdIn = // takes input from terminal ??
                        new BufferedReader(
                                new InputStreamReader(System.in))
        ) {
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println("echo: " + in.readLine());
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                    hostName);
            System.exit(1);
        }
    }
}

