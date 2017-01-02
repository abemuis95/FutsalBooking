/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.beans.*;
import java.io.Serializable;

/**
 *
 * @author USER
 */
public class Member implements Serializable {
    
    private int id;
    private String memberNo;
    private String name;
    private String NRIC;
    private String dateRegistered;
    private String birthDate;
    private String address;
    private String telNo;
    private String email;
    private String dateUpdate;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the memberNo
     */
    public String getMemberNo() {
        return memberNo;
    }

    /**
     * @param memberNo the memberNo to set
     */
    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the NRIC
     */
    public String getNRIC() {
        return NRIC;
    }

    /**
     * @param NRIC the NRIC to set
     */
    public void setNRIC(String NRIC) {
        this.NRIC = NRIC;
    }

    /**
     * @return the dateRegistered
     */
    public String getDateRegistered() {
        return dateRegistered;
    }

    /**
     * @param dateRegistered the dateRegistered to set
     */
    public void setDateRegistered(String dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    /**
     * @return the birthDate
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * @param birthDate the birthDate to set
     */
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
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
     * @return the telNo
     */
    public String getTelNo() {
        return telNo;
    }

    /**
     * @param telNo the telNo to set
     */
    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the dateUpdate
     */
    public String getDateUpdate() {
        return dateUpdate;
    }

    /**
     * @param dateUpdate the dateUpdate to set
     */
    public void setDateUpdate(String dateUpdate) {
        this.dateUpdate = dateUpdate;
    }
    
}
