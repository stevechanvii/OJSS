/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.SqlExecute;
import entity.Application;
import entity.Job;
import entity.Recruiter;
import entity.Seeker;
import java.io.StringReader;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author 41649
 */
public class ApplicationController {

    public ArrayList<Application> findAppByJob(String jobId) {
        ArrayList<Application> results = null;
        ArrayList<String> strResults = null;
        String sql = " SELECT * FROM application WHERE job_id = " + jobId;
        SqlExecute query = new SqlExecute();
        
        query.setSql(sql);
        strResults = query.doJsonQuery();
        
        if (!strResults.isEmpty()){
            results = convertToApplList(strResults);
        }    
        
        return results;
    }

    public ArrayList<Application> findAppBySeeker(String seekerId) {
        ArrayList<Application> results = null;

        ArrayList<String> strResults = null;
        String sql = " SELECT * FROM application WHERE seeker_id = " + seekerId;
        SqlExecute query = new SqlExecute();
        
        query.setSql(sql);
        strResults = query.doJsonQuery();
        
        if (!strResults.isEmpty()){
            results = convertToApplList(strResults);
        }    
        
        return results;
    }

    public Application findAppBySeekerJob(String jobId, String seekerId) {
        Application appli = null;

        ArrayList<String> result = null;
        String sql = " SELECT * FROM application WHERE job_id = " + jobId + " AND seeker_id = " + seekerId;
        SqlExecute query = new SqlExecute();

        query.setSql(sql);
        result = query.doJsonQuery();

        if (!result.isEmpty()) {
            appli = convertToApp(result.get(0));
        }
        return appli;
    }

    public boolean createApplication(String jobId, String seekerId) {
        boolean result = false;
        Application app = findAppBySeekerJob(jobId, seekerId);
        if (app == null) {
            String sql = " INSERT INTO ojss.application(create_time, job_id, seeker_id) ";

            long mills = System.currentTimeMillis();
            Date date = new Date(mills);

            sql += " Values (str_to_date('" + date.toString() + "', '%Y-%m-%d %H-%i-%s'), " + jobId + ", " + seekerId + ") ";
            result = executeSql(sql);
        }

        return result;
    }

    private Application convertToApp(String results) {
        JsonReader reader = Json.createReader(new StringReader(results));
        JsonObject appObject = reader.readObject();
        Application application = new Application();
        application.setAppId(appObject.getInt("app_id"));

        String dateStr = appObject.getString("create_time");
        Timestamp date = Timestamp.valueOf(dateStr);
        application.setDate(date);

        JobController jobController = new JobController();
        Job job = jobController.findJobById("" + appObject.getInt("job_id"));

        SeekerController seekerController = new SeekerController();
        Seeker seeker = seekerController.findSeekerById("" + appObject.getInt("seeker_id"));

        application.setJob(job);
        application.setSeeker(seeker);

        return application;

    }

    private ArrayList<Application> convertToApplList(ArrayList<String> results) {
        ArrayList<Application> applications = new ArrayList<Application>();
        for (String item : results) {
            JsonReader reader = Json.createReader(new StringReader(item));
            JsonObject appObject = reader.readObject();
            Application application = new Application();
            application.setAppId(appObject.getInt("app_id"));

            String dateStr = appObject.getString("create_time");
            Timestamp date = Timestamp.valueOf(dateStr);
            application.setDate(date);

            JobController jobController = new JobController();
            Job job = jobController.findJobById("" + appObject.getInt("job_id"));

            SeekerController seekerController = new SeekerController();
            Seeker seeker = seekerController.findSeekerById("" + appObject.getInt("seeker_id"));

            application.setJob(job);
            application.setSeeker(seeker);

            applications.add(application);
        }
        return applications;

    }

    private boolean executeSql(String sql) {
        boolean result;
        SqlExecute statement = new SqlExecute();
        statement.setSql(sql);
        result = statement.execute();
        return result;
    }
}
