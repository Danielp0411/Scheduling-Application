/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Represents a contact.
 *
 * @author Daniel
 */
public class Contact {
    /**Initializes contact ID. */
    private int contactID;
    /**Initializes contact name. */
    private String contactName;

    /**
     * Creates a contact.
     *
     * @param contactID sets contact id with a int
     * @param contactName sets contact name with a String
     */
    public Contact(int contactID, String contactName) {
        this.contactID = contactID;
        this.contactName = contactName;
    }

    /**
     * Sets Contact ID.
     *
     * @param contactID contact id to set
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /**
     * Sets Contact Name.
     *
     * @param contactName contact name to set
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
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
     * Gets Contact Name.
     *
     * @return current contact name as String
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * Formats String.
     *
     * @return contact name as String
     */
    @Override
    public String toString() {
        return contactName;
    }

}
