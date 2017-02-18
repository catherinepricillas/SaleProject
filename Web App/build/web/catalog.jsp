<%-- 
    Document   : catalog
    Created on : Nov 7, 2016, 12:48:24 PM
    Author     : Scarletta's
--%>

<%@page import="java.util.ArrayList"%>
<%@include file="layout.jsp" %>
 <%
    ArrayList<String> listProduct = new ArrayList<String>();
    
    listProduct.add("");

    try {
	CatalogWS.CatalogWS_Service service = new CatalogWS.CatalogWS_Service();
	CatalogWS.CatalogWS port = service.getCatalogWSPort();
	 // TODO initialize WS operation arguments here
	java.lang.String accessToken = access_token;
	java.lang.String userAgent = USER_AGENT;  
        System.out.println(userAgent);
	// TODO process result here
	java.util.List<java.lang.String> result = port.viewCatalog(accessToken, userAgent);
        listProduct= (ArrayList) result;
    } catch (Exception ex) {
	// TODO handle custom exceptions here
    }
    

     System.out.println(listProduct);
    
    System.out.println("Status = "+listProduct.get(0));
    if(listProduct.get(0).equals("2")||listProduct.get(0).equals("3"))
    {
        Cookie error = new Cookie("err_msg", "Your session is over!");
        error.setMaxAge(3);
        response.addCookie(error);
        response.sendRedirect("login.jsp");
    }
    else{
            if(request.getParameter("go")!=null){

                try {
                    CatalogWS.CatalogWS_Service service = new CatalogWS.CatalogWS_Service();
                    CatalogWS.CatalogWS port = service.getCatalogWSPort();
                     // TODO initialize WS operation arguments here
                    java.lang.String keyword =request.getParameter("searchbox");
                    java.lang.String filter = request.getParameter("radiobutton");
                    java.lang.String userId = user_id;
                    // TODO process result here
                    java.util.List<java.lang.Object> result = port.searchProduct(keyword, filter, userId);
                    listProduct= (ArrayList)result;
                } catch (Exception ex) {
                    // TODO handle custom exceptions here
                }
         }
    }

%>
                <div ng-app="ChatApp" ng-controller="ChatCtrl">
                 <div id="test"></div>   
		<h1><span id="asking">What are you going to buy today?</span></h1>
                <hr>
		<form method="post">
			<input id="search_bar" name="searchbox" type="text" placeholder=" Search catalog ...">
			<input id="search_button"name="go" type="submit" value="GO">
			<br/><br/>
			<span style="font-size:12px">by 
			<input type="radio" name="radiobutton" value="product" style="margin-left:20px;height:10px" checked>product <br/>
			<input type="radio" name="radiobutton" value="store" style="margin-left:37px;height:10px">store <br/>
			</span>
		</form>
		<br/> 
                
                <ul class="list-product">
                    <%
                            
                        for (int i = 0; i < listProduct.size(); i++) {
                            out.println(listProduct.get(i));
                        }
                    %>   
                </ul>
                
                    <div class = "chat" ng-show="chatBoxShowed" >
                        <div class ="top">
                            <img src="img/online-circle.png" height="10px">
                            {{receiver}}
                            <img ng-click="chatBoxShowed=!chatBoxShowed"src="img/close.png" height="15px" style="float:right">
                        </div>
                        <div class="middle" id="messages">
                            <div ng-repeat="x in messages | filter:byUserName:x.receiver"  class="{{x.style}}" >
                                {{x.body}}
                            </div>
                        </div>
                        <div class="bottom">
                            <form ng-submit="push()">
                                <textarea placeholder=" Type your message here.." id="inputchat" ng-model="message"></textarea>
                            <input type="submit" id="sendchat" name="sendchat"  value="SEND">
                            </form>
                        </div>
                    </div>
                </div> 
            </div>
            <script src="js/chat-app.js"></script>
	</body>
</html>