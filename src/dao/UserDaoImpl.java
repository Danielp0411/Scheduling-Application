/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.DBConnection.startConnection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class is used retrieve user data from the database.
 *
 * @author Daniel
 */
public class UserDaoImpl {

    /**
     * Searches for user in the database.
     *
     * @param userName username entered by user
     * @param password password entered by user
     * @return true (if found), false (if not found) as Boolean
     * @throws SQLException
     */
    public static boolean findUser(String userName, String password) throws SQLException {

        boolean found = false;

        startConnection();
        String SQLStmt = "SELECT * FROM users WHERE User_name = '" + userName + "' AND password = '" + password + "'";
        DBQuery.makeQuery(SQLStmt);
        ResultSet rs = DBQuery.getResult();
        if (rs.next()) {
            found = true;
        }
        return found;
    }
}
