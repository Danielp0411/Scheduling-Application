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
import model.FLD;

/**
 * This class is used to retrieve first level division data from the database.
 *
 * @author Daniel
 */
public class FLDDaoImpl {

    /**
     * Retrieves all first level divisions.
     *
     * @return all first level divisions as ObservableList
     * @throws SQLException
     */
    public static ObservableList<FLD> getAllFLDs() throws SQLException {
        ObservableList<FLD> allFLDs = FXCollections.observableArrayList();
        DBConnection.getConnection();
        String SQLStmt = "SELECT * FROM first_level_divisions";
        DBQuery.makeQuery(SQLStmt);
        ResultSet rs = DBQuery.getResult();
        while (rs.next()) {
            FLD FLD = new FLD(rs.getInt(1), rs.getString(2), rs.getInt(7));
            allFLDs.add(FLD);
        }
        return allFLDs;
    }

}
