/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Represents a first level division.
 *
 * @author Daniel
 */
public class FLD {
    /**Initializes division ID. */
    private int divisionID;
    /**Initializes division. */
    private String division;
    /**Initializes country ID. */
    private int countryID;

    /**
     * Creates a first level division.
     *
     * @param divisionID sets division id with a int
     * @param division sets division with a String
     * @param countryID sets country id with a int
     */
    public FLD(int divisionID, String division, int countryID) {
        this.divisionID = divisionID;
        this.division = division;
        this.countryID = countryID;
    }

    /**
     * Sets Division ID.
     *
     * @param divisionID division id to set
     */
    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }

    /**
     * Sets Division.
     *
     * @param division division to set
     */
    public void setDivision(String division) {
        this.division = division;
    }

    /**
     * Sets Country ID.
     *
     * @param countryID country id to set
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    /**
     * Gets Division ID.
     *
     * @return division id as int
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * Gets Division.
     *
     * @return division as String
     */
    public String getDivision() {
        return division;
    }

    /**
     * Gets Country ID.
     *
     * @return country id as int
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * Formats String.
     *
     * @return division as String
     */
    @Override
    public String toString() {
        return division;
    }
}
