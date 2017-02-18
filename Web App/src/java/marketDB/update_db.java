/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package marketDB;



import EditProductWS.EditProductWS_Service;
import EditProductWS.IOException_Exception;
import EditProductWS.ParseException_Exception;
import EditProductWS.ProtocolException_Exception;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.ws.WebServiceRef;
import org.json.simple.JSONObject;

/**
 *
 * @author Asus X550ZE
 */
@WebServlet(name = "update_db", urlPatterns = {"/update_db"})
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
             maxFileSize=1024*1024*10,      // 10MB
             maxRequestSize=1024*1024*50)
public class update_db extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8081/Marketplace_Web_Service/EditProductWS.wsdl")
    private EditProductWS_Service service;


  public  byte[] readFully(InputStream input) throws IOException
    {
        byte[] buffer = new byte[8192];
        int bytesRead;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        while ((bytesRead = input.read(buffer)) != -1)
        {
            output.write(buffer, 0, bytesRead);
        }
        return output.toByteArray();
    }
    
    private String getFileName(final Part part) {
            final String partHeader = part.getHeader("content-disposition");

            for (String content : part.getHeader("content-disposition").split(";")) {
                if (content.trim().startsWith("filename")) {
                    return content.substring(
                            content.indexOf('=') + 1).trim().replace("\"", "");
                }
            }
            return null;
        }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          String path="C:\\Users\\Asus X550ZE\\Documents\\NetBeansProjects\\Web App\\web\\img";
          Cookie[] cookies = request.getCookies();
          String access_token="";
          for(int i=0;i<cookies.length;i++){
                if(cookies[i].getName().equals("access_token")){
                    access_token =  cookies[i].getValue();
                }
            }
          String product_id = request.getParameter("product_id");
          String product_name=request.getParameter("prod_name");
          String desc = request.getParameter("desc_box");
          String price = request.getParameter("price_box");
          Part filePart = request.getPart("imgcatch");
          String fileName=  getFileName(filePart);
          
          InputStream in =filePart.getInputStream();
          byte[] imgByte = readFully(in);
          int status = 0;
          String user_agent = request.getHeader("user-agent");
        try {
            status=editProduct(access_token,product_id,product_name,desc,price,fileName,imgByte,user_agent);
            //WEB SERVICE UPDATE PRODUCT
        } catch (IOException_Exception ex) {
            Logger.getLogger(update_db.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ProtocolException_Exception ex) {
            Logger.getLogger(update_db.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException_Exception ex) {
            Logger.getLogger(update_db.class.getName()).log(Level.SEVERE, null, ex);
        }
          
        JSONObject arrayObj = new JSONObject();
        arrayObj.put("status",status);
        if(status==1)
        {
            response.sendRedirect("your_product.jsp");
        }else{
             Cookie error = new Cookie("err_msg", "Your session is over!");
             error.setMaxAge(3);
             response.addCookie(error);
             response.sendRedirect("login.jsp");
        }
            
    }

    private int editProduct(java.lang.String accessToken, java.lang.String productId, java.lang.String productName, java.lang.String description, java.lang.String price, java.lang.String imgName, byte[] imgByte, java.lang.String userAgent) throws ProtocolException_Exception, IOException_Exception, ParseException_Exception {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        EditProductWS.EditProductWS port = service.getEditProductWSPort();
        return port.editProduct(accessToken, productId, productName, description, price, imgName, imgByte, userAgent);
    }

 

  

   


}
