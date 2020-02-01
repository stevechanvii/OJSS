<%-- 
    Document   : myInvitation
    Created on : 20/05/2018, 10:11:43 PM
    Author     : 41649
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Invitation List</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <meta name="apple-mobile-web-app-capable" content="yes" />
        <link href="css/seeker_invitation.css" rel="stylesheet" />    
    </head> 
    <body>
        <div class="header" id="header">

            <!-- navi bar -->
            <div id="navi_bar" class="header">
                <a href="#" id="link_myAccount" class = "nav_link">${seeker.firstName}</a>
                <a href="logout" id="link_logout" class="nav_link">Log out</a>
            </div>

            <!-- logo + search bar-->
            <div id="search_bar" class="search_bar">
                <div id = "logo_img">
                    <img id="logo"  src="image/ojss_logo.png"
                         height = "150px" width = "300px">
                </div>

                <!-- search job bar -->
                <form id="searchJob" action="searchJob" method="post" class="search_form">
                    <label id="label_what" class="search_labels" >What</label>
                    <input type="text" id="input_what" class="seeker_input" name="keywords" placeholder="jobs,skills..."/>

                    <label id = "label_where"  class="search_labels"> Where</label>
                    <input type="txt" id="input_where" class="seeker_input" name="location" placeholder="location"/>

                    <!-- btn find jobs-->
                    <button type="submit" id="btn_findJobs" name="findJob" >Find Jobs</button>
                    <br>
                    <br>
                    <br>

                    <ol id="selection_list">
                        <li class="selection_list">

                            <!-- types of job -->
                            <label id = "label_typeJob"  class="search_label2"> Types of job  </label> 

                            <select id="typeOfJob" class = "selections" name = "jobType">
                                <option value="" name = "jobType" selected="selected">Please select</option>
                                <option value="Full time" name = "jobType" >Full-time</option>
                                <option value="Part time" name = "jobType" >Part-time</option>
                                <option value="Contract" name = "jobType" >Contract</option>
                                <option value="Permanent" name = "jobType" >Permanent</option>
                                <option value="Temporary" name = "jobType" >Temporary</option>
                                <option value="Internship" name = "jobType">Internship</option>
                            </select> 
                        </li>


                        <!-- Salary Estimate -->
                        <li class="selection_list">  
                            <label id = "label_salary"  class="search_label2">Salary Estimate </label> 

                            <select id="jobSalaries" class = "selections" name="jobSalary">
                                <option value="" name = "jobSalary" selected="selected">Please select</option>
                                <option value="5000" name = "jobSalary">above $5000</option>
                                <option value="7000" name = "jobSalary">above $7000</option>
                                <option value="9000" name = "jobSalary">above $9000</option>
                                <option value="11000" name = "jobSalary">above $11000</option>
                                <option value="13000" name = "jobSalary">above $13000</option>
                            </select> 
                        </li>

                        <!-- Compensation Level -->

                        <li class="selection_list">  
                            <label id = "label_compensation"  class="search_label2"> Compensation Level </label> 

                            <select id="jobCompens" class = "selections" name = "jobCompen">
                                <option value="" name = "jobCompen" selected="selected">Please select</option>
                                <option value="Low" name = "jobCompen" >Low</option>
                                <option value="Middle" name = "jobCompen">Middle</option>
                                <option value="High" name = "jobCompen">High</option>
                                <option value="Very High" name = "jobCompen">Very High</option>
                            </select> 
                        </li>
                    </ol>        
                </form>
            </div>
        </div>


        <!-- side bar (left)  show user's menu--> 

        <div id="side_bar" >

            <ul id="seeker_menu">

                <li id="myAccount">
                    <a id="myTitle">My Account</a>
                </li>

                <!--link to applied job-->
                <li id ="link_appliedJob" class="link_text">
                    <a id="appliedJob" href="<c:url value='myAppliedJob?${seeker.id}'/>">My Applied Jobs</a>
                </li>

                <!--link to show invitations --> 
                <li id ="link_showInvita" class="link_text">
                    <a id="showInvita" href="<c:url value='myInvitation?${seeker.id}'/>">Show My Invitations</a>
                </li>

                <!--link to manage my profile --> 
                <li id ="link_profile" class="link_text">
                    <a id="profile" href="seeker_buildCV">Manage My Profile</a>
                </li>

            </ul>
        </div>

        <!--Invitation list content--> 
        <div id="cotent_bg" >
            <div id = "invitationList">
                <c:forEach var="invitation" items="${invitations}"  varStatus="iter">
                    <!-- One invitation Tab --> 
                    <div id="oneInvi"  class="invitations">
                        <ul id="oneInvitation" class="inviList">

                            <!--Invitation title here-->
                            <li >
                                <p id="inviTable" class="jobTitleText">
                                    <span>To ${invitation.seeker.firstName}.${invitation.seeker.lastName}</span>
                                </p>
                            </li>

                            <!--Invi.date here-->
                            <li id="inviDate" class="inviDetails">
                                <span>Accept Date: </span>  &nbsp;&nbsp; 
                                <span>${invitation.createDate} </span>  

                            </li>

                            <!--Invitation description here-->
                            <!--                        <li id="inviDesc" class="inviDetails" style="width:650px">
                                                        <p><span>Invitation.description</span></p>
                                                    </li>-->


                            <!-- action to apply job -->

                            <li id="seeDetails" class="inviDetails" >
                                <a href="<c:url value='seeInvDetails?${invitation.recruiter.id}?${invitation.invId}'/>" id="seeJobDetails">See Details</a>
                            </li>
                        </ul>
                    </div>
                </c:forEach>
                <br>
            </div>
        </div>


        <!--side bar right show job details--> 
        <c:if test="${!empty requestScope.selectedInv}">
            <div id="sideBarRight">
                <ul id="showJobDetails">

                    <!--Show job title --> 
                    <li id="thisJobTitle">
                        <p><b>Invitation</b> </p>
                    </li>
                    <br>
                    <!--Show recruiter name-->
                    <li id="recruiterName" class="inviDetails" style="padding-left :20px">
                        <p>${selectedRecruiter.companyName}</p>
                    </li>

                    <!--Show invitation details -->
                    <li class="inviDetails_label">
                        <p>${selectedInv.createDate} </p>
                    </li>
                    <li class="inviDetails_label" style="margin-left: 10px ; padding-left: 10pt">
                        <p>${selectedInv.description}</p>
                    </li>

                </ul>
            </div>
        </c:if>

    </body>

</html>
