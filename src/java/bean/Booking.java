/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;

/**
 *
 * @author USER
 */
public class Booking implements Serializable{
    
    private int id;
    private String uniqueID;
    private String bookingDate;
    private String scheduleDate;
    private String memberNo;
    private String amountBefore;
    private String amountAfter;
    private String receipt;
    private String status;
    private String couponCode;
    private String courtCode[];
    private String reason;

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
     * @return the uniqueID
     */
    public String getUniqueID() {
        return uniqueID;
    }

    /**
     * @param uniqueID the uniqueID to set
     */
    public void setUniqueID(String uniqueID) {
        this.uniqueID = uniqueID;
    }

    /**
     * @return the bookingDate
     */
    public String getBookingDate() {
        return bookingDate;
    }

    /**
     * @param bookingDate the bookingDate to set
     */
    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    /**
     * @return the scheduleDate
     */
    public String getScheduleDate() {
        return scheduleDate;
    }

    /**
     * @param scheduleDate the scheduleDate to set
     */
    public void setScheduleDate(String scheduleDate) {
        this.scheduleDate = scheduleDate;
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
     * @return the amountAfter
     */
    public String getAmountAfter() {
        return amountAfter;
    }

    /**
     * @param amountAfter the amountAfter to set
     */
    public void setAmountAfter(String amountAfter) {
        this.amountAfter = amountAfter;
    }

    /**
     * @return the receipt
     */
    public String getReceipt() {
        return receipt;
    }

    /**
     * @param receipt the receipt to set
     */
    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the couponCode
     */
    public String getCouponCode() {
        return couponCode;
    }

    /**
     * @param couponCode the couponCode to set
     */
    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    /**
     * @return the courtCode
     */
    public String[] getCourtCode() {
        return courtCode;
    }

    /**
     * @param courtCode the courtCode to set
     */
    public void setCourtCode(String[] courtCode) {
        this.courtCode = courtCode;
    }

    /**
     * @return the amountBefore
     */
    public String getAmountBefore() {
        return amountBefore;
    }

    /**
     * @param amountBefore the amountBefore to set
     */
    public void setAmountBefore(String amountBefore) {
        this.amountBefore = amountBefore;
    }

    /**
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason the reason to set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }
    
}
