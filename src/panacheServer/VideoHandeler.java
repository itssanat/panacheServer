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

public class VideoHandeler extends Thread {

    Socket s;
    DataInputStream dis;

    public VideoHandeler(Socket s, DataInputStream dis )
    {
        this.s = s;
        this.dis = dis;
    }
    
    @Override
    public void run()
    {
        try{
        
        String userName = dis.readUTF();
        String name = dis.readUTF();
        String tags = dis.readUTF();
        int length  = dis.readInt();
        
        byte [] b = new byte[length];
        
        if(length>0){
            dis.readFully(b,0,length); 
        }
        
        LogIn forCount = new LogIn(userName);
        forCount.connect();
        int count = forCount.getCount();
        
        String path = "C:\\Users\\Satvik\\Documents\\NetBeansProjects\\PanacheServer\\Video\\"+userName+"."+String.valueOf(count)+".mp4" ;
        
        try{
            FileOutputStream fos = new FileOutputStream(path);
            fos.write(b);
            fos.flush();
            fos.close();
        }
        catch(Exception e)
        {
            System.out.println("Path Invalid");
        }
        
        File f = new File(path);
        
        if(f.exists())
        {
            System.out.println("File saved!!");
        }
        
        LogIn toAdd = new LogIn();
        toAdd.connect();
        toAdd.insert(path, name, userName,userName+"."+String.valueOf(count), tags);
        
        }
        catch(Exception ex)
        {
            System.out.println("Exception somewhere.....");
        }
        
    }
}
