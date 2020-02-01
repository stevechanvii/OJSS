/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.SqlExecute;
import entity.Recruiter;
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
public class RecruiterController {

    public Recruiter login(String email, String passwd) {
        ArrayList<String> result = null;
        SqlExecute query = new SqlExecute();
        Recruiter recruiter = null;

        String sql = " SELECT * FROM recruiter WHERE email = \"" + email + "\"";
        query.setSql(sql);
        result = query.doJsonQuery();

        if (result.isEmpty()) {
            System.out.println("Wrong Account!");
        } else {
            JsonReader reader = Json.createReader(new StringReader(result.get(0)));
            JsonObject jobObject = reader.readObject();
            if (jobObject.getString("password").equals(passwd)) {
                recruiter = convertToRecruiter(result.get(0));
            } else {
                System.out.println("Wrong password!");
            }
        }
        return recruiter;
    }
    
    public Recruiter findRecruiterById(String Id){
        ArrayList<String> result = null;
        String sql = "SELECT * FROM recruiter WHERE recruiter_id = " + Id ;
        SqlExecute query = new SqlExecute();
        Recruiter recruiter = null;
        
        query.setSql(sql);
        result = query.doJsonQuery();
        
        if (!result.isEmpty()){
            recruiter = convertToRecruiter(result.get(0));
        }
        return recruiter;
    }

    private Recruiter convertToRecruiter(String results) {
        JsonReader reader = Json.createReader(new StringReader(results));
        JsonObject recruiterObject = reader.readObject();
        Recruiter recruiter = new Recruiter();
        recruiter.setId(recruiterObject.getInt("recruiter_id"));
        recruiter.setEmail(recruiterObject.getString("email"));
        recruiter.setPasswd(recruiterObject.getString("password"));
        recruiter.setCompanyName(recruiterObject.getString("company_name"));
        recruiter.setPhone(recruiterObject.getString("phone"));
        recruiter.setAddress(recruiterObject.getString("address"));
        recruiter.setDescription(recruiterObject.getString("description"));
        if (recruiterObject.getInt("state") == 1) {
            recruiter.setIsActivate(true);
        } else {
            recruiter.setIsActivate(false);
        }
        return recruiter;

    }

    private ArrayList<Recruiter> convertToRecruiterList(ArrayList<String> results) {
        ArrayList<Recruiter> recruiters = new ArrayList<Recruiter>();
        for (String item : results) {
            JsonReader reader = Json.createReader(new StringReader(item));
            JsonObject recruiterObject = reader.readObject();
            Recruiter recruiter = new Recruiter();
            recruiter.setId(recruiterObject.getInt("recruiter_id"));
            recruiter.setEmail(recruiterObject.getString("email"));
            recruiter.setPasswd(recruiterObject.getString("password"));
            recruiter.setCompanyName(recruiterObject.getString("company_name"));
            recruiter.setPhone(recruiterObject.getString("phone"));
            recruiter.setAddress(recruiterObject.getString("address"));
            recruiter.setDescription(recruiterObject.getString("description"));
            if (recruiterObject.getInt("state") == 1) {
                recruiter.setIsActivate(true);
            } else {
                recruiter.setIsActivate(false);
            }
            recruiters.add(recruiter); 
        }
        return recruiters;

    }

     public boolean registerRecruiter(String email, String password) {
        String sql = "INSERT INTO ojss.recruiter (email, password) VALUES ('" + email + "', '" + password + "');";
        return executeSql(sql);
    }

    public boolean modifyRecruiter(Recruiter recruiter) {
        String sql = "UPDATE ojss.recruiter SET `email` = '" + recruiter.getEmail() + "', `password` = '"
                + recruiter.getPasswd() + "', `company_name` = '" + recruiter.getCompanyName() + "', "
                + "`phone` = '" + recruiter.getPhone() + "', `address` = '" + recruiter.getAddress() + "', "
                + "`description` = '" + recruiter.getDescription() + "', `state` = "
                + recruiter.isIsActivate() + " WHERE recruiter_id = " + recruiter.getId() + ";";
        return executeSql(sql);
    }

    public boolean deleteRecruiter(int id) {
        String sql = "DELETE FROM ojss.recruiter WHERE recruiter_id = " + id + ";";
        return executeSql(sql);
    }


    private boolean executeSql(String sql) {
        boolean result;
        SqlExecute statement = new SqlExecute();
        statement.setSql(sql);
        result = statement.execute();
        return result;
    }

      public boolean suspendRecruiter(int id) {
        String sql = "UPDATE ojss.recruiter SET `state` = 0 WHERE recruiter_id = " + id + ";";
        return executeSql(sql);
    }

    public boolean activateRecruiter(int id) {
        String sql = "UPDATE ojss.recruiter SET `state` = 1 WHERE recruiter_id = " + id + ";";
        return executeSql(sql);
    }

    public ArrayList<String> findRecruiter(String key, String value) {
        SqlExecute query = new SqlExecute();
        String sql = "SELECT * from ojss.recruiter WHERE `" + key + "` = '" + value + "';";
        query.setSql(sql);
        return query.doJsonQuery();
    }

    public boolean isRegisterable(String email, String password) {
        RegexUtil regex = new RegexUtil();
        if (regex.isEmail(email) && regex.isPassword(password)) {
            SeekerController sc = new SeekerController();
            return sc.findSeeker("email", email).isEmpty() && findRecruiter("email", email).isEmpty();
        }
        return false;
    }
}
