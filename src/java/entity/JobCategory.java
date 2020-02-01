/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author 41649
 */
public class JobCategory {
    private int categoryId;
    private String categoryName;
    private Recruiter recruiter = new Recruiter();
    
    public JobCategory(){
    
    }
    
    public JobCategory(int categoryId, String categoryName){
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
    
    /**
     * @return the categoryId
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * @return the categoryName
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * @return the recruiter
     */
    public Recruiter getRecruiter() {
        return recruiter;
    }

    /**
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * @param categoryName the categoryName to set
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * @param recruiter the recruiter to set
     */
    public void setRecruiter(Recruiter recruiter) {
        this.recruiter = recruiter;
    }
    
    
}
