/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.SqlExecute;
import entity.Seeker;
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
public class SeekerController {

    public Seeker login(String email, String passwd) {
        ArrayList<String> result = null;
        SqlExecute query = new SqlExecute();
        Seeker seeker = null;

        String sql = " SELECT * FROM seeker WHERE email = \"" + email + "\"";
        query.setSql(sql);
        result = query.doJsonQuery();

        if (result.isEmpty()) {
            System.out.println("Wrong Account!");
        } else {
            JsonReader reader = Json.createReader(new StringReader(result.get(0)));
            JsonObject jobObject = reader.readObject();
            if (jobObject.getString("password").equals(passwd)) {
                seeker = convertToSeeker(result.get(0));
            } else {
                System.out.println("Wrong password!");
            }
        }
        return seeker;
    }

    public Seeker findSeekerById(String Id) {
        ArrayList<String> result = null;
        SqlExecute query = new SqlExecute();
        Seeker seeker = null;

        String sql = " SELECT * FROM seeker WHERE seeker_id = \"" + Id + "\"";
        query.setSql(sql);
        result = query.doJsonQuery();

        if (!result.isEmpty()) {
            JsonReader reader = Json.createReader(new StringReader(result.get(0)));
            JsonObject jobObject = reader.readObject();
            seeker = convertToSeeker(result.get(0));
        }
        return seeker;
    }

    private Seeker convertToSeeker(String results) {
        JsonReader reader = Json.createReader(new StringReader(results));
        JsonObject seekerObject = reader.readObject();
        Seeker seeker = new Seeker();
        seeker.setId(seekerObject.getInt("seeker_id"));
        seeker.setEmail(seekerObject.getString("email"));
        seeker.setPasswd(seekerObject.getString("password"));
        seeker.setFirstName(seekerObject.getString("first_name"));

        if (!seekerObject.getString("last_name").equals("null")) {
            seeker.setLastName(seekerObject.getString("last_name"));
        }
        if (!seekerObject.getString("phone").equals("null")) {
            seeker.setPhone(seekerObject.getString("phone"));
        }
        if (!seekerObject.getString("address").equals("null")) {
            seeker.setAddress(seekerObject.getString("address"));
        }
        if (!seekerObject.getString("education").equals("null")) {
            seeker.setEducation(seekerObject.getString("education"));
        }
        if (!seekerObject.getString("skillset").equals("null")) {
            seeker.setSkillset(seekerObject.getString("skillset"));
        }
        if (!seekerObject.getString("description").equals("null")) {
            seeker.setDescription(seekerObject.getString("description"));
        }
        if (!seekerObject.getString("birthday").equals("null")) {
            seeker.setBirthday(java.sql.Date.valueOf(seekerObject.getString("birthday")));
        }
        if (seekerObject.getInt("state") == 1) {
            seeker.setIsActivate(true);
        } else {
            seeker.setIsActivate(false);
        }
        return seeker;

    }

    private ArrayList<Seeker> convertToSeekerList(ArrayList<String> results) {
        ArrayList<Seeker> seekers = new ArrayList<Seeker>();
        for (String item : results) {
            JsonReader reader = Json.createReader(new StringReader(item));
            JsonObject seekerObject = reader.readObject();
            Seeker seeker = new Seeker();
            seeker.setId(seekerObject.getInt("seeker_id"));
            seeker.setEmail(seekerObject.getString("email"));
            seeker.setPasswd(seekerObject.getString("password"));
            seeker.setFirstName(seekerObject.getString("first_name"));

            if (!seekerObject.getString("last_name").equals("null")) {
                seeker.setLastName(seekerObject.getString("last_name"));
            }
            if (!seekerObject.getString("phone").equals("null")) {
                seeker.setPhone(seekerObject.getString("phone"));
            }
            if (!seekerObject.getString("address").equals("null")) {
                seeker.setAddress(seekerObject.getString("address"));
            }
            if (!seekerObject.getString("education").equals("null")) {
                seeker.setEducation(seekerObject.getString("education"));
            }
            if (!seekerObject.getString("skillset").equals("null")) {
                seeker.setSkillset(seekerObject.getString("skillset"));
            }
            if (!seekerObject.getString("description").equals("null")) {
                seeker.setDescription(seekerObject.getString("description"));
            }
            if (!seekerObject.getString("birthday").equals("null")) {
                seeker.setBirthday(java.sql.Date.valueOf(seekerObject.getString("birthday")));
            }
            if (seekerObject.getInt("state") == 1) {
                seeker.setIsActivate(true);
            } else {
                seeker.setIsActivate(false);
            }
            seekers.add(seeker);
        }
        return seekers;

    }

    public boolean registerSeeker(String email, String password) {
        boolean result = false;
        SqlExecute query = new SqlExecute();
        String sql = "INSERT INTO ojss.seeker (email, password, first_name) VALUES ('" + email + "', '" + password + "', '" + email + "');";
        query.setSql(sql);
        result = query.execute();
        return result;
    }

    public boolean modifySeeker(Seeker seeker) {
        String sql = "UPDATE ojss.seeker SET `email` = '" + seeker.getEmail() + "', `password` = '"
                + seeker.getPasswd() + "', `first_name` = '" + seeker.getFirstName() + "', `last_name` = '"
                + seeker.getLastName() + "', `phone` = '" + seeker.getPhone() + "', `address` = '"
                + seeker.getAddress() + "', `education` = '" + seeker.getEducation() + "', `description` = '"
                + seeker.getDescription() + "', `birthday` = '" + seeker.getBirthday() + "', `state` = "
                + seeker.isIsActivate() + ", `skillset` = '" + seeker.getSkillset() + "' WHERE seeker_id = " + seeker.getId() + ";";
        return executeSql(sql);
    }

    public boolean deleteSeeker(int id) {
        String sql = "DELETE FROM ojss.seeker WHERE seeker_id = " + id + ";";
        return executeSql(sql);
    }

    private boolean executeSql(String sql) {
        boolean result;
        SqlExecute query = new SqlExecute();
        query.setSql(sql);
        result = query.execute();
        return result;
    }

    public boolean suspendSeeker(int id) {
        String sql = "UPDATE ojss.seeker SET `state` = 0 WHERE seeker_id = " + id + ";";
        return executeSql(sql);
    }

    public boolean activateSeeker(int id) {
        String sql = "UPDATE ojss.seeker SET `state` = 1 WHERE seeker_id = " + id + ";";
        return executeSql(sql);
    }

    public ArrayList<String> findSeeker(String key, String value) {
        SqlExecute query = new SqlExecute();
        String sql = "SELECT * from ojss.seeker WHERE `" + key + "` = '" + value + "';";
        query.setSql(sql);
        return query.doJsonQuery();
    }

    public ArrayList<Seeker> findAllSeeker() {
        SqlExecute query = new SqlExecute();
        String sql = "SELECT * FROM ojss.seeker";
        query.setSql(sql);
        ArrayList<String> strResult = query.doJsonQuery();
        ArrayList<Seeker> seekers = convertToSeekerList(strResult);
        return seekers;
    }

    public boolean isRegisterable(String email, String password) {
        RegexUtil regex = new RegexUtil();
        if (regex.isEmail(email) && regex.isPassword(password)) {
            RecruiterController rc = new RecruiterController();
            return rc.findRecruiter("email", email).isEmpty() && findSeeker("email", email).isEmpty();
        }
        return false;
    }

}
