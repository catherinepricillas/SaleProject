/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DelProductWS;

import connection.DbConnectionManager;
import connection.UrlConnectionManager;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
@WebService(serviceName = "DelProductWS")
public class DelProductWS {



    @WebMethod(operationName = "delProduct")
    public int delProduct(@WebParam(name = "access_token") String access_token, @WebParam(name = "product_id") String product_id,@WebParam(name = "user_agent") String user_agent) throws IOException, ParseException {
        int status= 0 ;
        Connection dbConn = DbConnectionManager.getConnection();
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
             try{
            String query = "DELETE FROM catalogue WHERE product_id='"+product_id+"'";
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
}
