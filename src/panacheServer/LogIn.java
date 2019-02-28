package panacheServer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Satvik
 */


import java.sql.*;

public class LogIn {
    Connection con;
    
    String name;
    String userName;
    String password;
    
    LogIn(String userName,String password)
   {
       this.password = password;
       this.userName = userName;
   }
   
   LogIn( String userName,String password, String name)
   {
       this.name = name;
       this.password = password;
       this.userName = userName;
   }
   LogIn(String userName)
   {
       this.userName = userName;
   }
   
   LogIn()
   {
       
   }
    public void connect()
    {
        try{
            //Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/server","root","root");
        }
        catch(Exception e)
        {
            System.out.println(" Unable to connect the databese ");
        }
    }
    
    public int getCount()
    {
        int temp=0;
        String sql = "select *from users where userID = ? ; ";
      
        try{
            PreparedStatement ptm = con.prepareStatement(sql);
            
            ptm.setString(1,userName);
            
            ResultSet rs = ptm.executeQuery();
             
            int count = 13;
            rs.next();
            count = rs.getInt(4);
           
             temp = count;
            count++;
            
            System.out.println(count);
            String update = "update users set count = ? where userID = ? ;";
            
            PreparedStatement pt = con.prepareStatement(update);
            
            pt.setInt(1, count);
            pt.setString(2, userName);
            pt.executeUpdate();
            
        }catch(Exception ex)
        {
            System.out.println("Error in getting and updating count ");
            System.out.println(ex.getMessage());
        }
        return temp ;
        
    }
    
    public boolean login()
    {
        String check = "select * from users where userID = ? and password = ? ;";
        
        
        try{
            PreparedStatement ptm = con.prepareStatement(check);
            ptm.setString(1, userName);
            ptm.setString(2,password);
            System.out.println("Request to Login.......");
            
            //Statement stm = con.createStatement();
            ResultSet rs = ptm.executeQuery();
            if(!rs.isBeforeFirst())
                return false;
            else 
                return true;
        }
        catch(Exception e)
        {
            System.out.println(" Error in Login");
        }
        return false;
    }
    
    public boolean register()
    {
        String check = "select *from users where userID = ?;";
        
        try{
            PreparedStatement ptm = con.prepareStatement(check);
            ptm.setString(1,userName);
            
            ResultSet rs = ptm.getResultSet();
            if(rs.isBeforeFirst())
                return false;
     
        }
        catch(Exception e)
        {
            System.out.println("Checking Duplicate enteries");
        }
        
        
        String query = "insert into users values (?,?,?,?);";
        
         try{
            PreparedStatement ptm = con.prepareStatement(query);
            ptm.setString(1,userName);
            ptm.setString(2,name);
            ptm.setString(3,password);
            ptm.setInt(4,0);
     
            ptm.executeUpdate();
            return true;
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        
        return false;
    }
    
    public void insert(String path, String name, String uploader, String videoID, String tags)
    {
        String sql = "insert into video values(?,?,?,?,?); ";
        
        try{
            PreparedStatement ptm = con.prepareStatement(sql);
            ptm.setString(1, path);
            ptm.setString(2, name);
            ptm.setString(3,uploader);
            ptm.setString(4,videoID);
            ptm.setString(5,tags);
            ptm.executeUpdate();
            
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
}
