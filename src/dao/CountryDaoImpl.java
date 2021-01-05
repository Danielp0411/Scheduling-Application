/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;

/**
 * This class is used to retrieve country data from the database.
 *
 * @author Daniel
 */
public class CountryDaoImpl {

    /**
     * Retrieves all countries.
     *
     * @return all country data as ObservableList
     * @throws SQLException
     */
    public static ObservableList<Country> getAllCountries() throws SQLException {
        ObservableList<Country> allCountries = FXCollections.observableArrayList();
        DBConnection.getConnection();
        String SQLStmt = "SELECT * FROM countries";
        DBQuery.makeQuery(SQLStmt);
        ResultSet rs = DBQuery.getResult();
        while (rs.next()) {
            Country c = new Country(rs.getInt(1), rs.getString(2));
            allCountries.add(c);
        }
        return allCountries;
    }

}
