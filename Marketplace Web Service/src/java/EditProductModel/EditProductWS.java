/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EditProductModel;

import connection.DbConnectionManager;
import connection.UrlConnectionManager;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.jws.Oneway;
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
@WebService(serviceName = "EditProductWS")
public class EditProductWS {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "validasiTokenEdit")
    public String validasiTokenEdit(@WebParam(name = "access_token") String access_token,@WebParam(name = "user_agent") String user_agent) throws IOException, ParseException {
        String targetIS="ValidateToken";
        String urlParameters="access_token="+access_token;
        HttpURLConnection urlConn = UrlConnectionManager.doReqPost(targetIS,urlParameters,user_agent);
        String resp = UrlConnectionManager.getResponse(urlConn);
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(resp);
        String statusResp = (String) obj.get("status");
        
        return statusResp;
     }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "setCurrentProduct")
    public String setCurrentProduct(@WebParam(name = "access_token") String access_token,@WebParam(name = "product_id") String product_id,@WebParam(name = "user_agent") String user_agent) throws IOException, ParseException {
        
       String answer= "";
        String resp = validasiTokenEdit(access_token,user_agent);
        if(resp.equals("valid"))
        {
          Connection dbConn = DbConnectionManager.getConnection();
          try {
                String query = "SELECT * FROM catalogue WHERE product_id='"+product_id+"'";
                Statement ps = dbConn.createStatement();
                ResultSet rs = ps.executeQuery(query);
                while(rs.next()){
                    String productname = rs.getString("productname");
                    String desc = rs.getString("productdesc");
                    String price = rs.getString("price");
                    String imagepath=rs.getString("imagepath");
                     answer= "<label for=\"name\"> Name </label><br/>\n" +
"                     <input type=\"text\" id=\"name\" onblur=\"return validateName()\" name=\"prod_name\" value=\""+productname+"\"><br/>\n" +
"                     <div id=\"edit1\"></div>\n" +
"                     <label for=\"desc\"> Description(max 200 chars)<br/>\n" +
"                     <textarea class=\"desc-box\" id=\"desc\" style=\"height:80px\" onblur=\"return validateDesc()\" onkeypress=\"return validateDesc200()\" name=\"desc_box\">"+desc+"</textarea><br/>\n" +
"                     <div id=\"edit2\"></div>\n" +
"                     <label for=\"price\"> Price (IDR) <br/>\n" +
"                     <input type=\"text\" onblur=\"return validatePrice()\" onkeypress=\"return validatePriceNumber(event)\" name=\"price_box\" value=\""+price+"\"><br/>\n" +
"                     <div id=\"edit3\"></div>";
                   
                } 
                
            } catch (SQLException ex) {
                System.out.println("Collect from database failed: An Exception has occurred! " + ex);
             
            }
        }else if (resp.equals("non-valid"))
        {
             answer="2";
            
        }else{
            answer="3";
        }
      
      return answer;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "editProduct")
    public int editProduct(@WebParam(name = "access_token") String access_token,@WebParam(name = "product_id") String product_id, @WebParam(name = "product_name") String product_name, @WebParam(name = "description") String description, @WebParam(name = "price") String price,@WebParam(name = "img_name") String img_name, @WebParam(name = "img_byte") byte[] img_byte,@WebParam(name = "user_agent") String user_agent) throws ProtocolException, IOException, ParseException {
        int status=0;
        Connection dbConn = DbConnectionManager.getConnection();
        java.util.Date date = new java.util.Date();
        java.sql.Date dateadded = new java.sql.Date(date.getTime());
        java.sql.Time timeadded = new java.sql.Time(date.getTime());
        int purchases = 0 ;
                
        String targetIS="ValidateToken";
        String urlParameters="access_token="+access_token;
        HttpURLConnection urlConn = UrlConnectionManager.doReqPost(targetIS,urlParameters,user_agent);
        String resp = UrlConnectionManager.getResponse(urlConn);
        
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(resp);
        String statusResp= (String) obj.get("status");
        String username = (String) obj.get("username");
        
        switch (statusResp) {
            case "valid":
                try {
                    String image_path = uploadImage(img_byte, img_name);
                    String query = "UPDATE catalogue "
                            + "SET productname= '"+product_name+"'"
                            + ",price = '"+price+"'"
                            + ",productdesc = '"+description+"'"
                            + ",imagepath = '"+image_path+"'"
                            + "WHERE product_id ='"+product_id+"'";
                    PreparedStatement ps = dbConn.prepareStatement(query);
                    int i=ps.executeUpdate();
                }catch (SQLException ex) {
                    System.out.println("Inser to database failed: An Exception has occurred! " + ex);
                }
                finally {
                    if (dbConn!=null) {
                        try {
                            dbConn.close();
                        }
                        catch (SQLException e) {
                            System.out.println(e);
                        }
                        dbConn = null;
                    }
                }   
                status =1;
                break;
            case "non-valid":
                status = 2;
                break;
            default:
                status =3;
                break;
        }
        return status;
    }
    
     private String uploadImage(byte[] byteImage, String fileName) {
        String photo = "";
        String path = "C:\\Users\\Asus X550ZE\\Documents\\NetBeansProjects\\Web App\\web\\img";

        OutputStream out = null;
        InputStream filecontent = null;
        try {
            out = new FileOutputStream(new File(path + File.separator
                    + fileName));

            filecontent = new ByteArrayInputStream(byteImage);
            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
                photo = path + "\\" + fileName;

            }
        } catch (Exception e) {

        }
        photo = "img/" + fileName;
        return photo;
    }
}
