package utils;

import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**This class is used handle application errors and alerts.
 * 
 * @author Daniel
 */
public class Messages {

    static ResourceBundle rb = ResourceBundle.getBundle("utils/Lang", Locale.getDefault());
    /**This method is called for login errors.
     * 
     * @param i requested case number
     * @return requested error
     */
    public static int loginError(int i) {

        switch (i) {

            case 1:
                JOptionPane.showMessageDialog(null, rb.getString("usernameEmpty"), "ERROR", 1);
                break;
            case 2:
                JOptionPane.showMessageDialog(null, rb.getString("passwordEmpty"), "ERROR", 1);
                break;
            case 3:
                JOptionPane.showMessageDialog(null, rb.getString("loginError"), "ERROR", 1);
                break;
        }
        return 0;
    }
    /**This method is called for customer errors/alerts.
     * 
     * @param i requested case number
     * @return requested error/alert
     */
    public static int customerHandler(int i) {
        switch (i) {

            case 1:
                JOptionPane.showMessageDialog(null, "Please select a customer to delete.", "ERROR", 1);
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "Customers appointment(s) must be deleted first.", "ERROR", 1);
                break;
            case 3:
                JOptionPane.showMessageDialog(null, "Customer Successfully Deleted.", "Success", 2);
                break;
            case 4:
                JOptionPane.showMessageDialog(null, "Please select a customer to update.", "ERROR", 1);
                break;
            case 5:
                JOptionPane.showMessageDialog(null, "Name cannot be empty.", "ERROR", 1);
                break;
            case 6:
                JOptionPane.showMessageDialog(null, "Address cannot be empty.", "ERROR", 1);
                break;
            case 7:
                JOptionPane.showMessageDialog(null, "Country cannot be empty.", "ERROR", 1);
                break;
            case 8:
                JOptionPane.showMessageDialog(null, "State/Province cannot be empty.", "ERROR", 1);
                break;
            case 9:
                JOptionPane.showMessageDialog(null, "Postal Code cannot be empty.", "ERROR", 1);
                break;
            case 10:
                JOptionPane.showMessageDialog(null, "Phone Number cannot be empty.", "ERROR", 1);
                break;
        }
        return 0;
    }
    /**This method is called for appointment errors/alerts.
     * 
     * @param i requested case number
     * @return requested error/alert
     */
    public static int AppointmentHandler(int i) {
        switch (i) {

            case 1:
                JOptionPane.showMessageDialog(null, "Please select an appointment to delete.", "ERROR", 1);
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "Please select an appointment to update.", "ERROR", 1);
                break;
            case 3:
                JOptionPane.showMessageDialog(null, "Title cannot be empty.", "ERROR", 1);
                break;
            case 4:
                JOptionPane.showMessageDialog(null, "Description cannot be empty.", "ERROR", 1);
                break;
            case 5:
                JOptionPane.showMessageDialog(null, "Location cannot be empty.", "ERROR", 1);
                break;
            case 6:
                JOptionPane.showMessageDialog(null, "Contact cannot be empty.", "ERROR", 1);
                break;
            case 7:
                JOptionPane.showMessageDialog(null, "Type cannot be empty.", "ERROR", 1);
                break;
            case 8:
                JOptionPane.showMessageDialog(null, "Start Date cannot be empty.", "ERROR", 1);
                break;
            case 9:
                JOptionPane.showMessageDialog(null, "End Date cannot be empty.", "ERROR", 1);
                break;
            case 10:
                JOptionPane.showMessageDialog(null, "End Date cannot be sooner than Start Date.", "ERROR", 1);
                break;
            case 11:
                JOptionPane.showMessageDialog(null, "Customer ID cannot be empty.", "ERROR", 1);
                break;
            case 12:
                JOptionPane.showMessageDialog(null, "User ID cannot be empty.", "ERROR", 1);
                break;
            case 13:
                JOptionPane.showMessageDialog(null, "End Time cannot be sooner than or equal to Start Time.", "ERROR", 1);
                break;
            case 14:
                JOptionPane.showMessageDialog(null, "The appointment you are trying to schedule is outside of office hours. Please schedule between 8:00 - 22:00 EST.", "ERROR", 1);
                break;
            case 15:
                JOptionPane.showMessageDialog(null, "New appointments cannot overlap with previously scheduled appointments.", "ERROR", 1);
                break;
            case 16:
                JOptionPane.showMessageDialog(null, "Please enter a valid ID (Number) in the Customer ID/User ID text fields.", "ERROR", 1);
                break;
        }
        return 0;
    }

}
