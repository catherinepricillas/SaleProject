package logout;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import db.ConnectionManager;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.*;
import java.util.*;
import java.sql.*;
import org.json.simple.JSONObject;
/**
 *
 * @author Scarletta's
 */
@WebServlet(urlPatterns = {"/logout_servlet"})
public class logout_servlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
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
        String token = request.getParameter("access_token");
        Connection currentCon = null;
        JSONObject arrayObj = new JSONObject();
     
        String fullToken = token+"#"+request.getHeader("user-agent")+"#"+request.getRemoteAddr();
        System.out.println("fulltoken:"+fullToken);
        String query = "DELETE FROM tokendata WHERE token ='" + fullToken +"'";
        
        try {
            //connect to database
            currentCon = ConnectionManager.getConnection();
            PreparedStatement ps = currentCon.prepareStatement(query);
            int i=ps.executeUpdate();
            arrayObj.put("status","ok");
            System.out.println("DELETE TOKEN");
            
          
        }
        
        catch (Exception ex) {
            System.out.println("Log out failed: An Exception has occurred! " + ex);
        }

        finally {
            if (currentCon!=null) {
               try {
                   currentCon.close();
               }
               catch (Exception e) {}
               currentCon = null;
            }
        }
        response.setContentType("application/json:charset=UTF-8");
        response.getWriter().write(arrayObj.toString());
    }


}
