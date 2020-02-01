/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author 41649
 */
public class Application {
    private int appId;
    private Job job;
    private Seeker seeker;
    private Timestamp date;

    /**
     * @return the app_id
     */
    public int getAppId() {
        return appId;
    }

    /**
     * @param app_id the app_id to set
     */
    public void setAppId(int appId) {
        this.appId = appId;
    }

    /**
     * @return the job
     */
    public Job getJob() {
        return job;
    }

    /**
     * @param job the job to set
     */
    public void setJob(Job job) {
        this.job = job;
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
     * @return the date
     */
    public Timestamp getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Timestamp date) {
        this.date = date;
    }
}
