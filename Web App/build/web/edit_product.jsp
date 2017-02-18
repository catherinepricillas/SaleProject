<%-- 
    Document   : edit_product
    Created on : Nov 11, 2016, 6:35:32 AM
    Author     : Asus X550ZE
--%>

<%@page import="java.sql.Array"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="layout.jsp"%>
<%
    String answers = "";
    String product_id=request.getParameter("product_id");;
    String productname="";
    String desc="";
    String price="";
    String imagepath="";
    int valid = 0;
  

    try {
	EditProductWS.EditProductWS_Service service = new EditProductWS.EditProductWS_Service();
	EditProductWS.EditProductWS port = service.getEditProductWSPort();
	 // TODO initialize WS operation arguments here
	java.lang.String accessToken = access_token;
	java.lang.String productId = product_id;
	// TODO process result here
	java.lang.String result = port.setCurrentProduct(accessToken, productId,USER_AGENT);
	answers = result;
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }


   if(answers.equals("2")||answers.equals("2"))
   {
        Cookie error = new Cookie("err_msg", "Your session is over!");
        error.setMaxAge(3);
        response.addCookie(error);
        response.sendRedirect("login.jsp");
   }

 %>
<!DOCTYPE html>
<script src="js/edit_product.js"></script>
             <div>
                <h1><span id="asking">Please update your product here</span></h1>
                <hr></hr>
                <br/>
                <form id="add_edit_form" enctype="multipart/form-data" onsubmit="return validateForm()" action="update_db?&product_id=<%=product_id%>" method="post">
                     <%= answers%>
                     Photo <br/>
                     <input type="file" name="imgcatch" value="Choose file">
                     <br/>
                     <input type="submit" name="update" value="UPDATE" style="margin-left:377px">
                     <input type="reset" value="CANCEL" style="margin-left:20px">
                 </form>
              </div>
	</div>
    </body>		
</html>
