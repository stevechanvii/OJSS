/*
 * To change this license header, choose License Headers in Project Prope  rties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.Search;
import entity.Application;
import entity.Invitation;
import entity.Job;
import entity.JobCategory;
import entity.MatchSeeker;
import entity.Recruiter;
import entity.Seeker;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author 41649
 */
@WebServlet(name = "OJSSControllerServlet",
        loadOnStartup = 1,
        urlPatterns = {"/searchJob",
            "/seeDetail",
            "/loginPage",
            "/login",
            "/logout",
            "/applyJob",
            "/myAppliedJob",
            "/seeAppDetails",
            "/myInvitation",
            "/seeInvDetails",
            "/registerPage",
            "/register",
            "/seeker_buildCV",
            "/updateSeekerCV",
            "/closeJob",
            "/advertiseJob",
            "/deleteJob",
            "/createdJobDetail",
            "/seeApplicants",
            "/seeApplicantDetails",
            "/sendInvitation",
            "/recruiterHome",
            "/manageRecruiterProfile",
            "/createCategory",
            "/createNewCategory",
            "/recruiter_createJob",
            "/createjob",
            "/matchByJob",
            "/seeCadiDetails",
            "/sendInvToCa",
            "/searchSeeker",
            "/modifyJob",
            "/recruiterModifyJob"})
public class OJSSServlet extends HttpServlet {

    Seeker seeker = null;
    Recruiter recruiter = null;

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userPath = request.getServletPath();
        HttpSession session = request.getSession();

        // if category page is requested
        if (userPath.equals("/seeDetails")) {
            Job selectedJob = new Job();
            // get categoryId from request
            String jobId = request.getQueryString();

            if (jobId != null) {
                JobController jobController = new JobController();
                selectedJob = jobController.findJobById(jobId);
                session.setAttribute("selectedJob", selectedJob);
            }

            userPath = "/searchJob";

            // if cart page is requested
        } else if (userPath.equals("/loginPage")) {

            userPath = "/login";

            // if checkout page is requested
        } else if (userPath.equals("/logout")) {

            if (session.getAttribute("seeker") != null) {
                session.removeAttribute("seeker");
            }
            if (session.getAttribute("recruiter") != null) {
                session.removeAttribute("recruiter");
            }

            this.seeker = null;
            this.recruiter = null;

            userPath = "/../../index";
            // if user switches language
        } else if (userPath.equals("/myAppliedJob")) {
            // TODO: Implement language request

            String seekerId = request.getQueryString();
            if (seekerId != null) {
                ApplicationController appController = new ApplicationController();
                ArrayList<Application> applications = appController.findAppBySeeker(seekerId);
                if (applications != null) {
                    session.setAttribute("applications", applications);
                }
            }

        } else if (userPath.equals("/seeAppDetails")) {
            Job selectedJob = new Job();
            // get categoryId from request
            String jobId = request.getQueryString();

            if (jobId != null) {
                JobController jobController = new JobController();
                selectedJob = jobController.findJobById(jobId);
                request.setAttribute("selectedAppliedJob", selectedJob);
            }

            userPath = "/myAppliedJob";
        } else if (userPath.equals("/myInvitation")) {
            String seekerId = request.getQueryString();

            if (seekerId != null) {
                InvitationController invController = new InvitationController();
                ArrayList<Invitation> invitations = invController.findInviBySeeker(seekerId);
                session.setAttribute("invitations", invitations);
            }

        } else if (userPath.equals("/seeInvDetails")) {

            String result = request.getQueryString();

            String[] temp = result.split("\\?");
            String recruiterId = temp[0];
            String invId = temp[1];

            if (recruiterId != null && invId != null) {
                RecruiterController recruiterController = new RecruiterController();
                Recruiter recruiter = recruiterController.findRecruiterById(recruiterId);
                request.setAttribute("selectedRecruiter", recruiter);

                InvitationController invController = new InvitationController();
                Invitation invitation = invController.findInvById(invId);
                request.setAttribute("selectedInv", invitation);
            }
            userPath = "/myInvitation";
        } else if (userPath.equals("/registerPage")) {
            userPath = "/register";
        } else if (userPath.equals("/seeker_buildCV")) {

        } else if (userPath.equals("/createdJobDetail")) {
            Job selectedJob = new Job();
            // get categoryId from request
            String jobId = request.getQueryString();

            if (jobId != null) {
                JobController jobController = new JobController();
                selectedJob = jobController.findJobById(jobId);
                request.setAttribute("selectedCreatedJob", selectedJob);
            }

            userPath = "/recruiterHome";
        } else if (userPath.equals("/seeApplicants")) {
            String jobId = request.getQueryString();
            if (jobId != null) {
                ApplicationController appController = new ApplicationController();
                ArrayList<Application> applications = appController.findAppByJob(jobId);
                if (applications != null) {
                    session.setAttribute("applicants", applications);
                }
            }
        } else if (userPath.equals("/seeApplicantDetails")) {
            String seekerId = request.getQueryString();
            if (seekerId != null) {
                SeekerController sc = new SeekerController();
                Seeker selectedApplicant = sc.findSeekerById(seekerId);
                request.setAttribute("selectedApplicant", selectedApplicant);
            }
            userPath = "/seeApplicants";
        } else if (userPath.equals("/seeCadiDetails")) {
            String seekerId = request.getQueryString();
            if (seekerId != null) {
                SeekerController sc = new SeekerController();
                Seeker selectedApplicant = sc.findSeekerById(seekerId);
                request.setAttribute("selectedCadidate", selectedApplicant);
            }
            userPath = "/matchSeeker";

        } else if (userPath.equals("/manageRecruiterProfile")) {
            userPath = "/recruiter_manageProfile";
        } else if (userPath.equals("/createCategory")) {
            userPath = "/recruiter_createCategory";

            String recruiterId = request.getQueryString();
            if (recruiterId != null) {
                JobCategoryController jcc = new JobCategoryController();
                ArrayList<JobCategory> jobCategories = jcc.findCategoryByRecId(Integer.parseInt(recruiterId));
                request.setAttribute("JobCategory", jobCategories);
            }
        } else if (userPath.equals("/recruiter_createJob")) {
            int recruiterId = this.recruiter.getId();

            JobCategoryController jcc = new JobCategoryController();
            ArrayList<JobCategory> jobCategories = jcc.findCategoryByRecId(recruiterId);
            request.setAttribute("JobCategory", jobCategories);

        } else if (userPath.equals("/matchByJob")) {
            String jobId = request.getQueryString();
            if (jobId != null) {

                MatchController mc = new MatchController();
                ArrayList<MatchSeeker> results = mc.matchByJob(jobId);

                session.setAttribute("matchRank", results);
            }
            userPath = "/matchSeeker";

        }

        // use RequestDispatcher to forward request internally
        String url = "/WEB-INF/view" + userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userPath = request.getServletPath();
        HttpSession session = request.getSession();

        // if addToCart action is called
        if (userPath.equals("/searchJob")) {

            if (session.getAttribute("selectedJob") != null) {
                session.removeAttribute("selectedJob");
            }

            // get user input from request
            String keywords = request.getParameter("keywords");
            String location = request.getParameter("location");
            String jobSalary = request.getParameter("jobSalary");
            String jobType = request.getParameter("jobType");
            String jobCompen = request.getParameter("jobCompen");

            session.setAttribute("keywords", keywords);
            session.setAttribute("location", location);

            JobController jobController = new JobController();
            ArrayList<Job> jobs = jobController.findJobByKeywords(keywords, location, jobType, jobSalary, jobCompen);
            session.setAttribute("jobs", jobs);
            // if updateCart action is called
        } else if (userPath.equals("/applyJob")) {

            String jobId = request.getParameter("selectedJobId");
            String seekerId = request.getParameter("seekerId");

            ApplicationController appController = new ApplicationController();
            boolean result = appController.createApplication(jobId, seekerId);

            Boolean appResult = new Boolean(result);

            request.setAttribute("applyResult", appResult);

            userPath = "/searchJob";

            // if purchase action is called
        } else if (userPath.equals("/login")) {
            // TODO: Implement purchase action
            String userName = request.getParameter("userName");
            String passwd = request.getParameter("password");
            //System.out.println(userName + "11111" + passwd);

            SeekerController sc = new SeekerController();
            Seeker seeker = sc.login(userName, passwd);
            this.seeker = seeker;
            RecruiterController rc = new RecruiterController();
            Recruiter recruiter = rc.login(userName, passwd);
            this.recruiter = recruiter;
            if (seeker != null) {
                session.setAttribute("seeker", seeker);
                if (session.getAttribute("invalidAccount") != null) {
                    session.removeAttribute("invalidAccount");
                }
                if (session.getAttribute("jobs") != null) {
                    session.removeAttribute("jobs");
                }
                if (session.getAttribute("selectedJob") != null) {
                    session.removeAttribute("selectedJob");
                }
                userPath = "/searchJob";
            } else if (recruiter != null) {
                session.setAttribute("recruiter", recruiter);
                if (session.getAttribute("invalidAccount") != null) {
                    session.removeAttribute("invalidAccount");
                }
                userPath = "/recruiterHome";
                JobController jc = new JobController();
                ArrayList<Job> jobs = jc.findJobByRecruiter("" + recruiter.getId());
                session.setAttribute("jobs", jobs);
            } else {
                session.setAttribute("invalidAccount", 1);
                userPath = "/login";
            }
        } else if (userPath.equals("/register")) {

            // get input from request
            String regEmail = request.getParameter("reg_email");
            String regPasswd = request.getParameter("reg_pwd");
            String registerType = request.getParameter("register_type");

            if (registerType.equals("seeker")) {
                SeekerController sc = new SeekerController();
                if (sc.isRegisterable(regEmail, regPasswd)) {
                    sc.registerSeeker(regEmail, regPasswd);
//                    Seeker seeker = new Seeker();
                    Seeker seeker = sc.login(regEmail, regPasswd);
                    seeker.setFirstName(regEmail);

                    session.setAttribute("seeker", seeker);
                    if (session.getAttribute("jobs") != null) {
                        session.removeAttribute("jobs");
                    }
                    userPath = "/searchJob";
                } else {
                    request.setAttribute("invalidAccount", 1);
                    userPath = "/register";
                }
            } else if (registerType.equals("recruiter")) {
                RecruiterController rc = new RecruiterController();
                if (rc.isRegisterable(regEmail, regPasswd)) {
                    rc.registerRecruiter(regEmail, regPasswd);
                    Recruiter recruiter = new Recruiter();
                    recruiter = rc.login(regEmail, regPasswd);
                    recruiter.setCompanyName(regEmail);
                    this.recruiter = recruiter;
                    session.setAttribute("recruiter", recruiter);
                    userPath = "/recruiterHome";
                    JobController jc = new JobController();
                    ArrayList<Job> jobs = jc.findJobByRecruiter("" + recruiter.getId());
                    session.setAttribute("jobs", jobs);
                } else {
                    request.setAttribute("invalidAccount", 1);
                    userPath = "/register";
                }
            } else {
                session.setAttribute("invalidInput", 1);
                userPath = "/register";
            }

            // if purchase action is called
        } else if (userPath.equals("/updateSeekerCV")) {
            String firstName = request.getParameter("f_name");
            String lastName = request.getParameter("l_name");
            String day = request.getParameter("day");
            String month = request.getParameter("month");
            String year = request.getParameter("year");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");
            String eduLevel = request.getParameter("edu_level");
            String skillsets = request.getParameter("skillsets");
            String description = request.getParameter("description");
            Date date = java.sql.Date.valueOf(year + "-" + month + "-" + day);

            Seeker s = new Seeker(seeker.getId(), seeker.getPasswd(), phone, address, seeker.getEmail(), firstName, lastName, eduLevel, skillsets, description, date, true);
            SeekerController sc = new SeekerController();
            if (sc.modifySeeker(s)) {
                seeker = s;
                session.setAttribute("seeker", seeker);
                userPath = "/searchJob";
            } else {
                session.setAttribute("updateState", 1);
                userPath = "/seeker_buildCV";
            }
        } else if (userPath.equals("/closeJob")) {
            JobController jc = new JobController();
            String jobId = request.getParameter("close");
            jc.adOrCloseJob(jobId, 0);
            ArrayList<Job> jobs = jc.findJobByRecruiter("" + this.recruiter.getId());
            session.setAttribute("jobs", jobs);
            userPath = "/recruiterHome";
        } else if (userPath.equals("/advertiseJob")) {
            JobController jc = new JobController();
            String jobId = request.getParameter("advertise");
            jc.adOrCloseJob(jobId, 1);
            ArrayList<Job> jobs = jc.findJobByRecruiter("" + this.recruiter.getId());
            session.setAttribute("jobs", jobs);
            userPath = "/recruiterHome";
        } else if (userPath.equals("/deleteJob")) {
            JobController jc = new JobController();
            String jobId = request.getParameter("delete");
            jc.deleteJob(jobId);
            ArrayList<Job> jobs = jc.findJobByRecruiter("" + this.recruiter.getId());
            session.setAttribute("jobs", jobs);
            userPath = "/recruiterHome";
        } else if (userPath.equals("/sendInvitation")) {
            String seekerId = request.getParameter("seekerId");
            String recruiterId = request.getParameter("recruiterId");

            InvitationController ic = new InvitationController();
            boolean result = ic.createInvitation(seekerId, recruiterId);

            Boolean invResult = new Boolean(result);

            request.setAttribute("invResult", invResult);

            userPath = "/seeApplicants";
        } else if (userPath.equals("/sendInvToCa")) {
            String seekerId = request.getParameter("seekerId");
            String recruiterId = request.getParameter("recruiterId");

            InvitationController ic = new InvitationController();
            boolean result = ic.createInvitation(seekerId, recruiterId);

            Boolean invResult = new Boolean(result);

            request.setAttribute("invResult", invResult);

            userPath = "/matchSeeker";

        } else if (userPath.equals("/recruiterProfile")) {
            String companyName = request.getParameter("company_name");
            String address = request.getParameter("address");
            String phone = request.getParameter("phoneNumber");
            String description = request.getParameter("companyDesc");

            RecruiterController rc = new RecruiterController();
            Recruiter r = rc.findRecruiterById(Integer.toString(recruiter.getId()));
            r.setAddress(address);
            r.setCompanyName(companyName);
            r.setPhone(phone);
            r.setDescription(description);
            if (rc.modifyRecruiter(r)) {
                this.recruiter = r;
                session.setAttribute("recruiter", r);
                userPath = "/recruiterHome";
            } else {
                request.setAttribute("invalidRecruiterProfile", 1);
                userPath = "/recruiter_manageProfile";
            }

        } else if (userPath.equals("/createNewCategory")) {
            String categoryName = request.getParameter("job_category");
            JobCategoryController jcc = new JobCategoryController();
            if (!jcc.createCategory(categoryName, recruiter.getId())) {
                request.setAttribute("invalidCategoryUpdate", 1);
            }
            ArrayList<JobCategory> ca = jcc.findCategoryByRecId(recruiter.getId());
            request.setAttribute("jobCategory", ca);
            userPath = "/recruiter_createCategory";
        } else if (userPath.equals("/createjob")) {
            String jobName = request.getParameter("jobName");
            String jobLocation = request.getParameter("jobLocation");
            String jobPosition = request.getParameter("jobPosition");
            int salary = Integer.parseInt(request.getParameter("salary"));
            String keywords = request.getParameter("keywords");
            String jobType = request.getParameter("jobType");
            String description = request.getParameter("description");
            String compensation = request.getParameter("compensation");
            String jobCategory = request.getParameter("jobCategory");
            JobController jc = new JobController();
            if (jc.createJob(jobName, jobLocation, jobPosition, salary, keywords, jobType, description, compensation, jobCategory, recruiter.getId())) {
                userPath = "/recruiterHome";

                ArrayList<Job> jobs = jc.findJobByRecruiter("" + this.recruiter.getId());
                session.setAttribute("jobs", jobs);
            } else {
                userPath = "/recruiterHome";
                request.setAttribute("createJobError", 1);
            }
        } else if (userPath.equals("/searchSeeker")) {
            MatchController mc = new MatchController();
            String keywords = request.getParameter("keywords");
            ArrayList<MatchSeeker> results = mc.matchByKeywords(keywords);

            session.setAttribute("matchRank", results);

            userPath = "/matchSeeker";
        } else if (userPath.equals("/modifyJob")) {
            JobController job = new JobController();
            String jobId = request.getParameter("modify");
            Job j = job.findJobById(jobId);
            request.setAttribute("job", j);
            JobCategoryController jcc = new JobCategoryController();
            ArrayList<JobCategory> jobCategories = jcc.findCategoryByRecId(this.recruiter.getId());
            request.setAttribute("JobCategory", jobCategories);
            userPath = "/recruiter_modifyJob";

        } else if (userPath.equals("/recruiterModifyJob")) {
            String jobName = request.getParameter("jobName");
            String jobLocation = request.getParameter("jobLocation");
            String jobPosition = request.getParameter("jobPosition");
            int salary = Integer.parseInt(request.getParameter("salary"));
            String keywords = request.getParameter("keywords");
            String jobType = request.getParameter("jobType");
            String description = request.getParameter("description");
            String compensation = request.getParameter("compensation");
            String jobCategory = request.getParameter("jobCategory");
            JobController jc = new JobController();
            
            
            
            if (jc.modifyJob(jobName, jobLocation, jobPosition, salary, keywords, jobType, description, compensation, jobCategory, Integer.parseInt(request.getParameter("jobId")))) {
                userPath = "/recruiterHome";
                ArrayList<Job> jobs = jc.findJobByRecruiter("" + this.recruiter.getId());
                session.setAttribute("jobs", jobs);
            } else {
                request.setAttribute("createJobError", 1);
                userPath = "/recruiterHome";
            }
        }
        // use RequestDispatcher to forward request internally
        String url = "/WEB-INF/view" + userPath + ".jsp";

        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
