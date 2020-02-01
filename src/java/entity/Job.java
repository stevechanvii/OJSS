/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;

/**
 *
 * @author 41649
 */
public class Job {

    private int jobId;
    private String jobName;
    private String position;
    private String address;
    private int salary;
    private String description;
    private String compensation;
    private String jobType;
    private ArrayList<JobCategory> jobCategories;
    private String relKeywords;
    private Recruiter recruiter = new Recruiter();
    private boolean isAdvertise;

    public Job() {

    }

    public Job(int jobId,
            String jobName,
            String position,
            String address,
            int salary,
            String description,
            String compensation,
            String jobType,
            ArrayList<JobCategory> jobCategoroies,
            String relKeywords,
            boolean isAdvertise) {
        this.jobId = jobId;
        this.jobName = jobName;
        this.position = position;
        this.address = address;
        this.salary = salary;
        this.description = description;
        this.compensation = compensation;
        this.jobType = jobType;
        this.jobCategories = jobCategories;
        this.relKeywords = relKeywords;
    }

    /**
     * @return the jobId
     */
    public int getJobId() {
        return jobId;
    }

    /**
     * @param jobId the jobId to set
     */
    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    /**
     * @return the jobName
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * @param jobName the jobName to set
     */
    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    /**
     * @return the position
     */
    public String getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the salary
     */
    public int getSalary() {
        return salary;
    }

    /**
     * @param salary the salary to set
     */
    public void setSalary(int salary) {
        this.salary = salary;
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
    public void setDescription(String descrition) {
        this.description = descrition;
    }

    /**
     * @return the compensation
     */
    public String getCompensation() {
        return compensation;
    }

    /**
     * @param compensation the compensation to set
     */
    public void setCompensation(String compensation) {
        this.compensation = compensation;
    }

    /**
     * @return the jobCategories
     */
    public ArrayList<JobCategory> getJobCategories() {
        return jobCategories;
    }

    /**
     * @param jobCategories the jobCategories to set
     */
    public void setJobCategories(ArrayList<JobCategory> jobCategories) {
        this.jobCategories = jobCategories;
    }

    /**
     * @return the relKeywords
     */
    public String getRelKeywords() {
        return relKeywords;
    }

    /**
     * @param relKeywords the relKeywords to set
     */
    public void setRelKeywords(String relKeywords) {
        this.relKeywords = relKeywords;
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
     * @return the isAdvertise
     */
    public boolean isIsAdvertise() {
        return isAdvertise;
    }

    /**
     * @param isAdvertise the isAdvertise to set
     */
    public void setIsAdvertise(boolean isAdvertise) {
        this.isAdvertise = isAdvertise;
    }

    /**
     * @return the jobType
     */
    public String getJobType() {
        return jobType;
    }

    /**
     * @param jobType the jobType to set
     */
    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

}
