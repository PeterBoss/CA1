/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EchoClient;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.rmi.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import shared.ProtocolStrings;

/**
 *
 * @author josephawwal
 */
public class Client extends Thread implements EchoListener {
    
    
    Socket sock;
    private InetAddress serverAddress;
    private int port;
    
    private Scanner input;
    private PrintWriter output;
    
    
    List<EchoListener> listeners;
    
    
    public void connect(String address, int port, String name) throws UnknownHostException, IOException {
        
        
        
        serverAddress = InetAddress.getByName(address);
                this.port = port;

        sock = new Socket(serverAddress, port);
        input = new Scanner(sock.getInputStream());
        output = new PrintWriter(sock.getOutputStream(), true);
        
        listeners = new ArrayList();
        
        send("Connecting" + name);
        
        
        
        
        
        
        
}
    
    public void registerEchoListener(EchoListener l){
        
        listeners.add(l);
        
    }
    
    public void unRegisterEchoListener(EchoListener l)
    {
        listeners.remove(l);
        
    }
    
    
    public void notifyListener(String message){
        
        for (EchoListener listener : listeners){
            
            listener.messageArrived(message);
        }
        
    }
        
        public void send(String message){
            
            output.println(message);
        }
        
        public void stopNewest() throws IOException {
            
            
            output.println(ProtocolStrings.STOP);
            
        }
        
        
        public void run(){
            
            String message = input.nextLine();
            while (!message.equals(ProtocolStrings.STOP)) {
                
                notifyListener(message);
                
                message = input.nextLine();
                
            }
            
            try {
                
                sock.close();
            }
            
            catch (IOException ex){
                
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                
            }
        }

    @Override
    public void messageArrived(String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
        
    }

