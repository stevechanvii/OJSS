<%-- 
    Document   : search
    Created on : 18/05/2018, 5:37:25 PM
    Author     : 41649
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Search Job</title>
        <!--<meta http-equiv="X-UA-Compatible" content="IE=edge" />-->
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <meta name="apple-mobile-web-app-capable" content="yes" />
        <link href="css/searchJob.css" rel="stylesheet" />    
    </head> 
    <body>

        <div class="header" id="haeder">
            <!-- navi bar -->

            <div id="navi_bar" class="header">
                <c:if test="${empty sessionScope.seeker && empty sessionScope.recruiter && empty sessionScope.admin}">
                    <a href="loginPage" id="link_login" class = "nav_link" >Log in</a>
                    <a href="registerPage" id="link_signup" class = "nav_link">Sign up</a>
                </c:if>
                <c:if test="${!empty sessionScope.seeker}">
                    <a href="#" id="link_myAccount" class = "nav_link">${seeker.firstName}</a>
                    <a href="logout" id="link_logout" class="nav_link">Log out</a>
                </c:if>
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
                    <input type="text" id="input_what" class="seeker_input" name="keywords" placeholder="jobs,skills..." value = "${keywords}"/>

                    <label id = "label_where"  class="search_labels"> Where</label>
                    <input type="txt" id="input_where" class="seeker_input" name="location" placeholder="location" value = "${location}"/>

                    <!-- btn find jobs-->
                    <button type="submit" id="btn_findJobs" name="findJob" class="btn">Find Jobs</button>
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
        <c:if test="${!empty sessionScope.seeker}">
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
        </c:if>

        <!--searching result content--> 
        <div id="cotent_bg" >
            <div id = "search_jobResult">
                <c:forEach var="job" items="${jobs}"  varStatus="iter">
                    <!-- One job Tab --> 
                    <div id="oneJobResult"  class="jobs">
                        <ul id="oneJob" class="jobList">

                            <!--Job title here-->
                            <li id = "${job.jobId}">
                                <p id="jobTitle" class="jobTitleText">
                                    <span>${job.jobName}</span>
                                </p>
                            </li>


                            <!--Job location here-->
                            <li id="jobLocation" class="jobDetails">
                                <span>${job.address}</span>
                            </li>

                            <!--Job posirtiio here-->
                            
                            <li id="jobPosition" class="jobDetails">
                                <span>Position:</span>
                                <span>${job.position}</span>
                            </li>

                            <!--Job description here-->

                            <li id="jobDesc" class="jobDetails" style="width:650px">
                                <p><span>Description:</span></p>
                                <p>${job.description}</p>
                            </li>



                            <!-- action to apply job -->

                            <li id="seeDetails" class="jobDetails" >
                                <a href="<c:url value='seeDetails?${job.jobId}'/>" id="seeJobDetails">See Job Details</a>
                            </li>
                        </ul>
                    </div>
                    <br>


                </c:forEach>
            </div>
        </div>


        <!--side bar right show job details--> 
        <c:if test="${!empty sessionScope.selectedJob}">
            <div id="sideBarRight">
                <ul id="showJobDetails">

                    <!--Show job title --> 
                    <li id="thisJobTitle">
                        <p>${selectedJob.jobName}</p>
                    </li>

                    <!--Show recruiter name-->
                    <li id="recruiterName" class="jobDetails" style="padding-left :20px">
                        <p>${selectedJob.recruiter.companyName}</p>
                    </li>

                    <!--button apply-->

                    <c:if test="${!empty sessionScope.seeker}">
                        <li id="btn_apply" style="padding-left: 20px">

                            <form action="<c:url value='applyJob'/>" method="post">
                                <input type="hidden" name="seekerId" value = "${seeker.id}">
                                <button class="btn" id="btn_applyJob" style="padding: 0px" name = "selectedJobId" value = "${selectedJob.jobId}">Apply Job</button>
                            </form>

                        </li>

                    </c:if>
                    <br>
                    <br>
                    <c:if test = "${applyResult == false}">
                        <p id="error_pwd" class="errors">
                            You have applied this job
                        </p>
                    </c:if>
                    <c:if test = "${applyResult == true}">
                        <p id="error_pwd" class="errors">
                            Your application has been submitted
                        </p>
                    </c:if>
                    <!--Show job category-->
                    <li class="jobDetails_label">
                        <p>Job Category : </p>
                    </li>

                    <li class="jobDetails_label" style="margin-left: 20px; width:200px">
                        <p>Job.Category</p>
                    </li>
                    <br>

                    <!--Show job salary-->
                    <li class="jobDetails_label">
                        <p>Job Salary :</p>
                    </li>

                    <li class="jobDetails_label" style="margin-left: 20px; width:200px">
                        <p>${selectedJob.salary}</p>
                    </li>
                    <br>

                    <!--Show job position-->
                    <li class="jobDetails_label">
                        <p>Job Position :</p>
                    </li>

                    <li class="jobDetails_label" style="margin-left: 20px; width:200px" >
                        <p>${selectedJob.position}</p>
                    </li>
                    <br>

                    <!--Show job type-->
                    <li class="jobDetails_label">
                        <p>Job Type :</p>
                    </li>

                    <li class="jobDetails_label" style="margin-left: 20px; width:200px">
                        <p>${selectedJob.jobType}</p>
                    </li>
                    <br>

                    <!--Show job position-->
                    <li class="jobDetails_label" >
                        <p>Compensation Level:</p>
                    </li>

                    <li class="jobDetails_label" style="margin-left: 20px; width:200px">
                        <p>${selectedJob.compensation}</p>
                    </li>
                    <br>

                    <!--Show job location-->
                    <li class="jobDetails_label">
                        <p>Job Location:</p>
                    </li>

                    <li class="jobDetails_label" style="margin-left: 20px; width:200px">
                        <p>${selectedJob.address}</p>
                    </li>
                    <br>

                    <!--Show job Rele keywords-->
                    <li class="jobDetails_label">
                        <p>Keywords: </p>
                    </li>
                    <br>

                    <li class="jobDetails_label" style="position: relative; top:5px; width:400px">
                        <p>${selectedJob.relKeywords}</p>
                    </li>
                    <br>

                    <!--Show job description-->
                    <li class="jobDetails_label"  style="position: relative; top:5px; width:400px">
                        <p>Job Description:</p>
                    </li>
                    <br>

                    <li class="jobDetails_label" style="position: relative; top:10px; width:400px">
                        <p>${selectedJob.description}</p>
                    </li>
                    <br>

                </ul>
            </div>
        </c:if>

    </body>

</html>
