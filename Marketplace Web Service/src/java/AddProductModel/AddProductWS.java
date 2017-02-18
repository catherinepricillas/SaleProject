/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AddProductModel;

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
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.servlet.annotation.MultipartConfig;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Asus X550ZE
 */
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
             maxFileSize=1024*1024*10,      // 10MB
             maxRequestSize=1024*1024*50)
@WebService(serviceName = "AddProductWS")
public class AddProductWS {

   
    @WebMethod(operationName = "addProduct")
    public int addProduct(@WebParam(name = "access_token") String access_token,@WebParam(name = "product_name") String product_name, @WebParam(name = "description") String description, @WebParam(name = "price") String price,@WebParam(name = "img_name") String img_name, @WebParam(name = "img_byte") byte[] img_byte,@WebParam(name = "user_agent") String user_agent) throws ProtocolException, IOException, ParseException {
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
                    String query = "INSERT INTO catalogue(productname,price,productdesc,username,dateadded,timeadded,purchases,imagepath) VALUES ('"+ product_name+"','"+ price +"','"+ description +"','"+ username +"','"+ dateadded +"','"+ timeadded +"','"+ purchases +"','" +image_path+"')";
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
        String path = "C:\\Users\\Asus X550ZE\\Documents\\NetBeansProjects\\Sale Project 2\\Web App\\web\\img";

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
