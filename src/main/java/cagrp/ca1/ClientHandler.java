package cagrp.ca1;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientHandler extends Thread implements Observer {

    private final Socket socket;
    private final PrintWriter writer;
    private final ChatServer server;
    private final Scanner input;

    public ClientHandler(Socket socket, ChatServer server) throws IOException {
        this.socket = socket;
        this.writer = new PrintWriter(socket.getOutputStream(), true);
        this.server = server;
        this.input = new Scanner(socket.getInputStream());
    }

    @Override
    public void run() {
        try {
            while (true) {
                String message = input.nextLine();
                System.out.println("Received: " + message);
                if (message == null) {
                    continue;
                    // temp (see echo example)
                } else if (message.equals("LOGOUT:")) {
                    break;
                }
                server.sendMessage(message);
            }
        } finally {
            try {
                writer.println("LOGGING OUT");//Echo the stop message back to the client for a nice closedown
                socket.close();
                server.deleteObserver(this);
                System.out.println("Closed a Connection");
            } catch (IOException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } 

    @Override
    public void update(Observable o, Object arg) {
        writer.println(arg);
    }
}
