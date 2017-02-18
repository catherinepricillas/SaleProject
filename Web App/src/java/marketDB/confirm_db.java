/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketDB;

import ConfirmWS.ConfirmWS_Service;
import ConfirmWS.IOException_Exception;
import ConfirmWS.ParseException_Exception;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author Asus X550ZE
 */
@WebServlet(name = "confirm_db", urlPatterns = {"/confirm_db"})
public class confirm_db extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/Marketplace_Web_Service/ConfirmWS.wsdl")
    private ConfirmWS_Service service;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         Cookie[] cookies = request.getCookies();
        String access_token ="";
        for (int i = 0; i < cookies.length; i++){
            if (cookies[i].getName().equals("access_token")){
                access_token = cookies[i].getValue();
            }
        }
        String productId = request.getParameter("product_id");
        String quantity = request.getParameter("quantity");
        String consignee = request.getParameter("consignee");
        String fulladdress = request.getParameter("fulladdress");
        String postalcode = request.getParameter("postalcode");
        String phonenumber = request.getParameter("phonenumber");
        String creditcard = request.getParameter("creditcard");
        String verification = request.getParameter("verification");
        int status =0;
        String user_agent =request.getHeader("user-agent");
        try {
            status= confirmPurchase(access_token,productId,quantity,consignee,fulladdress,postalcode,phonenumber,creditcard,verification,user_agent);
        } catch (IOException_Exception ex) {
            Logger.getLogger(confirm_db.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException_Exception ex) {
            Logger.getLogger(confirm_db.class.getName()).log(Level.SEVERE, null, ex);
        }
   
        if (status == 1){
            response.sendRedirect("purchases.jsp");
        }
        else
        {
            Cookie error = new Cookie("err_msg", "Your session is over!");
            error.setMaxAge(3);
            response.addCookie(error);
            response.sendRedirect("login.jsp");
        }
        
    }

    private int confirmPurchase(java.lang.String accessToken, java.lang.String productId, java.lang.String quantity, java.lang.String consignee, java.lang.String fulladdress, java.lang.String postalcode, java.lang.String phonenumber, java.lang.String creditcard, java.lang.String verification, java.lang.String userAgent) throws IOException_Exception, ParseException_Exception {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        ConfirmWS.ConfirmWS port = service.getConfirmWSPort();
        return port.confirmPurchase(accessToken, productId, quantity, consignee, fulladdress, postalcode, phonenumber, creditcard, verification, userAgent);
    }

}
