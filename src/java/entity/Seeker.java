/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;

/**
 *
 * @author stevechanvii
 */
public class Seeker extends Account {

    private String firstName;
    private String lastName;
    private String education;
    private String description;
    private String skillset;
    private Date birthday;
    private boolean isActivate;

    public Seeker() {

    }

    public Seeker(int Id, String passwd, String phone, String address, String email, String firstName, String lastName, String education, String skillset, String description, Date birthday, boolean isActivate) {
        super(Id, passwd, phone, address, email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.education = education;
        this.description = description;
        this.skillset = skillset;
        this.birthday = birthday;
        this.isActivate = isActivate;
    }

    /**
     * @return the first_name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param first_name the first_name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the second_name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param second_name the second_name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the education
     */
    public String getEducation() {
        return education;
    }

    /**
     * @param education the education to set
     */
    public void setEducation(String education) {
        this.education = education;
    }

    public void setSkillset(String skillset) {
        this.skillset = skillset;
    }

    public String getSkillset() {
        return skillset;
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
     * @return the birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * @param birthday the birthday to set
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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
