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
public class Recruiter extends Account{
    private String companyName;
    private String description;
    private boolean isActivate;
    
    public Recruiter(){
    
    }
    
    public Recruiter(int Id, String passwd, String userName, String phone, String address, String email, String companyName, String description, boolean isActivate){
        super(Id, passwd, phone, address, email);
        this.companyName = companyName;
        this.description = description;
        this.isActivate = isActivate;
    }
    
    /**
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    /**
     * @return the isActivate
     */
    public boolean isIsActivate() {
        return isActivate;
    }

    /**
     * @param isActivate the isActivate to set
     */
    public void setIsActivate(boolean isActivate) {
        this.isActivate = isActivate;
    }
    
}
