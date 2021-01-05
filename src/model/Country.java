/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 * Represents a country.
 *
 * @author Daniel
 */
public class Country {
    /**Initializes country ID. */
    private int countryID;
    /**Initializes country. */
    private String country;

    /**
     * Creates a country.
     *
     * @param countryID sets country id with a int
     * @param country sets country with a String
     */
    public Country(int countryID, String country) {
        this.countryID = countryID;
        this.country = country;
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
     * Sets Country.
     *
     * @param country country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets Country ID.
     *
     * @return current country id as int
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * Gets Country.
     *
     * @return current country as String
     */
    public String getCountry() {
        return country;
    }

    /**
     * Formats String.
     *
     * @return country as String
     */
    @Override
    public String toString() {
        return country;
    }
}
