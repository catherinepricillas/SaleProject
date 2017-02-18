<%-- 
    Document   : your_product
    Created on : Nov 11, 2016, 4:37:24 AM
    Author     : Asus X550ZE
--%>

<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="layout.jsp" %>
<script src="js/your_product.js"> </script>
<%
    ArrayList<String> listProduct = new ArrayList<String>();
    listProduct.add("");
   
  
    try {
	YourProductWS.YourProductWS_Service service = new YourProductWS.YourProductWS_Service();
	YourProductWS.YourProductWS port = service.getYourProductWSPort();
	 // TODO initialize WS operation arguments here
	java.lang.String accessToken = access_token;
	java.lang.String userAgent = USER_AGENT;
	// TODO process result here
	java.util.List<java.lang.String> result = port.getProduct(accessToken, userAgent);
	listProduct = (ArrayList) result;
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
  
    System.out.println(listProduct);

 
    if(listProduct.get(0).equals("2")||listProduct.get(0).equals("3"))
    {
       Cookie error = new Cookie("err_msg", "Your session is over!");
        error.setMaxAge(3);
        response.addCookie(error);
        response.sendRedirect("login.jsp");
    }
%>
<!DOCTYPE html>
                <div>
                    <h1><span id="asking">What are you going to sell today?</span></h1>
                    <hr></hr>
                    <br/><br/>
                    <ul class="list-product">
                        <%
                            //OUTPUT PRODUCT
                            for (int i = 0; i < listProduct.size(); i++) {
                                out.println(listProduct.get(i));
                            }
                        %>
                    </ul>
                </div>
            </div>
     </body>
</html>

