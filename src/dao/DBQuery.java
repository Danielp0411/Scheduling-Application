/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.DBConnection.conn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class is used to query the database.
 *
 * @author Daniel
 */
public class DBQuery {

    private static String query;
    private static Statement stmt;
    private static ResultSet rs;

    /**
     * Creates an SQL query.
     *
     * @param q query as String
     */
    public static void makeQuery(String q) {
        query = q;
        try {
            stmt = conn.createStatement();
            // determine query execution
            if (query.toLowerCase().startsWith("select")) {
                rs = stmt.executeQuery(q);
            }
            if (query.toLowerCase().startsWith("delete") || query.toLowerCase().startsWith("insert") || query.toLowerCase().startsWith("update")) {
                stmt.executeUpdate(q);
            }

        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    /**
     * Returns the results of the SQL query.
     *
     * @return rs as ResultSet
     */
    public static ResultSet getResult() {
        return rs;
    }
}
