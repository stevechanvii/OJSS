<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Recruiter Home</title>
        <!--<meta http-equiv="X-UA-Compatible" content="IE=edge" />-->
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <meta name="apple-mobile-web-app-capable" content="yes" />
        <link href="css/recruiter_createCategory.css" rel="stylesheet" />    
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

                    <!--link to applied job-->
                    <!--                    <li id ="link_createJobCate" class="link_text">
                                            <a id="createJobCate" href="#">Create a Category</a>
                                        </li>-->

                    <!--link to show invitations --> 
                    <li id ="link_createJob" class="link_text">
                        <a id="createJob" href="recruiter_createJob">Create A Job</a>
                    </li>
                    
                    <!--link to show invitations --> 
                <li id ="link_createJobCate" class="link_text">
                    <a id="createJobCate" href="createCategory?${recruiter.id}">Create a Category</a>
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
                <p id="myCreatedJobList" style="color: grey"><b>Create a New Job Category</b></p>

                <!-- One Seeker Tab --> 
                <div id="oneSeekerResult"  class="jobs">
                    <ul id="oneJob" class="jobList">

                        <!--Input job category here-->
                        <li class="content_li" >
                            <p id="jobTitle" class="jobTitleText">
                                <span>Job Category Name</span>
                            </p>
                        </li>   

                        <!--job category email here-->
                        <form id="updateCategory" action="createNewCategory" method="post" class="search_form">
                            <ul>
                                <li id="jobLocation" class="content_li" class="jobDetails">
                                    <input type="text" id="inputCategory" name="job_category"/>
                                </li>
                                <li>
                                    <button type="submit" class="btn_small">Save</button>
                                </li>
                            </ul>    
                        </form>


                        <c:if test="${!empty requestScope.invalidCategoryUpdate}">
                            <p id="error_cat" class="errors">
                                Invalid input!
                            </p>
                        </c:if>

                        <c:forEach items="${jobCategory}" var="categories" varStatus="iter">
                            <span>${categories.categoryName}</span>

                        </c:forEach>


                    </ul>
                </div>
                <br>
            </div>
        </div>


    </body>

</html>