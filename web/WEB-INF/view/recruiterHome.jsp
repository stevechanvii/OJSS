<%-- 
    Document   : recruiterHome
    Created on : 21/05/2018, 2:40:55 AM
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
        <link href="css/recruiter_home.css" rel="stylesheet" />    
    </head> 
    <body>
        <div class="header" id="header">

            <!-- navi bar -->
            <div id="navi_bar" class="header">
                <a href="#" id="link_myAccount" class = "nav_link" >${recruiter.companyName}</a>
                <a href="logout" id="link_logout" class = "nav_link">Log out </a>
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
                    <button type="submit" id="btn_findSeeker" name="findSeeker" class="btn"> Matching</button>
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
                    <a id="createdJobList" href="#">My Created Job List</a>
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
                <p id="myCreatedJobList">My Created Job List</p>
                <c:forEach var="job" items="${jobs}"  varStatus="iter">
                    <!-- One job Tab --> 
                    <div id="oneJobResult"  class="jobs">
                        <ul id="oneJob" class="jobList">

                            <!--Job title here-->
                            <li class="content_li" >
                                <p id="jobTitle" class="jobTitleText">
                                    <span><b>${job.jobName}</b></span>
                                </p>
                            </li>    

                            <!-- button groups--> 
                            <li class=""content_li style="margin-left: 90pt; width: 320">
                                <form action="<c:url value='modifyJob'/>"  style="float:left" method="post">
                                    <button name="modify"  class="btn_small" value = "${job.jobId}">Modify</button>
                                </form>
                                </form>
                                <form action="<c:url value='deleteJob'/>"  style="float:left" method="post">
                                    <button name="delete" class="btn_small"  value = "${job.jobId}" >Delete</button>
                                </form>
                                <c:if test="${job.isAdvertise==false}">
                                    <form action="<c:url value='advertiseJob'/>"  style="float:left" method="post">
                                        <button name="advertise" class="btn_small" value = "${job.jobId}">Advertise</button>
                                    </form>
                                </c:if>
                                <c:if test="${job.isAdvertise==true}">
                                    <form action="<c:url value='closeJob'/>" style="float:left" method="post">
                                        <button name="close" class="btn_small" value = "${job.jobId}">Close</button>
                                    </form>
                                </c:if>
                            </li>

                            <!--Job location here-->
                            <li id="jobLocation" class="content_li" class="jobDetails">
                                <span  class="jobDetails">${job.address}</span>
                            </li>

                            <!--Job posirtiio here-->

                            <li id="jobPosition" class="jobDetails">
                                <span>${job.position}</span>
                            </li>


                            <!--job relevant keywords-->
                            <li id="links" class="jobDetails" style="width:650px">
                                <p><span>${job.relKeywords}</span></p>
                            </li>


                            <!-- action to apply job / see applicants/ match more applicatns-->

                            <li id="seeDetails" >
                                <a href="createdJobDetail?${job.jobId}" class="link_test"  id="seeJobDetails">See Job Details</a>
                                <a href="seeApplicants?${job.jobId}" class="link_test"  id="seeApplicants">See Applicants</a>
                                <a href="matchByJob?${job.jobId}" class="link_test"  id="matchSeeker">Match more candidates</a> 
                            </li>
                        </ul>
                    </div>
                </c:forEach>
                <br>
            </div>
        </div>


        <!--side bar right show job details--> 
        <c:if test="${!empty requestScope.selectedCreatedJob}">
            <div id="sideBarRight">
                <ul id="showJobDetails">

                    <!--Show job title --> 
                    <li id="thisJobTitle">
                        <p>${selectedCreatedJob.jobName}</p>
                    </li>

                    <!--Show job category-->
                    <li class="jobDetails_label">
                        <p>Category : </p>
                    </li>

                    <li class="jobDetails_label" style="margin-left: 20px">
                        <p>Job.Category</p>
                    </li>
                    <br>

                    <!--Show job salary-->
                    <li class="jobDetails_label">
                        <p>Salary : </p>
                    </li>

                    <li class="jobDetails_label" style="margin-left: 20px">
                        <p>${selectedCreatedJob.salary}</p>
                    </li>
                    <br>

                    <!--Show job position-->
                    <li class="jobDetails_label">
                        <p>Position: </p>
                    </li>

                    <li class="jobDetails_label" style="margin-left: 20px">
                        <p>${selectedCreatedJob.position}</p>
                    </li>
                    <br>

                    <!--Show job type-->
                    <li class="jobDetails_label">
                        <p>Job Type : </p>
                    </li>

                    <li class="jobDetails_label" style="margin-left: 20px">
                        <p>${selectedCreatedJob.jobType}</p>
                    </li>
                    <br>

                    <!--Show job position-->
                    <li class="jobDetails_label">
                        <p>Compensation Level: </p>
                    </li>

                    <li class="jobDetails_label" style="margin-left: 20px">
                        <p>${selectedCreatedJob.compensation}</p>
                    </li>
                    <br>

                    <!--Show job location-->
                    <li class="jobDetails_label">
                        <p>Location </p>
                    </li>

                    <li class="jobDetails_label" style="margin-left: 20px">
                        <p>${selectedCreatedJob.address}</p>
                    </li>
                    <br>

                    <!--Show job Rele keywords-->
                    <li class="jobDetails_label">
                        <p>Relevent Keywords:</p>
                    </li>
                    <br>

                    <li class="jobDetails_label" style="position: relative; top:5px; width:400px">
                        <p>${selectedCreatedJob.relKeywords}</p>
                    </li>
                    <br>

                    <!--Show job description-->
                    <li class="jobDetails_label"  style="position: relative; top:5px; width:400px">
                        <p>Job Description: </p>
                    </li>
                    <br>

                    <li class="jobDetails_label" style="position: relative; top:10px; width:400px">
                        <p>${selectedCreatedJob.description}</p>
                    </li>
                    <br>

                </ul>
            </div>
        </c:if>


    </body>

</html>
