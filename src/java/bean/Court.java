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
public class Court implements Serializable {
    
    private int courtNumber;
    private String timeSlot;

    /**
     * @return the courtNumber
     */
    public int getCourtNumber() {
        return courtNumber;
    }

    /**
     * @param courtNumber the courtNumber to set
     */
    public void setCourtNumber(int courtNumber) {
        this.courtNumber = courtNumber;
    }

    /**
     * @return the timeSlot
     */
    public String getTimeSlot() {
        return timeSlot;
    }

    /**
     * @param timeSlot the timeSlot to set
     */
    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }
    
}
