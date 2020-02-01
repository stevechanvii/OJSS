<%-- 
    Document   : matchSeeker
    Created on : 21/05/2018, 9:39:58 PM
    Author     : 41649
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Recruiter Home</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <meta name="apple-mobile-web-app-capable" content="yes" />
        <link href="css/recruiter_matchingList.css" rel="stylesheet" />    
    </head> 
    <body>
        <div class="header" id="header">

            <!-- navi bar -->
            <div id="navi_bar" class="header">
                <a href="#" id="link_myAccount" class = "nav_link" >My account</a>
                <a href="#" id="link_logout" class = "nav_link">Log out </a>
            </div>

            <!-- logo + search bar-->
            <div id="search_bar" class="search_bar">
                <div id = "logo_img">
                    <img id="logo"  src="image/ojss_logo.png"
                         height = "150px" width = "300px">
                </div>

                <!-- search seeker bar -->

                <form id="searchSeeker" action="searchSeeker" method="post" class="search_form">

                    <label id="search_labels">Who</label>
                    <input type="text" id="input_what" class="seeker_input" name="keywords" placeholder="Enter skill keywords to searh candidates"/>


                    <!-- btn matching-->
                    <button type="submit" id="btn_findSeeker" name="findSeeker" class="btn">Matching</button>
                    <br>
                    <br>
                    <br>

                </form>
            </div>
        </div>


        <!-- side bar (left)  show user's menu--> 
        <div id="side_bar" >

            <!--recruiter functions--> 
            <div id="r_functions">
                <ul>
                    <li id="myFunctions" class="menuTitle">
                        <a id="f_title">Functions</a>
                    </li>

                    <!--link to applied job-->
                    <li id ="link_createJobCate" class="link_text">
                        <a id="createJobCate" href="createCategory?${recruiter.id}">Create a Category</a>
                    </li>

                    <!--link to show invitations --> 
                    <li id ="link_createJob" class="link_text">
                        <a id="createJob" href="recruiter_createJob">Create A Job</a>
                    </li>
                </ul>  
            </div>
            <br>

            <!--my account-->
            <ul id="recruiter_menu">

                <li id="myAccount" class="menuTitle">
                    <a id="myTitle">My Account</a>
                </li>

                <!--link to applied job-->
                <li id ="link_jobCategories" class="link_text">
                    <a id="jobCategories" href="#">My Job Categories</a>
                </li>

                <!--link to show invitations --> 
                <li id ="link_createdJobList" class="link_text">
                    <a id="createdJobList" href="recruiterHome">My Created Job List</a>
                </li>

                <!--link to build my resume --> 
                <li id ="link_myProfile" class="link_text">
                    <a id="myProfile" href="manageRecruiterProfile?${seeker.id}">Manage My Profile</a>
                </li>

            </ul>
        </div>

        <!--searching result content--> 
        <div id="content_bg" >
            <div id = "search_jobResult">
                <!--Title--> 
                <p id="myCreatedJobList"><b>Matching Result</b></p>
                 <c:if test = "${invResult == false}">
                    <p id="error_pwd" class="errors">
                        You have invited this guys!
                    </p>
                </c:if>
                <c:if test = "${invResult == true}">
                    <p id="error_pwd" class="errors">
                        Your invitation has been send!
                    </p>
                </c:if>
                <c:forEach var="applicant" items="${matchRank}"  varStatus="iter">
                    <!-- One Seeker Tab --> 
                    <div id="oneSeekerResult"  class="jobs">
                        <ul id="oneJob" class="jobList">

                            <!--Seeker name here-->
                            <li class="content_li" >
                                <p id="jobTitle" class="jobTitleText">
                                    <span>${applicant.seeker.firstName}.<b>${applicant.seeker.lastName}</b></span>
                                </p>
                            </li>    

                            <!--Seeker email here-->
                            <li id="jobLocation" class="content_li" class="jobDetails">
                                <span  class="jobDetails">${applicant.seeker.email}</span>
                            </li>
                            <!--Matching score -->
                            <li id="jobPosition" class="jobDetails">
                                <span>Matching score: </span>
                            </li>

                            <!--Matching score -->
                            <li id="jobPosition" class="jobDetails" style="font-size: 20pt;padding: 0 pt; font-family:'Arial-BoldMT', 'Arial Bold', 'Arial'; color: rgba(66, 202, 198, 1); align-self: flex-end">
                                <span>${applicant.score}</span>
                            </li>

                            <!--job seeker phone -->
                            <li id="links" class="jobDetails" style="width:650px">
                                <p><span>${applicant.seeker.phone}</span></p>
                            </li>

                            <!-- action to apply job / see applicants/ match more applicatns-->
                            <li id="seeDetails" >
                                <a href="seeCadiDetails?${applicant.seeker.id}" class="link_test"  id="seeJobDetails">See Seeker Profile / CV </a>
                                <form action="<c:url value='sendInvToCa'/>" method="post">
                                    <input type="hidden" name="seekerId" value = "${applicant.seeker.id}">
                                    <button class="btn_small" name="recruiterId" value = "${recruiter.id}">Send Invitation </button>
                                </form>   
                            </li>
                        </ul>
                    </div>
                </c:forEach>
                <br>
            </div>
        </div>


        <c:if test="${!empty requestScope.selectedCadidate}">
            <div id="sideBarRight">
                <ul id="seekerDetails">

                    <!--Show job title --> 
                    <li id="thisJobTitle">
                        <p>${selectedCadidate.firstName}.${selectedCadidate.lastName}</p>
                    </li>
                    <br>
                    <li id="title">
                        <span>Seeker Profile</span>
                    </li>

                    <!--Show job category-->
                    <li class="jobDetails_label">
                        <p>Date of Birth:</p>
                    </li>

                    <li class="jobDetails_label" style="margin-left: 20px">
                        <p>${selectedCadidate.birthday}</p>
                    </li>
                    <br>

                    <!--show email-->
                    <li class="jobDetails_label">
                        <p>Email: </p>
                    </li>

                    <li class="jobDetails_label" style="margin-left: 20px">
                        <p>${selectedCadidate.email}</p>
                    </li>
                    <br>

                    <!--Show phone numer-->
                    <li class="jobDetails_label">
                        <p>Phone Number: </p>
                    </li>

                    <li class="jobDetails_label" style="margin-left: 20px">
                        <p>${selectedCadidate.phone}</p>
                    </li>
                    <br>

                    <!--Show address-->
                    <li class="jobDetails_label">
                        <p>Address </p>
                    </li>

                    <li class="jobDetails_label" style="margin-left: 20px; margin-bottom: 30pt">
                        <p>${selectedCadidate.address}</p>
                    </li>
                    <!--seeker cv-->
                    <li id="title">
                        <span>Seeker CV</span>
                    </li>

                    <!--Show seeker's skillset-->
                    <li class="jobDetails_label">
                        <p>Skill Sets</p>
                    </li>

                    <li class="jobDetails_label" style="margin-left: 20px">
                        <p>${selectedCadidate.skillset}</p>
                    </li>
                    <br>

                    <!--Show job location-->
                    <li class="jobDetails_label">
                        <p>Education Level </p>
                    </li>

                    <li class="jobDetails_label" style="margin-left: 20px">
                        <p>${selectedCadidate.education}</p>
                    </li>
                    <br>

                    <!--Show seekeer description-->
                    <li class="jobDetails_label">
                        <p>Self Description</p>
                    </li>
                    <br>

                    <li class="jobDetails_label" style="position: relative; top:5px; width:400px">
                        <p>${selectedCadidate.description}</p>
                    </li>
                    <br>
                </ul>
            </div>
        </c:if>


    </body>

</html>
