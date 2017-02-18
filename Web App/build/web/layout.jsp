<%-- 
    Document   : layout
    Created on : Nov 9, 2016, 3:06:39 AM
    Author     : Asus X550ZE
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Cookie [] cookies = request.getCookies();
   String errorMsg = "";
   String username = "";
   String access_token = "#";
   String user_id="";
    for (int i = 0; i < cookies.length; i++){
            Cookie cookie = cookies[i];
            if(cookie.getName().equals("user_name"))
                username=cookie.getValue();
            if(cookie.getName().equals("access_token"))
                access_token=cookie.getValue();
             if(cookie.getName().equals("user_id"))
                user_id=cookie.getValue();
         }
    System.out.println(username);
    System.out.println(access_token);
    System.out.println(user_id);
    String USER_AGENT =request.getHeader("user-agent");
    String s="<span hidden id='user_name'>"+username+"</span>";
    String tokenFCM="<span id='token'></span>";
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <title>
            Sale Project
        </title>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular-cookies.min.js"></script>
        <script src="https://www.gstatic.com/firebasejs/3.6.1/firebase.js"></script>
    </head>

    <body>
        <%=s%>
        <%=tokenFCM%>
        
        <div class="container">
            <header>
                <h1 class="webtitle"> <span id="sale">Sale</span><span id="project">Project</span></h1>
                <div id="greeting"><b>Hi,<%=username%>!</b></div>
                <div id="logout"><a href="logout2_servlet" ><b>logout</b></a></div>
            </header>
            <div class="nav-bar">
                <div id="catalog"><a id="" href="catalog.jsp">Catalog</a></div>
                <div id="yourproduct"><a id="" href="your_product.jsp">Your Product</a></div>
                <div id="addproduct"><a id="" href="add_product.jsp">Add Product</a></div>
                <div id="sales"><a id="" href="sales.jsp">Sales</a></div>
                <div id="purchases"><a id="" href="purchases.jsp">Purchases</a></div>
            </div>