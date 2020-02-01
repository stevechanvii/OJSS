<%-- 
    Document   : myAppliedJob
    Created on : 20/05/2018, 6:46:23 PM
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
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <meta name="apple-mobile-web-app-capable" content="yes" />
        <link href="css/appliedJob.css" rel="stylesheet" />    
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
                    <button class="btn" type="submit" id="btn_findJobs" name="findJob" >Find Jobs</button>
                    <br>
                    <br>
                    <br>

                    <ol id="selection_list">
                        <li class="selection_list">

                            <!-- types of job -->
                            <label id = "label_typeJob"  class="search_label2"> Types of job  </label> 

                            <select id="typeOfJob" class = "selections" name = "jobType">
                                <option value="Please select" name = "jobType" selected="selected">Please select</option>
                                <option value="Full-time" name = "jobType" >Full-time</option>
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

                            <select id="jobSalaries" class = "selections" name = "jobSalary">
                                <option value="" name = "jobSalary" selected="selected">Please select</option>
                                <option value="$5000" name = "jobSalary">above $5000</option>
                                <option value="$7000" name = "jobSalary">above $7000</option>
                                <option value="$9000" name = "jobSalary">above $9000</option>
                                <option value="$11000" name = "jobSalary">above $11000</option>
                                <option value="$13000" name = "jobSalary">above $13000</option>
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

        <!--searching result content--> 

        <div id="cotent_bg" >
            <div id = "search_jobResult">
                <c:forEach var="application" items="${applications}"  varStatus="iter">
                    <!-- One job Tab --> 
                    <div id="oneJobResult"  class="jobs">
                        <ul id="oneJob" class="jobList">

                            <!--Job title here-->
                            <li>
                                <p id="jobTitle" class="jobTitleText">
                                    <span>${application.job.jobName}</span>
                                    <span id="appliedDate">Applied Date:${application.date}</span>

                                </p>
                            </li>


                            <!--Job location here-->
                            <li id="jobLocation" class="jobDetails">
                                <span>${application.job.address}</span>
                            </li>

                            <!--Job posirtiio here-->
                            <br>
                            <li id="jobPosition" class="jobDetails">
                                <span><b>Job Position : </b></span>
                                <span>${application.job.position}</span>
                            </li>

                            <!--Job salary here-->

                            <li id="jobSalary" class="jobDetails">
                                <span><b>Salary : </b></span>
                                <span>${application.job.salary}</span>
                            </li>


                            <!--Job description here-->
                            <li id="jobkey" class="jobDetails" style="width:650px">
                                <span><b>Relevant Keywords : </b></span>
                                <span>${application.job.relKeywords}</span>
                            </li>


                            <!-- action to apply job -->
                            <li id="seeDetails" class="jobDetails" >
                                <a href="<c:url value='seeAppDetails?${application.job.jobId}'/>" id="seeJobDetails">See Job Details</a>
                            </li>
                        </ul>
                    </div>
                    <br>
                </c:forEach>
            </div>
        </div>



        <!--side bar right show job details--> 
        <c:if test="${!empty requestScope.selectedAppliedJob}">
            <div id="sideBarRight">
                <ul id="showJobDetails">

                    <!--Show job title --> 
                    <li id="thisJobTitle">
                        <p><b>${selectedAppliedJob.jobName}</b></p>
                    </li>

                    <!--Show recruiter name-->
                    <li id="recruiterName" class="jobDetails" style="padding-left :20px">
                        <p>${selectedAppliedJob.recruiter.companyName}</p>
                    </li>

                    <!--button apply-->

                    <br>

                    <!--Show job category-->
                    <li class="jobDetails_label">
                        <p><b>Job Category : </b></p> 
                    </li>

                    <li class="jobDetails_label" style="margin-left: 20px">
                        <p>Job.Category</p>
                    </li>
                    <br>

                    <!--Show job salary-->
                    <li class="jobDetails_label">
                        <p><b>Job Salary : </b> ${selectedAppliedJob.salary}</p>
                    </li>


                    <br>

                    <!--Show job position-->
                    <li class="jobDetails_label">
                        <p><b>Job Position :  </b> ${selectedAppliedJob.position}</p>
                    </li>


                    <br>

                    <!--Show job type-->
                    <li class="jobDetails_label">
                        <p><b>Job Type : </b>  ${selectedAppliedJob.jobType}</p>
                    </li>


                    <br>

                    <!--Show job position-->
                    <li class="jobDetails_label">
                        <p><b>Job Compensation Level: </b>  ${selectedAppliedJob.compensation}</p>
                    </li>


                    <br>

                    <!--Show job location-->
                    <li class="jobDetails_label">
                        <p><b>Job Location :</b> ${selectedAppliedJob.address}</p>
                    </li>


                    <br>

                    <!--Show job Rele keywords-->
                    <li class="jobDetails_label">
                        <p><b>Job Relevent Keywords: </b></p>
                    </li>
                    <br>

                    <li class="jobDetails_label" style="position: relative; top:5px; width:400px">
                        <p>${selectedAppliedJob.relKeywords}</p>
                    </li>
                    <br>

                    <!--Show job description-->
                    <li class="jobDetails_label"  style="position: relative; top:5px; width:400px">
                        <p><b>Job Description:  </b></p>
                    </li>
                    <br>

                    <li class="jobDetails_label" style="position: relative; top:10px; width:400px">
                        <p>${selectedAppliedJob.description}</p>
                    </li>
                    <br>

                </ul>
            </div>
        </c:if>

    </body>

</html>