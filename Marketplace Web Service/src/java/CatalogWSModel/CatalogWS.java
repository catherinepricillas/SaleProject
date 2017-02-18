/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CatalogWSModel;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import connection.DbConnectionManager;
import connection.UrlConnectionManager;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.*;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Asus X550ZE
 */


    /**
     * This is a sample web service operation
     */
   @WebService(serviceName = "CatalogWS")
public class CatalogWS {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "viewCatalog")
    public ArrayList<String> viewCatalog(@WebParam(name = "access_token") String access_token,@WebParam(name = "user_agent") String user_agent) throws IOException, ParseException {
        int status=0;
        ArrayList<String> answers = new ArrayList<String>();
        Connection dbConn = DbConnectionManager.getConnection();
        String targetIS="ValidateToken";
        String urlParameters="access_token="+access_token;
        HttpURLConnection urlConn = UrlConnectionManager.doReqPost(targetIS,urlParameters,user_agent);
        System.out.println(user_agent);
       
        String resp = UrlConnectionManager.getResponse(urlConn);
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(resp);
        String statusResp = (String) obj.get("status");
         System.out.println("status= "+obj.get("status"));
        String username =(String) obj.get("username");
        System.out.println("obj= "+obj.get("user_id"));
        String user_id = (String)obj.get("user_id");
        System.out.println("String id = "+user_id);
        ArrayList<String> listUserOnline = new ArrayList<String>();
        listUserOnline= (ArrayList<String>) obj.get("user_online");
        System.out.println("List: "+  obj.get("user_online"));
        switch (statusResp) {
            case "valid":
                    try {
                        String query = "SELECT * FROM catalogue Order by product_id DESC";

                        Statement ps = dbConn.createStatement();
                        ResultSet rs = ps.executeQuery(query);
                        while (rs.next()) {
                            
                            String productname = rs.getString("productname");
                            String seller = rs.getString("username");
                            int product_id = rs.getInt("product_id");
                            int price = rs.getInt("price");
                            String productdesc = rs.getString("productdesc");
                            Date  dateAddedF = rs.getDate("dateadded");
                            SimpleDateFormat simpledatafo = new SimpleDateFormat("dd/MM/yyyy");
                            Time  timeAddedF = rs.getTime("timeadded");
                            SimpleDateFormat simpletimefo = new SimpleDateFormat("dd/MM/yyyy");
                            String dateadded = rs.getString("dateadded");
                            String timeadded = rs.getString("timeadded");
                            String qLike = "SELECT * from likes WHERE product_id='" + product_id + "'";
                            Statement psLike = dbConn.createStatement();
                            ResultSet rsLike = psLike.executeQuery(qLike);
                            boolean online = listUserOnline.indexOf(seller)!= -1;
                            int count = 0;
                            while (rsLike.next()) {
                                count++;
                            }
                            int likes = count;
                            int purchases = rs.getInt("purchases");
                            String imagepath = rs.getString("imagepath");
                         
                            String statuslike = "<span><a id=\"like\" href=\"like?product_id="+product_id+"\"><b>ASD</b></a></span>";
                            String checkLike = "SELECT * from likes WHERE product_id='" + product_id + "' AND user_id='" + user_id + "'";
                            boolean hasLiked;
                            Statement psCheckLike = dbConn.createStatement();
                            ResultSet rsCheckLike = psLike.executeQuery(checkLike);
                            hasLiked=rsCheckLike.next();
                            
                            if (!hasLiked) {
                                statuslike = "<span><a id=\"like\" href=\"like?product_id="+product_id+"\"><b>LIKE</b></a></span>";
                            }
                            else {
                                statuslike = "<span><a id=\"dislike\" href=\"dislike?product_id="+product_id+"\"><b>LIKED</b></a></span>";
                            }
                            String statusOnline = "<img src='img/offline-circle.png' height='10px'>";
                            if(online){
                                statusOnline = "<a style='text-decoration:none; color:#000000' ng-click='openChatBox(\""+seller+"\")'><img src='img/online-circle.png'height='10px'>";
                            }else{
                                statusOnline = "<img src='img/offline-circle.png' height='10px'>";
                            }
                            String answer = "<li>"
                                    + "<span id='date'>"
                                    + statusOnline+"<b> " + seller + "</b></span></a>"
                                    + "</span>"
                                    + "<br/>"
                                    + "<span id='date'>"
                                    + dateadded
                                    + "<br/>"
                                    + "at "+timeadded 
                                    + "<hr></hr>"
                                    + "</span>"
                                    + "<div class='item-list-product'>"
                                    + "<div style='position:absolute'>"
                                    + "<a href='" + imagepath + "'><img class='img-item' src='" + imagepath + "'></img></a>"
                                    + "</div>"
                                    + "<div id='product-info'>"
                                    + "<span><b>" + productname + "</b></span> <br/>"
                                    + "<span>IDR " + price + "</span> <br/>"
                                    + "<span style='font-size:12px;position:relative'>" + productdesc + "</span>"
                                    + "</div>"
                                    + "<div id='eddel'>"
                                    + "<br/>"
                                    + "<span style='font-size:14px'>" + likes + " likes</span> <br/>"
                                    + "<span style='font-size:14px'>" + purchases + " purchase</span> <br/>"
                                    + "<br/>"
                                    + statuslike
                                    + "<span style='margin-left:10px'><a id='buy' href='confirm.jsp?product_id="+product_id+"'><b>    BUY </b></a> </span>"
                                    + "</div>"
                                    + "</div>"
                                    + "</li>"
                                    + "<br/><br/>";
                            answers.add(answer);
                        }
                    } catch (SQLException ex) {
                        System.out.println("Insert to database failed: An Exception has occurred! " + ex);
                    }
                
                break;
            case "non-valid":
                String answer = "2";
                answers.add(answer);
                break;
            default:
                answer = "3";
                answers.add(answer);
                break;
        }
        System.out.println("Answer = "+answers.get(0));
        return answers;
    }
    
     @WebMethod(operationName = "searchProduct")
    public ArrayList searchProduct(@WebParam(name = "keyword") String keyword, @WebParam(name = "filter") String filter, @WebParam(name = "user_id") String user_id) {
        ArrayList<String> answers = new ArrayList<String>();
        Connection dbConn = DbConnectionManager.getConnection();
        try {
            String query;
            if (filter.equals("product"))  {
                query = "SELECT * FROM catalogue WHERE productname like '%" + keyword + "%'";
            }
            else {
                query = "SELECT * FROM catalogue WHERE username like '%" + keyword + "%'";
            }

            Statement ps = dbConn.createStatement();
            ResultSet rs = ps.executeQuery(query);
            while (rs.next()) {
                String productname = rs.getString("productname");
                int product_id = rs.getInt("product_id");
                int price = rs.getInt("price");
                String seller = rs.getString("username");
                String productdesc = rs.getString("productdesc");
                Date  dateAddedF = rs.getDate("dateadded");
                SimpleDateFormat simpledatafo = new SimpleDateFormat("dd/MM/yyyy");
                Time  timeAddedF = rs.getTime("timeadded");
                SimpleDateFormat simpletimefo = new SimpleDateFormat("dd/MM/yyyy");
                String dateadded = rs.getString("dateadded");
                String timeadded = rs.getString("timeadded");
                String qLike = "SELECT * from likes WHERE product_id='" + product_id + "'";
                Statement psLike = dbConn.createStatement();
                ResultSet rsLike = psLike.executeQuery(qLike);
                int count = 0;
                while (rsLike.next()) {
                    count++;
                }
                int likes = count;
                int purchases = rs.getInt("purchases");
                String imagepath = rs.getString("imagepath");
                
                String statuslike = "<span><a id=\"like\" href=\"like?product_id="+product_id+"\"><b>LIKE</b></a></span>";
                String checkLike = "SELECT * from likes WHERE product_id='" + product_id + "' AND user_id='" + user_id + "'";
                Statement psCheckLike = dbConn.createStatement();
                ResultSet rsCheckLike = psLike.executeQuery(checkLike);
                count = 0;
               boolean hasLiked = rsCheckLike.next();
                if (!hasLiked) {
                    statuslike = "<span><a id=\"like\" href=\"like?product_id="+product_id+"\"><b>LIKE</b></a></span>";
                }
                else {
                    statuslike = "<span><a id=\"dislike\" href=\"dislike?product_id="+product_id+"\"><b>LIKED</b></a></span>";
                }
                
                String answer = "<li>"
                        + "<span id='date'>"
                        +"<b>" + seller + "</b>"
                        + "</span><br/>"	
                        + "<span id='date'>"
                        + dateadded
                        + "<br/>"
                        + "at "+timeadded 
                        + "<hr></hr>"
                        + "</span>"
                        + "<div class='item-list-product'>"
                        + "<div style='position:absolute'>"
                        + "<a href='" + imagepath + "'><img class='img-item' src='" + imagepath + "'></img></a>"
                        + "</div>"
                        + "<div id='product-info'>"
                        + "<span><b>" + productname + "</b></span> <br/>"
                        + "<span>IDR " + price + "</span> <br/>"
                        + "<span style='font-size:12px;position:relative'>" + productdesc + "</span>"
                        + "</div>"
                        + "<div id='eddel'>"
                        + "<br/>"
                        + "<span style='font-size:14px'>" + likes + " likes</span> <br/>"
                        + "<span style='font-size:14px'>" + purchases + " purchase</span> <br/>"
                        + "<br/>"
                        + statuslike
                        + "<span style='margin-left:10px'><a id='buy' href='confirm.jsp?product_id="+product_id+"'><b>    BUY </b></a> </span>"
                        + "</div>"
                        + "</div>"
                        + "</li>"
                        + "<br/><br/>";
                answers.add(answer);
                
            }
        } catch (SQLException ex) {
            System.out.println("Insert to database failed: An Exception has occurred! " + ex);
        }
       
        return answers;
   }
    
    @WebMethod(operationName = "likeProduct")
    public int likeProduct(@WebParam(name = "access_token") String access_token, @WebParam(name = "product_id") String product_id,@WebParam(name = "user_agent") String user_agent) throws IOException, ParseException {
        //TODO write your implementation code here:
        int status=0;
        Connection dbConn = DbConnectionManager.getConnection();
        String targetIS="ValidateToken";
        String urlParameters="access_token="+access_token;
        HttpURLConnection urlConn = UrlConnectionManager.doReqPost(targetIS,urlParameters,user_agent);
        String resp = UrlConnectionManager.getResponse(urlConn);
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(resp);
        String statusResp = (String) obj.get("status");
        String username =(String) obj.get("username");
        String user_id = (String) obj.get("user_id");
        System.out.println("PRODUCT ID FOR LIKE="+product_id+"USER ID FOR LIKE="+user_id);
        System.out.println(statusResp);
        switch (statusResp) {
            case "valid":
                    
                        String query = "INSERT INTO likes (product_id, user_id) VALUES ('" + product_id + "','" + user_id + "')";
                        
                        try {
                            //connect to database
                            dbConn = DbConnectionManager.getConnection();
                            PreparedStatement ps = dbConn.prepareStatement(query);
                            int i=ps.executeUpdate();
                            System.out.println("INSERT DATABASE LIKE SUKSES");

                        }

                        catch (Exception ex) {
                            System.out.println("INSERT DATABASE GAK SUKSES");
                            System.out.println("Register failed: An Exception has occurred! " + ex);
                        }

                        finally {
                            if (dbConn!=null) {
                               try {
                                   dbConn.close();
                               }
                               catch (Exception e) {}
                               dbConn = null;
                            }
                        }
                    
                status=1;
                break;
            case "non-valid":
                status=2;
                break;
            default:
                status = 3;
                break;
        }
        
        
        return status;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "dislikeProduct")
    public int dislikeProduct(@WebParam(name = "access_token") String access_token, @WebParam(name = "product_id") String product_id,@WebParam(name = "user_agent") String user_agent) throws IOException, ParseException {
        //TODO write your implementation code here:
        int status=0;
        Connection dbConn = DbConnectionManager.getConnection();
        String targetIS="ValidateToken";
        String urlParameters="access_token="+access_token;
         HttpURLConnection urlConn = UrlConnectionManager.doReqPost(targetIS,urlParameters,user_agent);
        String resp = UrlConnectionManager.getResponse(urlConn);
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(resp);
        String statusResp = (String) obj.get("status");
        String username =(String) obj.get("username");
        String user_id = (String) obj.get("user_id");
        
        switch (statusResp) {
            case "valid":
                    
                        String query = "DELETE FROM likes WHERE product_id='" + product_id + "' AND user_id='" + user_id + "'";
                        try {
                            //connect to database
                            dbConn = DbConnectionManager.getConnection();
                            PreparedStatement ps = dbConn.prepareStatement(query);
                            int i=ps.executeUpdate();

                        }

                        catch (Exception ex) {
                            System.out.println("Register failed: An Exception has occurred! " + ex);
                        }

                        finally {
                            if (dbConn!=null) {
                               try {
                                   dbConn.close();
                               }
                               catch (Exception e) {}
                               dbConn = null;
                            }
                        }
                    
                status=1;
                break;
            case "non-valid":
                status=2;
                break;
            default:
                status = 3;
                break;
        }
        
        
        return status;
    }
}
