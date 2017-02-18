/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Scarletta's
 */
@WebServlet(urlPatterns = {"/logout2_servlet"})
public class logout2_servlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

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
        doPost(request, response);
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
        String access_token = "";
        Cookie cookie= null;
        Cookie[] cookies = request.getCookies();
         for (int i = 0; i < cookies.length; i++){
            Cookie cookie1 = cookies[i];
            if(cookies[i].getName().equals("access_token"))
            {
                access_token=cookie1.getValue();
                cookie = cookie1;
            }
         }
         System.out.println("TOKEN = "+access_token);
          
             
             
             
       
        String USER_AGENT = request.getHeader("user-agent");
        String url = "http://localhost:8082/Identity_Service/logout_servlet";
        URL connection = new URL(url);
        HttpURLConnection con = (HttpURLConnection) connection.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = "access_token="+access_token;

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
        
        JSONParser parser = new JSONParser();
        JSONObject obj = null;
         try {
             obj = (JSONObject) parser.parse(resp.toString());
         } catch (ParseException ex) {
             Logger.getLogger(logout2_servlet.class.getName()).log(Level.SEVERE, null, ex);
         }
         
        String status= (String) obj.get("status");
        System.out.println(status);
        if(status.equals("ok")){
             cookie.setMaxAge(0);
             response.sendRedirect("login.jsp");
         }
         else{
        
         }
               
               
               
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    

}
