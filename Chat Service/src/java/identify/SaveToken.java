/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package identify;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Asus X550ZE
 */
@WebServlet(name = "SaveToken", urlPatterns = {"/SaveToken"})
public class SaveToken extends HttpServlet {
    static final IdentityStorage storage  = new IdentityStorage(new ArrayList<Identity>());
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
            throws ServletException, IOException {
        String acrHeaders = request.getHeader("Access-Control-Request-Headers");
        String acrMethod = request.getHeader("Access-Control-Request-Method");
        String token =request.getParameter("token");
        String username =request.getParameter("username");
        storage.UpdateStorage(token, username);
        JSONObject arrayObj = new JSONObject();
        JSONArray dataArr = new JSONArray();
        arrayObj.put("status", "Ok");
        for(int i=0;i<storage.getData().size();i++){
            JSONObject data = new JSONObject();
            data.put("token",storage.getData().get(i).getToken());
            data.put("username",storage.getData().get(i).getUsername()); 
            dataArr.add(data);
        }
        if(storage.getData().size()>9){
        storage.getData().clear();
        }
        arrayObj.put("storage",dataArr);
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", acrHeaders);
        response.setHeader("Access-Control-Allow-Methods", acrMethod);
        response.setContentType("application/json:charset=UTF-8");
        response.getWriter().write(arrayObj.toString());  
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
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
