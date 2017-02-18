/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import YourProductModel.YourProductWS;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

/**
 *
 * @author Asus X550ZE
 */
public class UrlConnectionManager {
    static String url = "http://localhost:8082/Identity_Service/";

    public static  HttpURLConnection doReqPost(String path,String urlParameters,String user_agent) throws ProtocolException, IOException{
        
        URL connection = new URL(url+path);
        HttpURLConnection con = (HttpURLConnection) connection.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        
        con.setRequestProperty("user-agent",user_agent);
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        return con;
    }

    public static String getResponse(HttpURLConnection con) throws IOException{
        
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder resp = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            resp.append(inputLine);
        }
        in.close();
        return resp.toString();
    }
    
}
