<%-- 
    Document   : register
    Created on : Nov 7, 2016, 12:40:56 PM
    Author     : Scarletta's
--%>

<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.io.DataOutputStream"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.URL"%>
<%
    // HTTP POST request
            String fullname;
            String username;
            String email;
            String password;
            String confirmpassword;
            String fulladdress;
            String postalcode;
            String phonenumber;
            String errorMessage="";
            
            if(request.getParameter("register")!=null) {
                fullname = request.getParameter("fullname");
                username = request.getParameter("username");
                email = request.getParameter("email");
                password = request.getParameter("password");
                confirmpassword = request.getParameter("confirmpassword");
                fulladdress = request.getParameter("fulladdress");
                postalcode = request.getParameter("postalcode");
                phonenumber = request.getParameter("phonenumber");
                String url = "http://localhost:8082/Identity_Service/register_servlet";
                URL connection = new URL(url);
                HttpURLConnection con = (HttpURLConnection) connection.openConnection();

                //add reuqest header
                con.setRequestMethod("POST");
                String USER_AGENT = request.getHeader("user-agent");
                con.setRequestProperty("user-agent",USER_AGENT);
                con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

                String urlParameters = "fullname="+fullname+
                                       "&username="+username+
                                       "&email="+email+
                                       "&password="+password+
                                       "&confirmpassword="+confirmpassword+
                                       "&fulladdress="+fulladdress+
                                       "&postalcode="+postalcode+
                                       "&phonenumber="+phonenumber; 
                // Send post request
                con.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                wr.writeBytes(urlParameters);
                wr.flush();
                wr.close();

                int responseCode = con.getResponseCode();

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder resp = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                        resp.append(inputLine);
                }
                in.close();
                JSONParser parser = new JSONParser();
                JSONObject obj = (JSONObject) parser.parse(resp.toString());
                String status= (String) obj.get("status");
             
                
               if(status.equals("ok")){
                    String token= (String) obj.get("access_token");
                    String userName = (String) obj.get("username");
                    String user_id = ((Long) obj.get("user_id")).toString();
                    Cookie userId = new Cookie("user_id",user_id);
                    Cookie access_token = new Cookie("access_token", token);
                    Cookie user_name = new Cookie("user_name", userName);
                    access_token.setMaxAge(60 * 60 * 24);
                    user_name.setMaxAge(60 * 60 * 24);
                    userId.setMaxAge(60 * 60 * 24);
                    response.addCookie(access_token);
                    response.addCookie(user_name);
                    response.addCookie(userId);
                    response.sendRedirect("catalog.jsp");
               }
               else{
                   errorMessage= "<span id='error'>USERNAME OR EMAIL NOT VALID</span>";
               }
                
            }
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
                <link rel="stylesheet" href="css/style.css">
		<link rel="stylesheet" href="css/register.css">
		<script src="js/register.js"></script>
		<title>
			Sale Project
		</title>
	</head>
	
	<body>
		<div class="container">
			<h1 class="webtitle"> <span id="sale">Sale</span><span id="project">Project</span></h1>
			<br/>

		<span id="PleaseRegister">Please register</span>
		<hr>
		<br/>
                <%=errorMessage%>
		<form id="formregis" name="formregister" method="post" onsubmit="return validateForm()">
			<label for="fname">Full Name</label>
			<input type="text" id="fname" name="fullname" onblur="return validateName()">
			<span id="register1" style="font-family:Calibri;color:red"></span>
			<br>
			<br>
			<label for="uname">Username</label>
			<input type="text" id="uname" name="username" onblur="return validateUName()"> 
			<span id="register2" style="font-family:Calibri;color:red"></span>
			<br>
			<br>
			<label for="email">Email</label>
			<input type="text" id="email" name="email" onblur="return (validateEmail() && validateEmail2())">
			<span id="register3" style="font-family:Calibri;color:red"></span>
			<br>
			<br>
			<label for="pass">Password</label>
			<input type="password" id="pass" name="password" onblur="return (validatePassword() && validatePassAndConfirm())">
			<span id="register4" style="font-family:Calibri;color:red"></span>
			<br>
			<br/>
			<label for="confirm">Confirm Password</label>
			<input type="password" id="confirm" name="confirmpassword" onblur="return (validateConfirm() && validatePassAndConfirm())">
			<span id="register5" style="font-family:Calibri;color:red"></span>
			<br>
			<br/>
			<label for="faddress">Full Address</label>
			<textarea id="faddress" name="fulladdress" onblur="return validateAddress()"></textarea>
			<span id="register6" style="font-family:Calibri;color:red"></span>
			<br>
			<br>
			<label for="postal">Postal Code</label>
			<input type="text" id="postal" name="postalcode" onblur="return validatePostal()">
			<span id="register7" style="font-family:Calibri;color:red"></span>
			<br>
			<br>
			<label for="phone">Phone Number</label>
			<input type="text" id="phone" name="phonenumber" onblur="return validatePhone()">
			<span id="register8" style="font-family:Calibri;color:red"></span>
			<br>
			<br>
			<br>
			<input type="submit" id="register" name="register" value="REGISTER">
		</form>
		<br>
		<br>
		<p id="already">Already registered? Login  <span id="here"><a href="login.jsp">here</a></span></p>
		
	</div>
</body>	
	
</html>
