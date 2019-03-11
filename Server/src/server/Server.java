/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author antdr
 */
// Java implementation of  Server side 
// It contains two classes : Server and ClientHandler 
// Save file as Server.java 
import java.io.*;
import java.text.*;
import java.util.*;
import java.net.*;

// Server class 
public class Server {

    public static void main(String[] args) throws IOException {
        // server is listening on port 5056 
        ServerSocket ss = new ServerSocket(5056);
        // running infinite loop for getting 
        // client request 
        while (true) {
            Socket s = null;
            try {
                // socket object to receive incoming client requests 
                s = ss.accept();
                System.out.println("A new client is connected : " + s.getInetAddress());
                // obtaining input and out streams 
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                System.out.println("Assigning new thread for this client");

                // create a new thread object 
                Thread t = new ClientHandler(s, dis, dos);

                // Invoking the start() method 
                t.start();

            } catch (Exception e) {
                s.close();
                e.printStackTrace();
            }
        }
    }
}
