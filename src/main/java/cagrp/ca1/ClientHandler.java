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
    private String username;

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
                if (message.equals("LOGOUT:")) {
                    break;
                }
                String[] bits = message.split(":");
                switch (bits[0]) {
                    case "LOGIN": {
                        if (bits.length == 2) {
                            server.addObserver(this);
                            username = bits[1];
                            server.addClientToMap(username, this);
                            server.addClient(username);
                            server.broadcastClients();
                            continue;
                        }
                    }
                    case "MSG":
                        if (bits[1].equals("")) {
                            server.sendMessageToAll(bits, username);
                        }
                        else {
                            server.sendMessage(bits, username);
                        }
                }


            }
        } finally {
            try {
                writer.println("LOGGING OUT");
                socket.close();
                server.deleteObserver(this);
                server.removeClientFromMap(username);
                server.removeClient(username);
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
