<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Registration</title>
        <!--<meta http-equiv="X-UA-Compatible" content="IE=edge" />-->
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <meta name="apple-mobile-web-app-capable" content="yes" />
        <link href="css/register.css" rel="stylesheet" />

    </head> 
    <body>
        <!-- navi bar -->
        <div id="navi_bar" class="header">
            <a href="login" id="link_login" class = "nav_link" >Log in</a>
            <!--<a href="#" id="link_signup" class = "nav_link">Sign up</a>-->
        </div>


        <!-- Register header here --> 
        <div id="regisTitle">
            <div id="registerLogo" >
                <img src="image/register.png" id="pic_register">    
            </div>  
        </div>

        <!-- Register form here-->
        <form id="register_form" action="register" method="post">
            <ul>
                <li id="lable_email" class="label_text" >
                    Email Address
                </li>
                <li  class="user_input">
                    <input type="text" id="email" class="user_input" name="reg_email"/>    
                </li>
                <br>
                <li id="lable_pwd" class="label_text" >
                    Password
                </li>
                <li  class="user_input">
                    <input type="text" id="userPwd" class="user_input" name="reg_pwd"/>    
                </li>
                <br>
                <li style="float:left;width:140pt">
                    <input type="radio" name="register_type" value="seeker"checked="true"/>
                    <label id="regisSeeker" class="r_labels">Registe a Job Seeker</label>
                </li>

                <li style="float:right;width:140pt;">
                    <input type="radio" name="register_type" value="recruiter"/>
                    <label id="regisSeeker" class="r_labels">Registe a Recruiter</label>
                </li>
                <!--error msg --> 
                <c:if test="${!empty requestScope.invalidAccount}">
                        <p id="error_reg" class="errors">
                            Email exist or input is wrong!
                        </p>
                    </c:if>

                <!--login button-->
                <button  class="btn" type="submit" id="btn_regist" name="Sign Up" >Sign Up</button>
            </ul>
        </form>

    </body>
</html>
