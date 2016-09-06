package cagrp.ca1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatServer {

    private static ServerSocket serverSocket;
    private static String ip;
    private static int port;
    private static boolean keepRunning = true;

    public static void stopServer() {
        keepRunning = false;
    }

    private void runServer(String ip, int port) {
        this.port = port;
        this.ip = ip;

        System.out.println("Server started. Listening on: " + port + ", bound to: " + ip);

        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(ip, port));
            do {
                Socket socket = serverSocket.accept();
                System.out.println("Connected to a client");
                // do stuff
            } while (keepRunning);
        } catch (IOException ex) {
            //autogenerated
            Logger.getLogger(ChatServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {

        //has to be run with arguments
        new ChatServer().runServer(args[0], Integer.valueOf(args[1]));

    }

}
