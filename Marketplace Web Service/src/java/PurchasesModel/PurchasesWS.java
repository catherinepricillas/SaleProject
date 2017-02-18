/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PurchasesModel;

import connection.DbConnectionManager;
import connection.UrlConnectionManager;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
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
@WebService(serviceName = "PurchasesWS")
public class PurchasesWS {

   /**
     * Web service operation
     * @param user_id
     * @return hasPurchases
     */
    @WebMethod(operationName = "hasPurchases")
    public boolean hasPurchases (@WebParam(name = "username") String username) {
        boolean hasPurchases =false;
        Connection conn = DbConnectionManager.getConnection();
        try {
            String query = "SELECT * FROM purchase WHERE purchase.buyer = '"+username+"'";
            Statement stmt = conn.createStatement();
            ResultSet rslt = stmt.executeQuery(query);
            hasPurchases = rslt.next();
                
        } catch (SQLException ex) {
            System.out.println("Insert to database failed: An Exception has occurred! " + ex);
        }
       return hasPurchases;
    }

    /**
     * Web service operation
     * @param access_token
     * @param user_id
     * @return 
     */
    @WebMethod(operationName = "getPurchases")
    public ArrayList<String> getPurchases(@WebParam(name = "access_token") String access_token,@WebParam(name = "user_agent") String user_agent) throws IOException, ParseException {
        ArrayList<String> answers = new ArrayList<String>();
        Connection conn = DbConnectionManager.getConnection();
        String targetIS = "ValidateToken";
        String urlParameters="access_token="+access_token;
         HttpURLConnection urlConn = UrlConnectionManager.doReqPost(targetIS,urlParameters,user_agent);
        String resp = UrlConnectionManager.getResponse(urlConn);
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(resp);
        String statusResp = (String) obj.get("status");
        String username = (String) obj.get("username");
        System.out.println(statusResp);
        switch (statusResp) {
            case "valid":
                System.out.println(hasPurchases(username));
                if (hasPurchases(username)) {
                    try {
                        String query = "SELECT * FROM purchase WHERE purchase.buyer = '"+username+"' ORDER BY purchase.purchase_id DESC";
                        Statement stmt = conn.createStatement();
                        ResultSet rslt = stmt.executeQuery(query);
                        while (rslt.next()) {
                            int product_id = rslt.getInt("purchase_id");
                            String product_name = rslt.getString("product_name");
                            int product_price = rslt.getInt("product_price");
                            String seller = rslt.getString("seller");
                            String buyer = rslt.getString("buyer");
                            String image = rslt.getString("image");
                            int quantity = rslt.getInt("quantity");
                            String consignee = rslt.getString("consignee");
                            String fulladdress = rslt.getString("fulladdressbuyer");
                            int postalcode = rslt.getInt("postalcode");
                            String phonenumber = rslt.getString("newphonenumber");
                            String creditcard = rslt.getString("creditcard");
                            String verification = rslt.getString("verification");
                            Date  dateBoughtF = rslt.getDate("datebought");
                            SimpleDateFormat simpledatefo = new SimpleDateFormat("dd/MM/yyyy");
                            Time  timeBoughtF = rslt.getTime("timebought");
                            SimpleDateFormat simpletimefo = new SimpleDateFormat("dd/MM/yyyy");
                            String datebought = rslt.getString("datebought");
                            String timebought = rslt.getString("timebought");
                            int totalprice = product_price * quantity;

                            String answer = "<li>" 
                                    +"<span id='date'>" 
                                    +"<b>" + datebought + "</b>"
                                    +"<br/>"
                                    +"at " + timebought
                                    +"<hr></hr>"
                                    +"</span>"
                                    +"<div class='item-list-product'>"
                                    +"<div style='position:absolute'>"
                                    +"<a href='" + image + "'><img class='img-item' src='" + image + "'></img></a>"
                                    +"</div>"
                                    +"<div id='product-info' style='width:250px'>"
                                    +"<span><b>" + product_name + "</b></span><br/>"
                                    +"<span>IDR " + totalprice + "</span> <br/>"
                                    +"<span>" + quantity + "pcs</span><br/>"
                                    +"<span>@IDR " + product_price +"</span>"
                                    +"</div>"
                                    +"<div id='eddel' style='font-size:12px;left:380px;width:220px'>"
                                    +"<span>Delivery to <b>" + consignee + "</b> </span> <br/>"
                                    +"<span>" + fulladdress + "</span> <br/>"
                                    +"<span>" + postalcode + "</span><br/>"
                                    +"<span>" + phonenumber + "</span>"
                                    +"</div>"
                                    +"</div>"
                                    +"<span style=\"margin-left:120px;font-size:12px\">bought from <b>" + seller + "</b></span>"
                                    +"</li>"
                                    +"<br>";
                            answers.add(answer);
                        }
                    } catch (SQLException ex) {
                        System.out.println("Insert to database failed: An Exception has occurred! " + ex);
                    }
                } else {
                    String answer = "<b>You have not purchased any product.<b>";
                    System.out.println(answer);
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
