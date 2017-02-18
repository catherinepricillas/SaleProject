/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import CatalogWS.CatalogWS_Service;
import CatalogWS.IOException_Exception;
import CatalogWS.ParseException_Exception;
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
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author Scarletta's
 */
@WebServlet(urlPatterns = {"/like"})
public class like extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/Marketplace_Web_Service/CatalogWS.wsdl")
    private CatalogWS_Service service;

   
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request,response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Cookie [] cookies = request.getCookies();
         String username = "";
         String access_token = "";
    for (int i = 0; i < cookies.length; i++){
            Cookie cookie = cookies[i];
            if(cookie.getName().equals("user_name"))
                username=cookie.getValue();
            if(cookie.getName().equals("access_token"))
                access_token=cookie.getValue();
         }
         String product_id = request.getParameter("product_id");
         int status= 0;
         String user_agent =request.getHeader("user-agent");
        try {
            status = likeProduct(access_token,product_id,user_agent);
        } catch (ParseException_Exception ex) {
            Logger.getLogger(like.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException_Exception ex) {
            Logger.getLogger(like.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(status==1){
        response.sendRedirect("catalog.jsp");
        }else{
            Cookie error = new Cookie("err_msg", "Your session is over!");
            error.setMaxAge(3);
            response.addCookie(error);
            response.sendRedirect("login.jsp");
        }
    }

    private int likeProduct(java.lang.String accessToken, java.lang.String productId, java.lang.String userAgent) throws IOException_Exception, ParseException_Exception {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        CatalogWS.CatalogWS port = service.getCatalogWSPort();
        return port.likeProduct(accessToken, productId, userAgent);
    }

 

       
    

}
