<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Build CV</title>
        <!--<meta http-equiv="X-UA-Compatible" content="IE=edge" />-->
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <meta name="apple-mobile-web-app-capable" content="yes" />
        <link href="css/seeker_buildCV.css" rel="stylesheet" />
    </head> 
    <body>
        <div class="header" id="haeder">
            <!-- navi bar -->
            <div id="navi_bar" class="header">
                <c:if test="${!empty sessionScope.seeker}">
                    <a href="#" id="link_myAccount" class = "nav_link">${seeker.firstName}</a>
                    <a href="logout" id="link_logout" class="nav_link">Log out</a>
                </c:if>
            </div>

            <!-- Logo build CV header here --> 
            <div id="logoTitle">
                <div id="logoBuildCV" >
                    <img src="image/logo_buildCV.png" id="pic_buildCV">    
                </div>  
            </div>

            <!-- step 1 and 2 titles -->
            <div id="title_side">
                <ul style="width: 1000pt">
                    <li id="createTitle">
                        <p>Step 1 Create your own profile</p>
                    </li>
                    <li style="float: left; width: 70pt"></li>

                    <li id="cvTitle">
                        <p>Step 2 Create your own CV</p>
                    </li>    
                </ul>
            </div>


            <!--User input start -->

            <div id="cv_content">
                <form id="entry_form" action="updateSeekerCV" method="post">

                    <!-- create profile phase--> 
                    <ul id="left_side" style="float: left">
                        <li id="lable_fname" class="label_text" >
                            First Name
                        </li>
                        <li  class="user_input">
                            <input type="text" id="f_name" class="user_input" name="f_name" value ="${seeker.getFirstName()}"/>    
                        </li>

                        <li id="lable_lname" class="label_text" >
                            Last Name
                        </li>
                        <li  class="user_input">
                            <input type="text" id="l_name" class="user_input" name="l_name" value ="${seeker.getLastName()}"/>    
                        </li>
                        <li id="lable_dob" class="label_text" >
                            Date of Birth ${seeker.getBirthday().getYear()} ${seeker.getBirthday().getMonth()} ${seeker.getBirthday().getDay()}
                        </li>
                        <li  class="user_input" >
                            <select name="day" id="day" class="date" ">
                                <script>

                                    for (i = 1; i < 32; i++) {
                                        document.write("<option value='" + i + "'>" + i + "</option>")
                                    }


                                </script>
                            </select>   

                            <select name="month" id="month" class="date">
                                <script>
                                    for (i = 1; i < 13; i++) {
                                        document.write("<option value='" + i + "'>" + i + "</option>")
                                    }
                                </script>
                            </select>   

                            <select name="year" id="year" class="date">
                                <script>

                                    for (i = 1970; i < 2019; i++) {
                                        document.write("<option value='" + i + "'>" + i + "</option>")
                                    }
                                </script>
                            </select>   
                        </li>  
                        <li>
                            <label class="dateLabels">
                                Date
                            </label>
                            <label class="dateLabels">
                                Month
                            </label>
                            <label class="dateLabels">
                                Year
                            </label>
                        </li>
                        <li id="lable_address" class="label_text" >
                            Address
                        </li>
                        <li  class="user_input">
                            <input type="text" id="address" class="user_input" name="address" value ="${seeker.getAddress()}"/>    
                        </li>
                        <li id="lable_phone" class="label_text" >
                            Phone Number
                        </li>
                        <li  class="user_input">
                            <input type="text" id="phone" class="user_input" name="phone" value ="${seeker.getPhone()}"/>    
                        </li>
                    </ul>

                    <!-- create cv phase--> 

                    <ul id="right_side" style="float:left; margin-left:0pt;">
                        <li id="lable_eduLevel" class="label_text" >
                            Education Level
                        </li>
                        <li  class="user_input">
                            <input type="text" id="eduLevel" class="user_input" name="edu_level" value ="${seeker.getEducation()}"/>  
                        </li>

                        <li id="lable_skillSets" class="label_text" >
                            Your skills
                        </li>
                        <li  class="user_input">
                            <input type="text" id="skillSets" class="user_input" name="skillsets" value ="${seeker.getSkillset()}"/>  
                        </li>
                        <li id="lable_selfDesc" class="label_text" >
                            Introduce Yourself
                        </li>
                        <li  class="user_input" style="height:120pt">
                            <textarea id="selfdesc"rows="5" cols="30" id="lable_selfDesc" class="user_input" name="description">${seeker.getDescription()}</textarea>
                        </li>
                        <c:if test="${!empty sessionScope.updateState}">
                            <p id="error_update" class="errors">
                                Update failed!
                            </p>
                        </c:if>
                        <button class="btn" href="#" id="btn_backToHome" name="back">Back</button>
                        <button  class="btn" type="submit" id="btn_login" name="logIn" >Update</button>
                    </ul>



                    <!--buttons-->

                </form>


            </div>
        </div>
    </body>
</html>

























