/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Represents a customer.
 *
 * @author Daniel
 */
public class Customer {
    /**Initializes customer ID. */
    private int custID;
    /**Initializes customer name. */
    private String custName;
    /**Initializes customer address. */
    private String custAddress;
    /**Initializes customer division. */
    private String custDivision;
    /**Initializes customer postal. */
    private String custPostal;
    /**Initializes customer phone. */
    private String custPhone;
    /**Initializes customer division ID. */
    private int custDivID;

    /**
     * Creates a customer.
     *
     * @param custID sets customer id with a int
     * @param custName sets customer name with a String
     * @param custAddress sets customer address with a String
     * @param custPostal sets customer postal code with a String
     * @param custPhone sets customer phone number with a String
     * @param custDivID sets division id with a int
     */
    public Customer(int custID, String custName, String custAddress, String custPostal, String custPhone, int custDivID) {
        this.custID = custID;
        this.custName = custName;
        this.custAddress = custAddress;
        this.custPostal = custPostal;
        this.custPhone = custPhone;
        this.custDivID = custDivID;
    }
    /**Creates a customer with division.
     * 
     * @param custID sets customer id with a int
     * @param custName sets customer name with a String
     * @param custAddress sets customer address with a String
     * @param custDivision sets division with a String
     * @param custPostal sets customer postal code with a String
     * @param custPhone sets customer phone number with a String
     * @param custDivID sets division id with a int
     */
    public Customer(int custID, String custName, String custAddress, String custDivision, String custPostal, String custPhone, int custDivID) {
        this.custID = custID;
        this.custName = custName;
        this.custAddress = custAddress;
        this.custDivision = custDivision;
        this.custPostal = custPostal;
        this.custPhone = custPhone;
        this.custDivID = custDivID;
    }

    /**Sets Customer ID.
     * 
     * @param custID customer id to set
     */ 
    public void setCustID(int custID) {
        this.custID = custID;
    }
    /**Sets Customer Name.
     * 
     * @param custName customer name to set
     */
    public void setCustName(String custName) {
        this.custName = custName;
    }
    /**Sets Customer Address.
     * 
     * @param custAddress customer address to set
     */
    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }
    /**Sets Customer Division.
     * 
     * @param custDivision customer division to set
     */
    public void setCustDivision(String custDivision) {
        this.custDivision = custDivision;
    }
    /**Sets Customer Postal Code.
     * 
     * @param custPostal customer postal code to set
     */
    public void setCustPostal(String custPostal) {
        this.custPostal = custPostal;
    }
    /**Sets Customer Phone Number.
     * 
     * @param custPhone customer phone number to set
     */
    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }
    /**Sets Customer Division ID.
     * 
     * @param custDivID customer division id to set
     */
    public void setCustDivID(int custDivID) {
        this.custDivID = custDivID;
    }

    /**Gets Customer ID.
     * 
     * @return customer id as int
     */
    public int getCustID() {
        return custID;
    }
    /**Gets Customer Name.
     * 
     * @return customer name as String
     */
    public String getCustName() {
        return custName;
    }
    /**Gets Customer Address.
     * 
     * @return customer address as String
     */
    public String getCustAddress() {
        return custAddress;
    }
    /**Gets Customer Division.
     * 
     * @return customer division as String
     */
    public String getCustDivision() {
        return custDivision;
    }
    /**Gets Customer Postal Code.
     * 
     * @return customer postal code as String
     */
    public String getCustPostal() {
        return custPostal;
    }
    /**Gets Customer Phone Number.
     * 
     * @return customer phone number as String
     */
    public String getCustPhone() {
        return custPhone;
    }
    /**Gets Customer Division ID.
     * 
     * @return customer division id as int 
     */
    public int getCustDivID() {
        return custDivID;
    }

}
