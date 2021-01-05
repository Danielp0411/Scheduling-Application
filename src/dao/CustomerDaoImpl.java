/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import static dao.CustomerDaoImpl.getAllCustomers;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;

/**
 * This class is used to create, retrieve, update, and delete customer data from the database.
 *
 * @author Daniel
 */
public class CustomerDaoImpl {

    /**
     * Retrieves all customers.
     *
     * @return all customer data and associated division as ObservableList
     * @throws SQLException
     */
    public static ObservableList<Customer> getAllCustomers() throws SQLException {
        ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
        DBConnection.getConnection();
        String SQLStmt = "SELECT Customer_ID, Customer_Name, Address, Division, Postal_Code, Phone, customers.Division_ID "
                + "FROM customers, first_level_divisions "
                + "WHERE customers.Division_ID = first_level_divisions.Division_ID";
        DBQuery.makeQuery(SQLStmt);
        ResultSet rs = DBQuery.getResult();
        while (rs.next()) {
            Customer c = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
            allCustomers.add(c);
        }
        return allCustomers;
    }

    /**
     * Creates a customer using user-entered information.
     *
     * @param c customer to create
     */
    public static void addCustomer(Customer c) {
        DBConnection.getConnection();
        String SQLStmt = "INSERT INTO customers (Customer_ID, Customer_Name, Address, Postal_Code, Phone, Division_ID) "
                + "VALUES ('" + c.getCustID() + "', '" + c.getCustName() + "', '" + c.getCustAddress() + "', '"
                + c.getCustPostal() + "', '" + c.getCustPhone() + "', '" + c.getCustDivID() + "')";
        DBQuery.makeQuery(SQLStmt);
        ResultSet rs = DBQuery.getResult();
    }

    /**
     * Deletes a customer.
     *
     * @param customerID customer id to delete
     */
    public static void deleteCustomer(int customerID) {
        DBConnection.getConnection();
        String SQLStmt = "DELETE FROM customers WHERE Customer_ID = '" + customerID + "'";
        DBQuery.makeQuery(SQLStmt);
        ResultSet rs = DBQuery.getResult();
    }

    /**
     * Updates a customer using user-entered information.
     *
     * @param c customer to update
     */
    public static void updateCustomer(Customer c) {
        DBConnection.getConnection();
        String SQLStmt = "UPDATE customers SET Customer_Name = '" + c.getCustName() + "', Address = '" + c.getCustAddress()
                + "', Postal_Code = '" + c.getCustPostal() + "', Phone = '" + c.getCustPhone() + "', Division_ID = '"
                + c.getCustDivID() + "' WHERE Customer_ID = '" + c.getCustID() + "'";

        DBQuery.makeQuery(SQLStmt);
        ResultSet rs = DBQuery.getResult();
    }

    /**
     * Generates a Customer ID.
     *
     * @return customer id as int
     * @throws SQLException
     */
    public static int custIDGen() throws SQLException {
        int e = 0;
        for (int i = 0; i < getAllCustomers().size(); i++) {
            e = getAllCustomers().get(i).getCustID();
        }
        return e + 1;
    }

}
