<%-- 
    Document   : catalog
    Created on : Nov 9, 2016, 3:09:49 AM
    Author     : Asus X550ZE
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="layout.jsp"%>
<script src="js/add_product.js"></script>
            <div>
                <h1><span id="asking">Please add your product here</span></h1>
                <hr></hr>
                <br/>
                <form id="add_edit_form" method="post" action="add_db" onsubmit="return validateAddForm()" enctype="multipart/form-data">
                    <label for="name"> Name </label><br/>
                    <input type="text" id="name" name="prod_name" onblur="return validateName()"><br/>
                    <div id="add1"></div>
                    <label for="desc"> Description(max 200 chars)<br/>
                    <textarea class="desc-box" id="desc" style="height:80px"  name="desc_box" onkeypress="return validateDesc200()" onblur="return validateDesc()"></textarea><br/>
                    <div id="add2"></div>
                    <label for="price"> Price (IDR) <br/>
                    <input type="text" name="price_box" onkeypress="return validatePriceNumber(event)" onblur="return validatePrice()"><br/>
                    <div id="add3"></div>
                     Photo <br/>
                     <input id="pic" type="file" name="imgcatch" value="Choose file" onblur="return validateImageFile()">
                     <div id="add4"></div>
                     <br/>
                     <input type="submit" name="add" value="ADD"  style="margin-left:377px">
                     <input type="reset" value="CANCEL" style="margin-left:20px">
                 </form>
                
            </div>
        </div>
     </body>
</html>
