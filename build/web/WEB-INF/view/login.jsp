<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Log in</title>
        <!--<meta http-equiv="X-UA-Compatible" content="IE=edge" />-->
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <meta name="apple-mobile-web-app-capable" content="yes" />
        <link href="css/Login.css" rel="stylesheet" />


    </head> 
    <body>
        <div class="header" id="haeder">

            <!-- navi bar -->
            <div id="navi_bar" class="header">
                
                <a href="registerPage" id="link_signup" class = "nav_link">Sign up</a>
            </div>

            <!-- Logo login header here --> 
            <div id="logoTitle">
                <div id="logoLogin" >
                    <img src="image/login.png" id="pic_login">    
                </div>  
            </div>


            <!-- user name and pwd entry   searchJob   -->
            <form id="entry_form" action="login" method="post">
                <ul>
                    <li id="lable_userName" class="label_text" >
                        User Name
                    </li>
                    <li  class="user_input">
                        <input type="text" id="userName" class="user_input" name="userName" />    
                    </li>
                    <br>


                    <li id="lable_pwd" class="label_text" >
                        Password
                    </li>
                    <li  class="user_input">
                        <input type="text" id="userPwd" class="user_input" name="password"/>    
                    </li>
                    <br>
                    <!--error msg --> 
                    <c:if test="${!empty sessionScope.invalidAccount}">
                        <p id="error_pwd" class="errors">
                            Username or Password is wrong
                        </p>
                    </c:if>
                    <!--login button-->
                    <button  class="btn" type="submit" id="btn_login" name="login" >Log In</button>
                </ul>
            </form>























        </div>














    </body>
</html>
