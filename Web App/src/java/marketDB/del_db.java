/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketDB;


import DelProductWS.DelProductWS_Service;
import DelProductWS.IOException_Exception;
import DelProductWS.ParseException_Exception;
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
import org.json.simple.JSONObject;

/**
 *
 * @author Asus X550ZE
 */
@WebServlet(name = "del_db", urlPatterns = {"/del_db"})
public class del_db extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/Marketplace_Web_Service/DelProductWS.wsdl")
    private DelProductWS_Service service;



   

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
          String access_token="";
          for(int i=0;i<cookies.length;i++){
                if(cookies[i].getName().equals("access_token")){
                    access_token =  cookies[i].getValue();
                }
            }
          String product_id = request.getParameter("product_id");
          int status=0;
          String user_agent=request.getHeader("user-agent");
        try {
            status=delProduct(access_token,product_id,user_agent);
        } catch (IOException_Exception ex) {
            Logger.getLogger(del_db.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException_Exception ex) {
            Logger.getLogger(del_db.class.getName()).log(Level.SEVERE, null, ex);
        }
         if(status==1)
         {
            JSONObject arrayObj = new JSONObject();
            arrayObj.put("status",status);
            response.sendRedirect("your_product.jsp");
         }
         else{
             Cookie error = new Cookie("err_msg", "Your session is over!");
             error.setMaxAge(3);
             response.addCookie(error);
             response.sendRedirect("login.jsp");
         }
    }

    private int delProduct(java.lang.String accessToken, java.lang.String productId, java.lang.String userAgent) throws ParseException_Exception, IOException_Exception {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        DelProductWS.DelProductWS port = service.getDelProductWSPort();
        return port.delProduct(accessToken, productId, userAgent);
    }

  



}
