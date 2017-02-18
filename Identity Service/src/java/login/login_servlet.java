package login;

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
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import token.TokenGenerator;

/**
 *
 * @author Scarletta's
 */
@WebServlet(name = "login_servlet", urlPatterns = {"/login_servlet"})
public class login_servlet extends HttpServlet {

     public String generateToken (int len){
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ ) 
           sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        
        return sb.toString();
        
    }
     
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       JSONObject arrayObj = new JSONObject();
        try {
            
            LoginBean user = new LoginBean();
            user.setUsername(request.getParameter("EmailorUsername"));
            user.setPassword(request.getParameter("password"));
            int userID=0;
            user = LoginDAO.login(user);
            System.out.println(user.isValid());
            if (user.isValid()) {
                InetAddress addr = InetAddress.getLocalHost();
                String user_agent = request.getHeader("user-agent");
                String ip_address = request.getRemoteAddr();//addr.getHostAddress();
                TokenGenerator tokGen = new TokenGenerator();
                tokGen.buildToken(user_agent, ip_address);
                String fullToken= tokGen.getToken();
                String token = tokGen.getStrToken();
                System.out.println("FULL TOKEN: "+fullToken);
                System.out.println("IP_ADDRESS FROM LOGIN.JSP: "+request.getLocalAddr());
                userID = LoginDAO.getUserID(user);
                arrayObj.put("access_token",token);
                arrayObj.put("user_id", ((Integer) userID).toString());
                arrayObj.put("username",user.getUsername());
                arrayObj.put("status","ok");
                System.out.println(token);
                System.out.println(((Integer) userID).toString());
                System.out.println(user.getUsername());
                
                String query = "INSERT INTO tokendata(user_id, token, create_time) VALUES ('"+ userID+"','"+ fullToken +"',NOW())";
                //to trace the process in console
                System.out.println("Your user name is " + user.getUsername());
                System.out.println("Your token is " + token);
                System.out.println("Query: "+query);
                Connection currentCon = null;
                currentCon = ConnectionManager.getConnection();
                try {
                    //connect to database
                   
                    PreparedStatement ps = currentCon.prepareStatement(query);
                    int i=ps.executeUpdate();
                    System.out.println("TOKEN MASUK DATABASE");
                    
                }

                catch (Exception ex) {
                    System.out.println("Register failed: An Exception has occurred! " + ex);
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
                
                //RequestDispatcher rd=request.getRequestDispatcher("catalog.jsp");  
                //System.out.println("wkwk");
                //rd.forward(request,response);
                //System.out.println("wkwkwk");
                
            }
            else {
                arrayObj.put("status","error");
            }
            
            response.setContentType("application/json:charset=UTF-8");
            response.getWriter().write(arrayObj.toString());
        }
        catch (Throwable theException) {
            System.out.println(theException);
        }
    }
} 

    


