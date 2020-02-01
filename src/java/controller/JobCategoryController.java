/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.SqlExecute;
import entity.JobCategory;
import entity.Recruiter;
import java.io.StringReader;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import util.RegexUtil;

/**
 *
 * @author stevechanvii
 */
public class JobCategoryController {

    public ArrayList<JobCategory> findCategoryByRecId(int recruiterId) {
        SqlExecute query = new SqlExecute();
        query.setSql("SELECT * from ojss.category WHERE `recruiter_id` = " + recruiterId + ";");

        return convertToCategoryList(query.doJsonQuery());
    }

    public JobCategory findCategoryById(int id) {
        SqlExecute query = new SqlExecute();
        query.setSql("SELECT * from ojss.category WHERE `category_id` = '" + id + "';");

        return convertToCategory(query.doJsonQuery());
    }

    

    public ArrayList<JobCategory> convertToCategoryList(ArrayList<String> category) {
        ArrayList<JobCategory> jobCategoryList = new ArrayList<JobCategory>();
        for (String item : category) {
            JobCategory jobCategory = new JobCategory();
            JsonReader reader = Json.createReader(new StringReader(item));
            JsonObject categoryObject = reader.readObject();
            jobCategory.setCategoryId(categoryObject.getInt("category_id"));
            jobCategory.setCategoryName(categoryObject.getString("name"));
            RecruiterController sc = new RecruiterController();
            Recruiter recruiter = sc.findRecruiterById(Integer.toString(categoryObject.getInt("recruiter_id")));
            jobCategory.setRecruiter(recruiter);
            jobCategoryList.add(jobCategory);
        }
        return jobCategoryList;
    }

    public JobCategory convertToCategory(ArrayList<String> category) {

        JobCategory jobCategory = new JobCategory();
        Recruiter recruiter = new Recruiter();
        JsonReader reader = Json.createReader(new StringReader(category.get(0)));
        JsonObject categoryObject = reader.readObject();
        jobCategory.setCategoryId(categoryObject.getInt("category_id"));
        jobCategory.setCategoryName(categoryObject.getString("name"));
        RecruiterController sc = new RecruiterController();
        recruiter = sc.findRecruiterById(Integer.toString(categoryObject.getInt("recruiter_id")));
        jobCategory.setRecruiter(recruiter);

        return jobCategory;
    }

    private boolean executeSql(String sql) {
        boolean result;
        SqlExecute query = new SqlExecute();
        query.setSql(sql);
        result = query.execute();
        return result;
    }

    public boolean createCategory(String name, int recruiterId) {
        RegexUtil regex = new RegexUtil();
        if (regex.isCategory(name)) {
            String sql = "INSERT INTO ojss.category (`name`, recruiter_id) VALUES ('" + name + "', " + recruiterId + ");";
            return executeSql(sql);
        }
        return false;
    }

    public boolean modifyCategory(JobCategory jobCategory) {
        String sql = "UPDATE ojss.category SET `name` = '" + jobCategory.getCategoryName() + "' WHERE category_id = " + jobCategory.getCategoryId() + ";";
        return executeSql(sql);
    }

    public boolean deleteCategory(JobCategory jobCategory) {
        String sql = "DELETE FROM ojss.category WHERE category_id = " + jobCategory.getCategoryId() + ";";
        return executeSql(sql);
    }

}
