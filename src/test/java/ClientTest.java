/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import client.Client;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import org.junit.Test;
import server.ChatServer;

/**
 *
 * @author josephawwal
 */
public class ClientTest {
    
    public ClientTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws IOException, InterruptedException {
    
        new Thread(new Runnable(){
            
            @Override
            
            public void run(){
                
                ChatServer.main(null);
                
            }
        }).start();
        
    }
    
    @AfterClass
    public static void tearDownClass() throws IOException {
        
        ChatServer.stopServer();
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
   

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
  @Test
     
  
     public void testServerStart() throws IOException, InterruptedException 
     {
     
         ChatServer CS = new ChatServer();
       //  CS.runServer("localhost", 9000);
         
     }
     
     public void testServerStop() throws IOException, InterruptedException {
         
         ChatServer CS = new ChatServer();
         CS.stopServer();
         
     }
     
     public void testConnection() throws IOException{
         
         Client client = new Client();
       
            client.connect("localhost", 9000, "joseph");
      
     }
     
     public void testMessageArrived(){
         
         Client client = new Client();
         client.messageArrived("test");
     }
}
