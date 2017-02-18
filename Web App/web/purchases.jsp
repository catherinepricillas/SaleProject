<%-- 
    Document   : purchases
    Created on : Nov 12, 2016, 8:41:23 PM
    Author     : UX303L
--%>

<%@page import= "java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="layout.jsp" %>	
<%
    ArrayList<String> listPurchases = new ArrayList<String>();
    listPurchases.add("");

    try {
	PurchasesWS.PurchasesWS_Service service = new PurchasesWS.PurchasesWS_Service();
	PurchasesWS.PurchasesWS port = service.getPurchasesWSPort();
	 // TODO initialize WS operation arguments here
	java.lang.String accessToken = access_token;
	// TODO process result here
	java.util.List<java.lang.String> result = port.getPurchases(accessToken,USER_AGENT);
	 listPurchases = (ArrayList) result;
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
   
    System.out.println(listPurchases);
    if (listPurchases.get(0).equals("2") || listPurchases.get(0).equals("3")){
        Cookie error = new Cookie("err_msg", "Your session is over!");
        error.setMaxAge(3);
        response.addCookie(error);
        response.sendRedirect("login.jsp");
       }
%>

<!DOCTYPE HTML>
            <div>
		<h1><span id="asking">Here are your purchases</span></h1>
		<hr></hr>
		<br/><br/>
		<ul class="list-product">
                    <%
                        for (int i = 0; i < listPurchases.size(); i++){
                           out.println(listPurchases.get(i));
                        }
                    %>
                </ul>
            </div>
        </div>
    </body>
</html>
       
                