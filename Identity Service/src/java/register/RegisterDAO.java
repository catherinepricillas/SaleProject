package register;

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
public class RegisterDAO {
    static Connection currentCon = null;
    
    public static RegisterBean register(RegisterBean bean) {
        
        String fullname = bean.getFullname();
        String username = bean.getUsername();
        String email = bean.getEmail();
        String password = bean.getPassword();
        String address = bean.getAddress();
        String postal = bean.getPostal();
        String phone = bean.getPhone();
        
        if (checkEmailAndUsernameExist(bean)) {
        String query = "INSERT INTO userdata(fullname, username, email, password, fulladdress, postalcode, phonenumber) VALUES ('"+ fullname +"','"+ username +"','"+ email +"','"+ password +"','"+ address +"','"+ postal +"','"+ phone +"')";
        bean.setValid(true);
        //to trace the process in console
        System.out.println("Your user name is " + username);
        System.out.println("Your password is " + password);
        System.out.println("Query: "+query);
        
        try {
            //connect to database
            currentCon = ConnectionManager.getConnection();
            PreparedStatement ps = currentCon.prepareStatement(query);
            int i=ps.executeUpdate();
            System.out.println(" MASUK DATABASE");
          
        }
        catch (Exception ex) {
            System.out.println("Register failed: An Exception has occurred! " + ex);
        }
        finally {
            if (currentCon!=null) {
               try {
                   currentCon.close();
               }
               catch (Exception e) {}
               currentCon = null;
            }
        }
        }else{
            System.out.println("GK MASUK DATABASE");
            bean.setValid(false);
        }
        return bean;
    }
    
    public static int getUserID (RegisterBean bean) throws SQLException {
        String username = bean.getUsername();
        String query = "SELECT user_id FROM userdata WHERE username='"+username+"'";
        int userID=0;
        try {
            //connect to database
            currentCon = ConnectionManager.getConnection();
            Statement stmt= currentCon.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
            userID = rs.getInt("user_id");
            }  
        }
        catch (Exception ex) {
            System.out.println("Failed " + ex);
        }
         finally {
            if (currentCon!=null) {
               try {
                   currentCon.close();
               }
               catch (Exception e) {}
               currentCon = null;
            }
        }
        
        return userID;
    }
    
    public static boolean checkEmailAndUsernameExist(RegisterBean bean){
        String username = bean.getUsername();
        String email = bean.getEmail();
        boolean result = true;
        String query = "SELECT * FROM userdata WHERE username='" + username + "' OR email= '" + email + "'";
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //connect to database
            currentCon = ConnectionManager.getConnection();
            stmt = currentCon.createStatement();
            rs = stmt.executeQuery(query);
            boolean more = rs.next();
            if(more){
                result = false;
            }
            else {
                result = true;
            }

        }

        catch (Exception ex) {
            System.out.println("Failed " + ex);
        }
        System.out.println("RESULT ADA ATAU ENGGAK = "+result);
        return result;
        
    }
    
}
