/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package YourProductModel;

import connection.DbConnectionManager;
import connection.UrlConnectionManager;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.sql.Date;
import java.text.SimpleDateFormat;
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
@WebService(serviceName = "YourProductWS")
public class YourProductWS {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "hasProduct")
    public boolean hasProduct(@WebParam(name = "username") String username) {
       boolean hasProduct =false;
       Connection dbConn = DbConnectionManager.getConnection();
        try {
            String query = "SELECT * FROM catalogue WHERE username='"+username+"'";
            Statement ps = dbConn.createStatement();
            ResultSet rs = ps.executeQuery(query);
            hasProduct = rs.next();
                
        } catch (SQLException ex) {
            System.out.println("Insert to database failed: An Exception has occurred! " + ex);
        }
       return hasProduct;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getProduct")
    public ArrayList<String> getProduct(@WebParam(name = "access_token") String access_token,@WebParam(name = "user_agent") String user_agent) throws IOException, ParseException {
        int status=0;
        ArrayList<String> answers = new ArrayList<String>();
        Connection dbConn = DbConnectionManager.getConnection();
        String targetIS="ValidateToken";
        String urlParameters="access_token="+access_token;
        HttpURLConnection urlConn = UrlConnectionManager.doReqPost(targetIS,urlParameters,user_agent);
        String resp = UrlConnectionManager.getResponse(urlConn);
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(resp);
        String statusResp = (String) obj.get("status");
        String username =(String) obj.get("username");
        String user_id =(String) obj.get("user_id");
        switch (statusResp) {
            case "valid":
                if (hasProduct(username)) {
                    try {
                        String query = "SELECT * FROM catalogue WHERE username='" + username + "' ORDER BY product_id DESC";

                        Statement ps = dbConn.createStatement();
                        ResultSet rs = ps.executeQuery(query);
                        while (rs.next()) {
                            String productname = rs.getString("productname");
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
                            int count = 0;
                            while (rsLike.next()) {
                                count++;
                            }
                            int likes = count;
                            int purchases = rs.getInt("purchases");
                            String imagepath = rs.getString("imagepath");

                            String answer = "<li>"
                                    + "<span id='date'>"
                                    + "<b>" + dateadded + "</b> "
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
                                    + "<span><a id='edit' href='edit_product.jsp?product_id=" + product_id + "'><b> EDIT  </b></a> </span>"
                                    + "<span style='margin-left:5px'><a  id='delete' href='del_db?product_id=" + product_id + "'><b>DELETE </b></a> </span>"
                                    + "</div>"
                                    + "</div>"
                                    + "</li>"
                                    + "<br/><br/>";
                            answers.add(answer);
                        }
                    } catch (SQLException ex) {
                        System.out.println("Inser to database failed: An Exception has occurred! " + ex);
                    }
                } else {
                    String answer = "<b>You do not have any product.<b>";
                    answers.add(answer);
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
        return answers;
    }

   

   
}
