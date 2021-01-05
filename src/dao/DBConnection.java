/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class is used to connect to the database.
 *
 * @author Daniel
 */
public class DBConnection {

    static Connection conn;

    /**
     * Starts the database connection.
     */
    public static void startConnection() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://wgudb.ucertify.com/WJ06ccu", "U06ccu", "53688726515");
            System.out.println("Connected to Database");
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    /**
     * Gets the database connection.
     *
     * @return conn as Connection
     */
    public static Connection getConnection() {
        return conn;
    }

}
