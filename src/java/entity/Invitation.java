/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Timestamp;

/**
 *
 * @author 41649
 */
public class Invitation {
    private int invId;
    private Recruiter recruiter;
    private Seeker seeker;
    private Timestamp createDate;
    private String description;

    /**
     * @return the invId
     */
    public int getInvId() {
        return invId;
    }

    /**
     * @param invId the invId to set
     */
    public void setInvId(int invId) {
        this.invId = invId;
    }

    /**
     * @return the recruiter
     */
    public Recruiter getRecruiter() {
        return recruiter;
    }

    /**
     * @param recruiter the recruiter to set
     */
    public void setRecruiter(Recruiter recruiter) {
        this.recruiter = recruiter;
    }

    /**
     * @return the seeker
     */
    public Seeker getSeeker() {
        return seeker;
    }

    /**
     * @param seeker the seeker to set
     */
    public void setSeeker(Seeker seeker) {
        this.seeker = seeker;
    }

    /**
     * @return the createDate
     */
    public Timestamp getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
