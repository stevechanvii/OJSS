<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Create a job</title>
        <!--<meta http-equiv="X-UA-Compatible" content="IE=edge" />-->
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <meta name="apple-mobile-web-app-capable" content="yes" />
        <link href="css/recruiter_createJob.css" rel="stylesheet" />    
    </head> 
    <body>
        <div class="header" id="header">

            <!-- navi bar -->
            <div id="navi_bar" class="header">
                <c:if test="${!empty sessionScope.recruiter}">
                    <a href="#" id="link_myAccount" class = "nav_link">${recruiter.getCompanyName()}</a>
                    <a href="logout" id="link_logout" class="nav_link">Log out</a>
                </c:if>
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

                    <li id ="link_createJob" class="link_text">
                        <a id="createJob" href="recruiter_createJob">Create A Job</a>
                    </li>

                    <!--link to show invitations --> 
                    <li id ="link_createJobCate" class="link_text">
                        <a id="createJobCate" href="createCategory?${recruiter.id}">Create a Category</a>
                    </li>

                    <!--link to show invitations --> 
                    <!--                    <li id ="link_createJob" class="link_text">
                                            <a id="createJob" href="#">Create A Job</a>
                                        </li>-->
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
                <p id="myCreatedJobList" style="color: grey"><b>Create a New Job</b></p>

                <!-- One create job  -->   
                <div id="oneSeekerResult"  class="jobs">
                    <ul id="oneJob" class="jobList">

                        <!--create job  form start -->
                        <form  id="createJobs" action="createjob" method="post">
                            <ul>

                                <!--Input job name here-->
                                <li class="content_li" >
                                    <p class="jobDetails_label">
                                        <span>Job Name</span>
                                    </p>
                                </li>    

                                <li id="jobLocation" class="link_text">
                                    <input type="text" class="inputs" name="jobName"/>     
                                </li>

                                <!--Input job location here-->
                                <li class="content_li" >
                                    <p class="jobDetails_label">
                                        <span>Job Location</span>
                                    </p>
                                </li>  

                                <li id="jobLocation" class="link_text">
                                    <input type="text" class="inputs" name="jobLocation"/>     
                                </li>

                                <!--Input job position here-->

                                <li class="content_li" >
                                    <p class="jobDetails_label">
                                        <span>Job Position</span>
                                    </p>
                                </li>  

                                <li id="jobLocation" class="link_text">
                                    <input type="text" class="inputs" name="jobPosition"/>     
                                </li>


                                <!--Input job location here-->

                                <li class="content_li" >
                                    <p class="jobDetails_label">
                                        <span>Salary Estimate</span>
                                    </p>
                                </li>  

                                <li id="jobLocation" class="link_text">
                                    <input type="text" class="inputs" name="salary"/>     
                                </li>


                                <!--Input job location here-->
                                <li class="content_li" >
                                    <p class="jobDetails_label">
                                        <span>Job Relevant Keywords</span>
                                    </p>
                                </li>  

                                <li id="jobLocation" class="link_text">
                                    <input type="text" class="inputs" name="keywords"/>     
                                </li>

                                <!--select job type here-->
                                <li class="content_li" >
                                    <p class="jobDetails_label">
                                        <span>Job Type</span>
                                    </p>
                                </li>  
                                <li class="content_li">
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


                                <!--select Compensation level here-->
                                <li class="content_li" >
                                    <p class="jobDetails_label">
                                        <span>Job Compensation Level</span>
                                    </p>
                                </li>  
                                <li class="content_li">
                                    <select id="jobCompens" class = "selections" name="compensation">
                                        <option value="" name = "jobCompen" selected="selected">Please select</option>
                                        <option value="Low" name = "jobCompen" >Low</option>
                                        <option value="Middle" name = "jobCompen">Middle</option>
                                        <option value="High" name = "jobCompen">High</option>
                                        <option value="Very High" name = "jobCompen">Very High</option>
                                    </select> 
                                </li>


                                <!--select job category here-->

                                <li class="content_li" >
                                    <p class="jobDetails_label">
                                        <span>Job Category</span>
                                    </p>
                                </li>  

                                <li class="content_li">
                                    <select id="jobType" class = "selections" name="jobCategory">

                                        <option value="" name = "jobCategory" selected="selected">Please select</option>
                                        <c:forEach  var="category" items = "${JobCategory}" varStatus = "iter">
                                            <option value="${category.categoryId}" name = "jobCategory" name="jobCategory">${category.categoryName}</option>
                                        </c:forEach>

                                    </select> 
                                </li>

                                <!--select job description here-->

                                <li class="content_li" >
                                    <p class="jobDetails_label">
                                        <span>Job Description</span>
                                    </p>
                                </li>  
                                <br>

                                <!--Button save here -->
                                <li  class="content_li" >
                                    <textarea id="jobdesc" rows="20" cols="50" id="lable_selfDesc" class="user_input" name="description"></textarea>
                                </li>

                                <li class="button_li">
                                    <c:if test="${!empty sessionScope.createJobError}">
                                        <p id="error_createJob" class="errors">
                                            Update failed!
                                        </p>
                                    </c:if>
                                    <!--<button class="btn" id="saveJob" name="edit">Edit</button>-->
                                    <button class="btn" id="editJob" name="save">Save</button>
                                </li>

                            </ul>    
                        </form> 
                    </ul>
                </div>

            </div>
        </div>

    </body>

</html>