<%--
    Document   : index
    Created on : Jun 9, 2010, 3:59:32 PM
    Author     : tgiunipero
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Welcome</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <link href="css/main_page.css" rel="stylesheet" />
      
</head> 
<body>
    <div class="header" id="haeder">
        <!-- navi bar -->
        <div id="navi_bar" class="header">
            <a href="loginPage" id="link_login" class = "nav_link" >Log in</a>
            <a href="registerPage" id="link_signup" class = "nav_link">Sign up</a>
        </div>
        
        <!-- Logo login header here --> 
        <div id="logoTitle">
            <div id="logo" >
                <img src="image/ojss_logo.png" id="pic_logo">    
            </div>  
        </div>
    </div>
        
        <!--Search bar -->
    <form id="searchJob" action="searchJob" method="post" class="search_form">
                
                <label id="label_what" class="search_labels" >What</label>
                    <input type="text" id="input_what" class="seeker_input" name="keywords" placeholder="jobs,skills..."/>
                
                <label id = "label_where"  class="search_labels"> Where</label>
                    <input type="txt" id="input_where" class="seeker_input" name="location" placeholder="location"/>
                           
                <!-- btn find jobs-->
                <button type="submit" id="btn_findJobs" name="findJob" class="btn" >Find Jobs</button>
    </form>
        
    
    
        
    

    </body>
</html>


