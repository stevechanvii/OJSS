/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.SqlExecute;
import entity.Job;
import entity.Recruiter;
import java.io.StringReader;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author 41649
 */
public class JobController {

    public ArrayList<Job> findJobByRecruiter(String recruiterId) {
        ArrayList<Job> results = new ArrayList<Job>();

        String sql = " SELECT * FROM job Where recruiter_id = " + recruiterId;

        SqlExecute query = new SqlExecute();

        query.setSql(sql);
        ArrayList<String> strResults = query.doJsonQuery();
        results = convertToJob(strResults);
        return results;
    }

    public Job findJobById(String id) {
        Job result = new Job();

        SqlExecute query = new SqlExecute();
        String sql = " SELECT * FROM job Where job_id = " + id;

        query.setSql(sql);
        ArrayList<String> strResults = query.doJsonQuery();
        ArrayList<Job> results = convertToJob(strResults);

        result = results.get(0);

        return result;
    }

    public ArrayList<Job> findJobByKeywords(String Keywords, String location, String jobType, String jobSalary, String jobCompen) {
        ArrayList<Job> results = new ArrayList<Job>();
        SqlExecute query = new SqlExecute();

        String sql = " SELECT * FROM job WHERE is_advertised = 1 ";

        if (Keywords.trim().length() != 0) {
            sql += "and (";
            String[] keywords = Keywords.split(",");
            String reg = " REGEXP '^.*(";
            for (int i = 0; i < keywords.length; i++) {
                if (i != keywords.length - 1) {
                    reg += keywords[i].trim() + "|";
                } else {
                    reg += keywords[i].trim() + ").*$' ";
                }
            }
            sql += " name " + reg + " OR " + " position " + reg + " OR " + " keywords " + reg + ") ";
        }

        if (location.trim().length() != 0) {
            sql += "and (";
            String reg = " REGEXP '^.*(" + location + ").*$' ";
            sql += " address " + reg + ") ";
        }

        if (jobType != null && jobType.trim().length() != 0 && !jobType.equals("Please select")) {
            sql += " and ( type = '" + jobType + "') ";
        }

        if (jobSalary != null && jobSalary.trim().length() != 0 && !jobSalary.equals("Please select")) {
            sql += " and ( salary >= " + jobSalary + " ) ";
        }

        if (jobCompen != null && jobCompen.trim().length() != 0 && !jobCompen.equals("Please select")) {
            sql += " and ( compensation = '" + jobCompen + "' ) ";
        }

        sql += " ORDER BY job_id DESC ";

        query.setSql(sql);
        ArrayList<String> strResults = query.doJsonQuery();
        results = convertToJob(strResults);

        return results;
    }

    public ArrayList<Job> findAllJobs() {
        ArrayList<Job> results = new ArrayList<Job>();
        return results;
    }

    private ArrayList<Job> convertToJob(ArrayList<String> jobs) {
        RecruiterController reController = new RecruiterController();
        ArrayList<JsonObject> jsResults = new ArrayList<JsonObject>();
        ArrayList<Job> results = new ArrayList<Job>();
        for (String item : jobs) {
            JsonReader reader = Json.createReader(new StringReader(item));
            JsonObject jobObject = reader.readObject();

            Job job = new Job();
            job.setJobId(jobObject.getInt("job_id"));
            job.setJobName(jobObject.getString("name"));
            job.setPosition(jobObject.getString("position"));
            job.setAddress(jobObject.getString("address"));
            job.setSalary(jobObject.getInt("salary"));
            job.setDescription(jobObject.getString("description"));
            job.setCompensation(jobObject.getString("compensation"));
            job.setJobType(jobObject.getString("type"));
            job.setRelKeywords(jobObject.getString("keywords"));
            if (jobObject.getInt("is_advertised") == 1) {
                job.setIsAdvertise(true);
            } else {
                job.setIsAdvertise(false);
            }

            Recruiter recruiter = reController.findRecruiterById("" + jobObject.getInt("recruiter_id"));
            job.setRecruiter(recruiter);

            results.add(job);
        }

        return results;
    }

    private boolean executeSql(String sql) {
        boolean result;
        SqlExecute query = new SqlExecute();
        query.setSql(sql);
        result = query.execute();
        return result;
    }

    public boolean deleteJob(String jobId) {
        String pre_sql1 = "DELETE FROM application WHERE job_id = " + jobId + ";";
        String pre_sql2 = "DELETE FROM job_category WHERE job_id = " + jobId + ";";
        executeSql(pre_sql1);
        executeSql(pre_sql2);
        String sql = "DELETE FROM job WHERE job_id = " + jobId + ";";
        return executeSql(sql);
    }

    public boolean adOrCloseJob(String jobId, int state) {

        String sql = "UPDATE job SET `is_advertised` = " + state + "  WHERE job_id = " + jobId + ";";
        return executeSql(sql);
    }

    public boolean createJob(String jobName, String jobLocation, String jobPosition,
            int salary, String keywords, String jobType, String description,
            String compensation, String jobCategory, int recruiterId) {

        String sql = "INSERT INTO ojss.job (`name`, `position`, address, salary, "
                + "description, compensation, keywords, `type`, is_advertised, "
                + "recruiter_id) " + "VALUES ('" + jobName + "', '" + jobPosition + "', "
                + "'" + jobLocation + "', " + salary + ", '" + description + "', '" + compensation + "', "
                + "'" + keywords + "', '" + jobType + "', 0, " + recruiterId + ");";
        return executeSql(sql);
    }

    public boolean modifyJob(String jobName, 
                              String jobLocation, 
                              String jobPosition,
                              int salary, 
                              String keywords, 
                              String jobType, 
                              String description,
                              String compensation, 
                              String jobCategory, 
                              int jobId) {
        String sql = "UPDATE ojss.job SET `name` = '" + jobName + "', `position` = '"
                + jobPosition + "', `address` = '" + jobLocation + "', `salary` = "
                + salary + ", `description` = '" + description + "', `compensation` = '"
                + compensation + "', `keywords` = '" + keywords + "', `type` = '"
                + jobType + "' WHERE job_id = " + jobId + ";";
        return executeSql(sql);
    }
}
