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
import model.Contact;

/**
 * This class is used to retrieve contact data from the database.
 *
 * @author Daniel
 */
public class ContactDaoImpl {

    /**
     * Retrieves all contacts.
     *
     * @return all contact data as ObservableList
     * @throws SQLException
     */
    public static ObservableList<Contact> getAllContacts() throws SQLException {
        ObservableList<Contact> allContacts = FXCollections.observableArrayList();
        DBConnection.getConnection();
        String SQLStmt = "SELECT * FROM contacts";
        DBQuery.makeQuery(SQLStmt);
        ResultSet rs = DBQuery.getResult();
        while (rs.next()) {
            Contact c = new Contact(rs.getInt(1), rs.getString(2));
            allContacts.add(c);
        }
        return allContacts;
    }

}
