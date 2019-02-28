/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panacheServer;
import java.io.*;
import java.net.*;
import java.sql.*;
/**
 *
 * @author Satvik
 */
public class Video {
    
    public static void main(String [] args)
    {
        ServerSocket ss=null;
        try{
             ss = new ServerSocket(25002);
        }
        catch(Exception ex)
        {
            System.out.println("Server not started");
        }
        
        while(true )
        {
            try{
                Socket s = ss.accept();
                System.out.println("Connected");
                DataInputStream dis = new DataInputStream(s.getInputStream());
                
                Thread t = new VideoHandeler(s,dis);
                t.start();
                System.out.println("Thread started");
                
            }
            catch(Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }
        
    }
    
}
