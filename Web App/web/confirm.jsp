<%-- 
    Document   : confirm
    Created on : Nov 11, 2016, 1:11:51 AM
    Author     : UX303L
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="js/confirm.js"></script>
<%@ include file="layout.jsp" %>


<%
    String product_id =request.getParameter("product_id");
    String answer = "";

    try {
	ConfirmWS.ConfirmWS_Service service = new ConfirmWS.ConfirmWS_Service();
	ConfirmWS.ConfirmWS port = service.getConfirmWSPort();
	 // TODO initialize WS operation arguments here
	java.lang.String accessToken = access_token;
	java.lang.String productid = product_id;
	java.lang.String userAgent = USER_AGENT;
	// TODO process result here
	java.lang.String result = port.getProductData(accessToken, productid, userAgent);
	answer = result;
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
 



   if(answer.equals("2")|| answer.equals("3"))
   {
        Cookie error = new Cookie("err_msg", "Your session is over!");
        error.setMaxAge(3);
        response.addCookie(error);
        response.sendRedirect("login.jsp");
   }

 %>
<!DOCTYPE html>
                <%
                    out.println(answer);
                %>
                <br/>
            </div>
        </div>
    </body>
 </html>


