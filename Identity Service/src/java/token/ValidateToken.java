/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package token;

import db.ConnectionManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Asus X550ZE
 */
@WebServlet(name = "ValidateToken", urlPatterns = {"/ValidateToken"})
public class ValidateToken extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JSONObject arrayObj = new JSONObject();
        String str_token = request.getParameter("access_token");
        String user_agent = request.getHeader("user-agent");
        String ip_address = request.getRemoteAddr();
        String access_token =str_token+"#"+user_agent+"#"+ip_address;
        String query = "SELECT * FROM tokendata WHERE token='"+access_token+"'";
        System.out.println("Client USER_AGENT: "+user_agent);
        System.out.println("Client IP_ADDRESS: "+ip_address);
        try{
         Connection currentCon=ConnectionManager.getConnection();
         Statement stmt = currentCon.createStatement();
         ResultSet rs = stmt.executeQuery(query);
         boolean valid = rs.next();
         System.out.println("ADA TOKEN TAU GAK = "+valid);
         
         if(valid){
            
             Timestamp create_time = getTimeStamp(access_token);
             Calendar cal = Calendar.getInstance();
             cal.setTimeInMillis(create_time.getTime());
             cal.add( Calendar.MINUTE, 30);
             Timestamp expired_time = new Timestamp(cal.getTimeInMillis());
             Timestamp current_time = new Timestamp(System.currentTimeMillis());
             
             if(expired_time.before(current_time)){
                 arrayObj.put("status", "expired");
             }
             else{
                 arrayObj =getJsonObj(access_token);
                 setCreateTime(getUserID(access_token),access_token,current_time);
             }
         }
         else
         {
            arrayObj.put("status", "non-valid");
         }
        }catch(SQLException se){
            System.out.println(se);
        }
        response.setContentType("application/json:charset=UTF-8");
        response.getWriter().write(arrayObj.toString());   
    }
    private void setCreateTime(int user_id,String access_token,Timestamp create_time) throws SQLException{
        Connection dbConn = ConnectionManager.getConnection();
        String query = "UPDATE tokendata SET create_time='"+ create_time+"'"
                +"WHERE token='"+access_token+"'";
        PreparedStatement ps = dbConn.prepareStatement(query);
        int i=ps.executeUpdate();
    }
    
    private ArrayList<String> getUsersOnline(){
        Connection currentCon =null;
        String query = "SELECT * FROM tokendata";
        ArrayList<String> listUser = new ArrayList<String>();
        try{
            currentCon=ConnectionManager.getConnection();
            Statement stmt = currentCon.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                String username = getUserName(rs.getInt("user_id"));
                listUser.add(username);
            }
            
        }catch (Exception ex) {
            System.out.println("Failed " + ex);
        }
        return listUser;
    }
    
    private JSONObject getJsonObj(String access_token){
        Connection currentCon =null;
        int user_id = getUserID(access_token);
        String userId = ""+user_id;
        String username = getUserName(user_id);
        String query = "SELECT * FROM userdata WHERE user_id='"+userId+"'";
        String fullname = "";
        String fulladdress="";
        String postalcode="";
        String phonenumber="";
        ArrayList <String> listUserOnline = getUsersOnline();
        JSONObject ArrayObj = new JSONObject();
        
         try{
            currentCon=ConnectionManager.getConnection();
            Statement stmt = currentCon.createStatement();
            ResultSet rs = stmt.executeQuery(query);
               while(rs.next()){
                  fullname = rs.getString("fullname");
                  fulladdress= rs.getString("fulladdress");
                  postalcode= rs.getString("postalcode");
                  phonenumber= rs.getString("phonenumber");
               }
                 JSONArray arrJson = new JSONArray();
                 ArrayObj.put("status", "valid");
                 ArrayObj.put("user_online",listUserOnline);
                 ArrayObj.put("username", username);
                 ArrayObj.put("user_id", userId);
                 ArrayObj.put("fullname", fullname);
                 ArrayObj.put("fulladdress", fulladdress);
                 ArrayObj.put("postalcode", postalcode);
                 ArrayObj.put("phonenumber",phonenumber);
         } catch (Exception ex) {
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
         return ArrayObj;
    }
    
    private String getUserName(int user_id){
        Connection currentCon=null;
        String query = "SELECT username FROM userdata WHERE user_id='"+user_id+"'";
        String username ="";
        try {
            //connect to database
            currentCon = ConnectionManager.getConnection();
            Statement stmt= currentCon.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next()){
                username = rs.getString("username");
            } 
            System.out.println("USERNAME BERHASIL DITARIK" + username);
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
        
        return username;
    } 
    
    private int getUserID(String access_token){
        Connection currentCon=null;
        String query = "SELECT user_id FROM tokendata WHERE token='"+access_token+"'";
        int user_id=0;
        try {
            //connect to database
            currentCon = ConnectionManager.getConnection();
            Statement stmt= currentCon.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                user_id = rs.getInt("user_id");
            }  
            System.out.println("USERID BERHASIL DITARIK" + user_id);
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
        
        return user_id;
    }
    
    private Timestamp getTimeStamp(String access_token){
         Connection currentCon=null;
        String query = "SELECT create_time FROM tokendata WHERE token='"+access_token+"'";
        Timestamp create_time=null;
        try {
            //connect to database
            currentCon = ConnectionManager.getConnection();
            Statement stmt= currentCon.prepareStatement(query);
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()){
                create_time= rs.getTimestamp("create_time");
            }  
            System.out.println("CREATETIME BERHASIL DITARIK" + create_time);
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
        
        return create_time;

    }
}
