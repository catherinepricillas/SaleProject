<%-- 
    Document   : sales
    Created on : Nov 11, 2016, 1:13:15 AM
    Author     : UX303L
--%>

<%@page import= "java.util.ArrayList" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="layout.jsp" %>	
<%
    ArrayList<String> listSales = new ArrayList<String>();
    listSales.add("");
   
    try {
	SalesWS.SalesWS_Service service = new SalesWS.SalesWS_Service();
	SalesWS.SalesWS port = service.getSalesWSPort();
	 // TODO initialize WS operation arguments here
	java.lang.String accessToken = access_token;
	// TODO process result here
	java.util.List<java.lang.String> result = port.getSales(accessToken,USER_AGENT);
	listSales = (ArrayList) result;
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
  
   System.out.println(listSales);
    if (listSales.get(0).equals("2") || listSales.get(0).equals("3")){
               Cookie error = new Cookie("err_msg", "Your session is over!");
        error.setMaxAge(3);
        response.addCookie(error);
        response.sendRedirect("login.jsp");
       }
%>

<!DOCTYPE HTML>
            <div>
		<h1><span id="asking">Here are your sales</span></h1>
		<hr></hr>
		<br/><br/>
		<ul class="list-product">
                    <%
                        for (int i = 0; i < listSales.size(); i++){
                           out.println(listSales.get(i));
                        }
                    %>
                </ul>
            </div>
        </div>
    </body>
</html>
       
                