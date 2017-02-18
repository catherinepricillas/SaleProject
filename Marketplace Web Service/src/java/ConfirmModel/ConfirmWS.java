/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConfirmModel;

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
@WebService(serviceName = "ConfirmWS")
public class ConfirmWS {

   @WebMethod(operationName = "getProductData")
    public String getProductData(@WebParam(name = "access_token") String access_token, @WebParam(name = "productid") String productid,@WebParam(name = "user_agent") String user_agent) throws IOException, ParseException {
        Connection conn = DbConnectionManager.getConnection();
        String targetIS = "ValidateToken";
        String urlParameters="access_token="+access_token;
        HttpURLConnection urlConn = UrlConnectionManager.doReqPost(targetIS,urlParameters,user_agent);
        String resp = UrlConnectionManager.getResponse(urlConn);
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(resp);
        String statusResp = (String) obj.get("status");
        String answer ="";
        switch (statusResp) {
            case "valid":{
                try {
                 
                    String fullname = (String) obj.get("fullname");
                    String fulladdress = (String) obj.get("fulladdress");
                    String postalcode = (String) obj.get("postalcode");
                    String phonenumber = (String) obj.get("phonenumber");
                    String query2 = "SELECT * FROM catalogue WHERE product_id = '"+productid+"'";
                    Statement stmt = conn.createStatement();
                    ResultSet rslt2 = stmt.executeQuery(query2);
                    rslt2.next();
                    String productname = rslt2.getString("productname");
                    int price = rslt2.getInt("price");
                    int total = price;
                    answer = "<h1><span id='asking'>Please confirm your purchase </span></h1>"
                            +"<hr></hr>"
                            +"<br>"
                            +"<form name='confirmform' id='confirmform' onsubmit='return validateConfirm()' action='confirm_db?product_id=" + productid +"' method='post'>"
                            +"<label for='product'>Product<span style='margin-left: 30px;'>:</label>" + productname + "<br>"
                            +"<label for='price'>Price<span style='margin-left: 46px;'>:</label>"
                            +"IDR " + price + "<br>"
                            +"<label for='quantity'>Quantity<span style='margin-left:26px;'>:</label>"
                            +"<input id='quantity' onblur='return validateQuantity()' onkeydown='return countTotal("+ price +")' onkeyup='return countTotal(" + price + ")' style='width:25px;' type='text' name='quantity' value='1' onkeypress='return validateNumber(event)'> pcs"
                            +"<br>"
                            +"<div id='cekquantity' style='margin-left:84px;'></div>"
                            +"<label for='totalprice'>Total Price<span style='margin-left: 13px;'>:</label>"
                            +"<label id='totalprice'>IDR "+ total +"</label><br>"
                            +"<label >Delivery to<span style='margin-left: 12.5px;'>:</label><br><br>"
                            +"<label class='smallerlabel' for='consignee' >Consignee</label><br>"
                            +"<input id='consignee' type='text' name='consignee' value='" + fullname +"' onblur='return validateConsignee()'><br>"
                            +"<div id='cekconsignee'></div>"
                            +"<label class='smallerlabel' for='address'>Full Address</label><br>"
                            +"<textarea id='fulladdress' type='text' name='fulladdress' onblur='return validateAddress()'>"+ fulladdress +"</textarea><br>"
                            +"<div id='cekaddress'></div>"
                            +"<label class='smallerlabel' for='postalcode'>Postal Code</label><br>"
                            +"<input id='postalcode' type='text' name='postalcode' value='"+ postalcode +"' onblur='return validatePostal()'><br>"
                            +"<div id='cekpostal'></div>"
                            +"<label class='smallerlabel' for='phonenumber'>Phone Number</label><br>"
                            +"<input id='phonenumber' type='text' name='phonenumber' value='"+ phonenumber +"' onblur='return validatePhone()'><br>"
                            +"<div id='cekphone'></div>"
                            +"<label class='smallerlabel' for='ccnumber'>12 Digits Credit Card Number</label><br>"
                            +"<input id='ccnumber' type='text' name='creditcard' onkeypress='return validateCardNumber(event)' onblur='return validateCC()'><br>"
                            +"<div id='cekcc'></div>"
                            +"<label class='smallerlabel' for='verification'>3 Digits Card Verification Value</label><br>"
                            +"<input id='ver' type='text' name='verification' onkeypress='return validateVerification(event)' onblur='return validateVer()'><br>"
                            +"<div id='cekver'></div>"
                            +"<input type='submit' onclick='return validateForm()' value='CONFIRM' style=' margin-left:376px;' href=''>"
                            +"<input type='reset' value='CANCEL' style='margin-left:20px;'>"
                            +"</form>";
                }catch (SQLException ex) {
                    answer = "<b>Insert to database failed: An Exception has occurred!<b> " + ex;
                }
                break;}
            case "non-valid":{
                answer = "2";
                break;}
            default:{
                answer = "3";
                break;}
        }
        return answer;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "confirmPurchase")
    public int confirmPurchase(@WebParam(name = "access_token") String access_token, @WebParam(name = "product_id") String product_id, @WebParam(name = "quantity") String quantity, @WebParam(name = "consignee") String consignee, @WebParam(name = "fulladdress") String fulladdress, @WebParam(name = "postalcode") String postalcode, @WebParam(name = "phonenumber") String phonenumber, @WebParam(name = "creditcard") String creditcard, @WebParam(name = "verification") String verification,@WebParam(name = "user_agent") String user_agent) throws IOException, ParseException {
        int status=0;
        Connection conn = DbConnectionManager.getConnection();  
        String targetIS="ValidateToken";
        String urlParameters="access_token="+access_token;
        HttpURLConnection urlConn = UrlConnectionManager.doReqPost(targetIS,urlParameters,user_agent);
        String resp = UrlConnectionManager.getResponse(urlConn);
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(resp);
        String statusResp= (String) obj.get("status");
        String username = (String) obj.get("username");
        int quantity2 = Integer.parseInt(quantity);
        System.out.println(statusResp);
        switch (statusResp) {
            case "valid":{
                try {
                    String query = "SELECT * FROM catalogue WHERE product_id = '"+product_id+"'";
                    Statement stmt = conn.createStatement();
                    ResultSet rslt = stmt.executeQuery(query);
                    int product_price=0;
                    String product_name="";
                    String seller="";
                    String image="";
                    while(rslt.next())
                    {
                        product_name = rslt.getString("productname");
                        product_price = rslt.getInt("price");
                        seller = rslt.getString("username");
                        image = rslt.getString("imagepath");
                       
                    }
                    int total = product_price * quantity2;
                    String query2 = "INSERT INTO purchase (product_name, product_price, seller, buyer, image, quantity, consignee,"
                                   +"fulladdressbuyer, postalcode, newphonenumber, creditcard, verification, datebought, timebought) VALUES ("
                                   +"'" + product_name + "', '" + product_price +"', '" + seller + "', '" + username + "', '" + image + "', '" + quantity + "', '" + consignee +"', '" + fulladdress + "', '" + postalcode +"',"
                                   +"'" + phonenumber + "', '" + creditcard + "', '" + verification + "', curdate(), curtime())";
                    System.out.println("Query : "+query2);
                    PreparedStatement stmt2 = conn.prepareStatement(query2);
                    int i = stmt2.executeUpdate();
                    status = 1;
                } catch (SQLException ex) {
                    System.out.println("Insert  asdasd to database failed: An Exception has occurred! " + ex);
                }
                
                break;}
            case "non-valid":{
                status = 2;
                break;}
            default:{
                status = 3;
                break;}
        }
        return status;
    }
}
