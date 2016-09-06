/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import cagrp.ca1.ChatServer;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import org.junit.Test;

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
         CS.runServer("localhost", 9000);
         
     }
     
     public void testServerStop() throws IOException, InterruptedException {
         
         ChatServer CS = new ChatServer();
         CS.stopServer();
         
     }
}
