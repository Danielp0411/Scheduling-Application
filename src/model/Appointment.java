/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;

/**
 * Represents an appointment.
 *
 * @author Daniel
 */
public class Appointment {
    /**Initializes appointment ID. */
    private int apptID;
    /**Initializes appointment title. */
    private String apptTitle;
    /**Initializes appointment description. */
    private String apptDescription;
    /**Initializes appointment location. */
    private String apptLocation;
    /**Initializes appointment type. */
    private String apptType;
    /**Initializes appointment start. */
    private Timestamp apptStart;
    /**Initializes appointment end. */
    private Timestamp apptEnd;
    /**Initializes customer ID. */
    private int custID;
    /**Initializes user ID. */
    private int userID;
    /**Initializes contact ID. */
    private int contactID;
    /**Initializes appointment type count. */
    private int apptTypeCount;

    /**
     * Creates an appointment by type and how many of that type occur.
     *
     * @param apptType sets appointment type with a String
     * @param apptTypeCount sets appointment type count with a int
     */
    public Appointment(String apptType, int apptTypeCount) {
        this.apptType = apptType;
        this.apptTypeCount = apptTypeCount;
    }

    /**
     * Creates an appointment.
     *
     * @param apptID sets appointment id with a int
     * @param apptTitle sets appointment title with a String
     * @param apptDescription sets appointment description with a String
     * @param apptLocation sets appointment location with a String
     * @param apptType sets appointment type with a String
     * @param apptStart sets appointment start with a Timestamp
     * @param apptEnd sets appointment end with a Timestamp
     * @param custID sets customer id with a int
     * @param userID sets user id with a int
     * @param contactID sets contact id with a int
     */
    public Appointment(int apptID, String apptTitle, String apptDescription, String apptLocation, String apptType, Timestamp apptStart, Timestamp apptEnd, int custID, int userID, int contactID) {
        this.apptID = apptID;
        this.apptTitle = apptTitle;
        this.apptDescription = apptDescription;
        this.apptLocation = apptLocation;
        this.apptType = apptType;
        this.apptStart = apptStart;
        this.apptEnd = apptEnd;
        this.custID = custID;
        this.userID = userID;
        this.contactID = contactID;
    }

    /**
     * Sets the appointment ID.
     *
     * @param apptID appointment id to set
     */
    public void setApptID(int apptID) {
        this.apptID = apptID;
    }

    /**
     * Sets appointment title.
     *
     * @param apptTitle appointment title to set
     */
    public void setApptTitle(String apptTitle) {
        this.apptTitle = apptTitle;
    }

    /**
     * Sets appointment Description.
     *
     * @param apptDescription appointment description to set
     */
    public void setApptDescription(String apptDescription) {
        this.apptDescription = apptDescription;
    }

    /**
     * Sets appointment Location.
     *
     * @param apptLocation appointment location to set
     */
    public void setApptLocation(String apptLocation) {
        this.apptLocation = apptLocation;
    }

    /**
     * Sets appointment type.
     *
     * @param apptType appointment type to set
     */
    public void setApptType(String apptType) {
        this.apptType = apptType;
    }

    /**
     * Sets appointment start.
     *
     * @param apptStart appointment start to set
     */
    public void setApptStart(Timestamp apptStart) {
        this.apptStart = apptStart;
    }

    /**
     * Sets appointment end.
     *
     * @param apptEnd appointment end to set
     */
    public void setApptEnd(Timestamp apptEnd) {
        this.apptEnd = apptEnd;
    }

    /**
     * Sets customer ID.
     *
     * @param custID customer id to set
     */
    public void setCustID(int custID) {
        this.custID = custID;
    }

    /**
     * Sets user ID.
     *
     * @param userID user id to set
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Sets contact ID.
     *
     * @param contactID contact id to set
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /**
     * Sets Appointment Type Count.
     *
     * @param apptTypeCount appointment type count to set
     */
    public void setApptTypeCount(int apptTypeCount) {
        this.apptTypeCount = apptTypeCount;
    }

    /**
     * Gets Appointment ID.
     *
     * @return current appointment id as int
     */
    public int getApptID() {
        return apptID;
    }

    /**
     * Gets Appointment Title.
     *
     * @return current appointment title as String
     */
    public String getApptTitle() {
        return apptTitle;
    }

    /**
     * Gets Appointment Description.
     *
     * @return current appointment description as String
     */
    public String getApptDescription() {
        return apptDescription;
    }

    /**
     * Gets Appointment Location.
     *
     * @return current appointment location as String
     */
    public String getApptLocation() {
        return apptLocation;
    }

    /**
     * Gets Appointment Type.
     *
     * @return current appointment type as String
     */
    public String getApptType() {
        return apptType;
    }

    /**
     * Gets Appointment Start.
     *
     * @return current appointment start as Timestamp
     */
    public Timestamp getApptStart() {
        return apptStart;
    }

    /**
     * Gets Appointment End.
     *
     * @return current appointment end as Timestamp
     */
    public Timestamp getApptEnd() {
        return apptEnd;
    }

    /**
     * Gets Customer ID.
     *
     * @return current customer id as int
     */
    public int getCustID() {
        return custID;
    }

    /**
     * Gets User ID.
     *
     * @return current user id as int
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Gets Contact ID.
     *
     * @return current contact id as int
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * Gets Appointment Type Count.
     *
     * @return current appointment type count as int
     */
    public int getApptTypeCount() {
        return apptTypeCount;
    }

}
