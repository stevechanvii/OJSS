/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.SqlExecute;
import entity.Application;
import entity.Invitation;
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
public class InvitationController {
    public Invitation findInvById(String invId){
        ArrayList<String> strResults = null;
        
        Invitation invitation = null;
        
        String sql = " SELECT * FROM invitation WHERE inv_id = " + invId;
        SqlExecute query = new SqlExecute();
        query.setSql(sql);
        strResults = query.doJsonQuery();
        
        if (!strResults.isEmpty()){
            invitation = convertToInv(strResults.get(0));
        }
        
        return invitation;
    }
    
    public ArrayList<Invitation> findInviBySeeker(String seekerId) {
        ArrayList<Invitation> invitations = null;
        
        ArrayList<String> result = null;
        String sql = " SELECT * FROM invitation WHERE seeker_id = " + seekerId;
        SqlExecute query = new SqlExecute();
        
        query.setSql(sql);
        result = query.doJsonQuery();
        
        if (!result.isEmpty()) {
            invitations = convertToInvList(result);
        }
        
        return invitations;
    }
    
    private Invitation findInvBySeekerRecruiter(String seekerId, String recruiterId){
        ArrayList<String> strResults = null;
        
        Invitation invitation = null;
        
        String sql = " SELECT * FROM invitation WHERE seeker_id = " + seekerId + " AND " + " recruiter_id = " + recruiterId;
        SqlExecute query = new SqlExecute();
        query.setSql(sql);
        strResults = query.doJsonQuery();
        
        if (!strResults.isEmpty()){
            invitation = convertToInv(strResults.get(0));
        }
        
        return invitation;
    }
    
    public boolean createInvitation(String seekerId, String recruiterId){
        boolean result = false;
        Invitation invitation = findInvBySeekerRecruiter(seekerId, recruiterId);
        if (invitation == null){
            long mills = System.currentTimeMillis();
            Date date = new Date(mills);
            
            String sql = "INSERT INTO ojss.invitation (recruiter_id, seeker_id, create_time, description) " +
                    " VALUES (" + recruiterId +", " + seekerId + ", str_to_date('" + date.toString() + "', '%Y-%m-%d'), 'Hi, Guys. Welcome to Join us!');";
            
            result = executeSql(sql);
        }    
        return result;
    }

    private Invitation convertToInv(String results) {
        JsonReader reader = Json.createReader(new StringReader(results));
        JsonObject invObject = reader.readObject();
        Invitation invitation = new Invitation();
        invitation.setInvId(invObject.getInt("inv_id"));

        String dateStr = invObject.getString("create_time");
        Timestamp date = Timestamp.valueOf(dateStr);
        invitation.setCreateDate(date);

        SeekerController seekerController = new SeekerController();
        Seeker seeker = seekerController.findSeekerById("" + invObject.getInt("seeker_id"));

        RecruiterController recruiterController = new RecruiterController();
        Recruiter recruiter = recruiterController.findRecruiterById("" + invObject.getInt("recruiter_id"));

        invitation.setSeeker(seeker);
        invitation.setRecruiter(recruiter);
        
        invitation.setDescription(invObject.getString("description"));

        return invitation;

    }

    private ArrayList<Invitation> convertToInvList(ArrayList<String> results) {
        ArrayList<Invitation> invitations = new ArrayList<Invitation>();
        for (String item : results) {
            JsonReader reader = Json.createReader(new StringReader(item));
            JsonObject invObject = reader.readObject();
            Invitation invitation = new Invitation();
            invitation.setInvId(invObject.getInt("inv_id"));

            String dateStr = invObject.getString("create_time");
            Timestamp date = Timestamp.valueOf(dateStr);
            invitation.setCreateDate(date);

            SeekerController seekerController = new SeekerController();
            Seeker seeker = seekerController.findSeekerById("" + invObject.getInt("seeker_id"));

            RecruiterController recruiterController = new RecruiterController();
            Recruiter recruiter = recruiterController.findRecruiterById("" + invObject.getInt("recruiter_id"));

            invitation.setSeeker(seeker);
            invitation.setRecruiter(recruiter);
            
            invitation.setDescription(invObject.getString("description"));
            
            invitations.add(invitation);
        }
        return invitations;

    }

    private boolean executeSql(String sql) {
        boolean result;
        SqlExecute statement = new SqlExecute();
        statement.setSql(sql);
        result = statement.execute();
        return result;
    }
}
