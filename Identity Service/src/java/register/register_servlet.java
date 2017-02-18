package register;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import db.ConnectionManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.security.SecureRandom;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import static register.RegisterDAO.currentCon;
import token.TokenGenerator;


/**
 *
 * @author Scarletta's
 */
@WebServlet(name = "register_servlet", urlPatterns = {"/register_servlet"})
public class register_servlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    public String generateToken (int len){
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ ) 
           sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        
        return sb.toString();
        
    }
    
    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject arrayObj = new JSONObject();
        
        try {
            RegisterBean user = new RegisterBean();
            user.setFullname(request.getParameter("fullname"));
            user.setUsername(request.getParameter("username"));
            user.setEmail(request.getParameter("email"));
            user.setPassword(request.getParameter("password"));
            user.setAddress(request.getParameter("fulladdress"));
            user.setPostal(request.getParameter("postalcode"));
            user.setPhone(request.getParameter("phonenumber"));
            int userID=0;
            user = RegisterDAO.register(user);
            System.out.println(user.isValid());
            if (user.isValid()) {
                //HttpSession session = request.getSession(true);
                //session.setAttribute("currentSessionUser", user);
                InetAddress addr = InetAddress.getLocalHost();
                String user_agent = request.getHeader("user-agent");
                String ip_address = request.getRemoteAddr();//addr.getHostAddress();
                TokenGenerator tokGen = new TokenGenerator();
                tokGen.buildToken(user_agent, ip_address);
                String fullToken= tokGen.getToken();
                String token = tokGen.getStrToken();
                System.out.println("FULL TOKEN: "+fullToken);
                System.out.println("IP_ADDRESS FROM LOGIN.JSP: "+request.getLocalAddr());
                userID = RegisterDAO.getUserID(user);
                Timestamp current_date = new Timestamp(System.currentTimeMillis());
                String query = "INSERT INTO tokendata (user_id,token,create_time) VALUES ('"+userID+"','"+fullToken+"','"+current_date+"')";
                currentCon = ConnectionManager.getConnection();
                PreparedStatement ps = currentCon.prepareStatement(query);
                int i=ps.executeUpdate();
                System.out.println("MASUKIN TOKEN KE DATABASE BERHASIL");
                arrayObj.put("access_token",token);
                arrayObj.put("user_id",userID);
                arrayObj.put("username",user.getUsername());
                arrayObj.put("status","ok");
               System.out.println("UDAH KIRIM RESPONSE OKE KE REGISTER.JSP!");
            }
            else {
               arrayObj.put("status","error");
                System.out.println("UDAH KIRIM RESPONSE ERROR KE REGISTER.JSP!");
            }
            response.setContentType("application/json:charset=UTF-8");
            response.getWriter().write(arrayObj.toString());
            
        }
        catch (Throwable theException) {
            System.out.println(theException);
        }
    }
    

}
