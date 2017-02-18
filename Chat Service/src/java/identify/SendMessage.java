/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package identify;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Asus X550ZE
 */
@WebServlet(name = "SendMessage", urlPatterns = {"/SendMessage"})
public class SendMessage extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
            String sender = request.getParameter("sender");
            String receiver = request.getParameter("receiver");
            String message = request.getParameter("body");
            int index = SaveToken.storage.findUsername(receiver);
            System.out.println(index);
            String tokenTo =SaveToken.storage.getData().get(index).getToken();
            JSONObject obj = new JSONObject();
            obj.put("sender",sender);
            obj.put("receiver",receiver);
            obj.put("body",message);
            JSONObject arrayObj = new JSONObject();
            arrayObj.put("to",tokenTo);
            arrayObj.put("data",obj);
            System.out.println(arrayObj.toString());
            String USER_AGENT = "Mozilla/5.0";
            String authKey = "key=AIzaSyBPldzkpB5YtLm3N8cYbdoweqtn5Dk3IfQ";
            String contentType = "application/json";
            String url = "https://fcm.googleapis.com/fcm/send";
            URL connection = new URL(url);
            HttpURLConnection con = (HttpURLConnection) connection.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            con.setRequestProperty("Content-Type",contentType);
            con.setRequestProperty("Authorization",authKey);

            String urlParameters = arrayObj.toString();
            // Send post request
            con.setDoOutput(true); 
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();


            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder resp = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                    resp.append(inputLine);
            }
            in.close(); 
            String acrHeaders = request.getHeader("Access-Control-Request-Headers");
            String acrMethod = request.getHeader("Access-Control-Request-Method");
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Headers", acrHeaders);
            response.setHeader("Access-Control-Allow-Methods", acrMethod);
            response.setContentType("application/json:charset=UTF-8");
            response.getWriter().write(resp.toString());
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(SendMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(SendMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
