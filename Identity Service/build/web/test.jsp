<%-- 
    Document   : test
    Created on : Nov 8, 2016, 3:48:44 AM
    Author     : Asus X550ZE
--%>

<%@page import="java.net.InetAddress"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="token.TokenGenerator" %>
<!DOCTYPE html>
<%
    InetAddress addr = InetAddress.getLocalHost();
    String user_agent=request.getHeader("user-agent");
    String ip_address = addr.getHostAddress();
    TokenGenerator tokGen = new TokenGenerator();
    tokGen.buildToken(user_agent, ip_address);
    String[] arrElToken = tokGen.getToken().split("#");
    for(int i=0;i<arrElToken.length;i++){
        out.println(arrElToken[i]);
        out.println("</br>");
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head> 
    <body>
      <%= tokGen.getToken() %>
    </body>
</html>
