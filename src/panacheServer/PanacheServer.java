/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panacheServer;
import java.net.*;
import java.io.*;

/**
 *
 * @author Satvik
 */
public class PanacheServer {

    
    public static void main(String[] args) {
       
    ServerSocket ss = null;
    try{
        ss = new ServerSocket(25001);
    }catch(Exception e)
    {
        System.out.println("Can not initialize Server Socket");
    }
        
        while(true)
        {
            Socket s = null;
            try{
                System.out.println("waiting");
                s = ss.accept();
                System.out.println("connected");
                InputStream is = s.getInputStream();
                OutputStream os = s.getOutputStream();
                
                Thread t = new ClientHandeler(s,is,os);
                
                t.start();
                
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
      
    }
    
}
