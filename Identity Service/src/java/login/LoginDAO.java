package login;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import db.ConnectionManager;
import java.text.*;
import java.util.*;
import java.sql.*;
/**
 *
 * @author Scarletta's
 */
public class LoginDAO {
    static Connection currentCon = null;
    static ResultSet rs = null;
    
    public static LoginBean login(LoginBean bean) {
        //preparing some objects for connection
        Statement stmt = null;
        
        String username = bean.getUsername();
        String password = bean.getPassword();
        
        String searchQuery = "SELECT * FROM userdata WHERE (username='"+ username + "' OR email ='"+ username +"') AND password = '" + password +"'";
        
        //to trace the process in console
        System.out.println("Your user name is " + username);
        System.out.println("Your password is " + password);
        System.out.println("Query: "+searchQuery);
        
        try {
            //connect to database
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(searchQuery);
            boolean more = rs.next();
            System.out.println("BERHASIL CEK DATABASE");
            
            //if user does not exist set isValid to false
            if (!more) {
                System.out.println("Username doesn't exist");
                bean.setValid(false);    
            }
            
            //if user exists set isValid to true
            else if (more) {
                String usernameDB = rs.getString("username");
                String passwordDB = rs.getString("password");
                
                System.out.println("Welcome " + usernameDB);
                bean.setUsername(usernameDB);
                bean.setPassword(passwordDB);
                bean.setValid(true);
            }
            
        }
        
        catch (Exception ex) {
            System.out.println("Log In failed: An Exception has occurred! " + ex);
        }
        
        //exception handling
        finally {
            if(rs!=null) {
                try {
                    rs.close();
                }
                catch (Exception e) {}
                rs = null;
            }
            
            if (stmt!=null) {
                try {
                    stmt.close();
                }
                catch (Exception e) {}
                stmt = null;
            }
            
            if (currentCon!=null) {
               try {
                   currentCon.close();
               }
               catch (Exception e) {}
               currentCon = null;
            }
        }
        return bean;
    }
    
      public static int getUserID (LoginBean bean) throws SQLException {
        String username = bean.getUsername();
        String query = "SELECT user_id FROM userdata WHERE username='"+ username + "' OR email='" + username + "'";
        Statement stmt = null;
        ResultSet rs = null;
        int userID=0;
        try {
            //connect to database
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(query);
            
            while(rs.next()){
                userID = rs.getInt("user_id");
            }
            
        }
        
        catch (Exception ex) {
            System.out.println("Failed " + ex);
        }
        
        return userID;
        
    }
}
