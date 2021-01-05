/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view_controller;

import static dao.AppointmentDaoImpl.getAllAppointments;
import static dao.CustomerDaoImpl.getAllCustomers;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customer;
import static dao.CustomerDaoImpl.deleteCustomer;
import javafx.collections.ObservableList;
import model.Appointment;
import static utils.Messages.customerHandler;

/**
 * FXML Controller for the Customer screen. This class contains methods to display a table of all customers and buttons to go to other screens.
 *
 * @author Daniel
 */
public class CustomerController implements Initializable {

    @FXML
    private TableView<Customer> custTableView;
    @FXML
    private TableColumn<Customer, Integer> idCol;
    @FXML
    private TableColumn<Customer, String> nameCol;
    @FXML
    private TableColumn<Customer, String> addressCol;
    @FXML
    private TableColumn<Customer, String> FLDCol;
    @FXML
    private TableColumn<Customer, String> postalCol;
    @FXML
    private TableColumn<Customer, String> phoneCol;

    private static Customer selectedCustomer = null;

    public static Customer getSelectedCustomer() {
        return selectedCustomer;
    }
    /**Takes the user to the CustomerAdd screen.
     * 
     * @param event clicking button (with image)
     * @throws IOException 
     */
    @FXML
    private void addCustomer(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/view_controller/CustomerAdd.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /**Validates a customer has been selected and deletes the selected customer from the database.
     * If no customer has been selected, user gets an error message.
     * 
     * @param event clicking button (with image)
     * @throws SQLException 
     */
    @FXML
    private void delCust(ActionEvent event) throws SQLException {
        selectedCustomer = custTableView.getSelectionModel().getSelectedItem();

        if (selectedCustomer == null) {
            customerHandler(1);
        } else {
            ObservableList<Appointment> custAppointments = getAllAppointments().filtered(a -> {
                return a.getCustID() == selectedCustomer.getCustID();
            });
            if (custAppointments.isEmpty()) {
                deleteCustomer(selectedCustomer.getCustID());
                custTableView.setItems(getAllCustomers());
                customerHandler(3);
            } else {
                customerHandler(2);
            }
        }
    }
    /**Validates a customer has been selected and takes the user to the CustomerUpdate screen.
     * If no customer has been selected, user gets an error message.
     * 
     * @param event clicking button (with image)
     * @throws IOException 
     */
    @FXML
    private void updtCustomer(ActionEvent event) throws IOException {
        selectedCustomer = custTableView.getSelectionModel().getSelectedItem();

        if (selectedCustomer == null) {
            customerHandler(4);
        } else {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(getClass().getResource("/view_controller/CustomerUpdate.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }
    /**Takes the user back to the Main screen.
     * 
     * @param event clicking the back arrow
     * @throws IOException 
     */
    @FXML
    private void goBack(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/view_controller/Main.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Initializes the controller class. Sets table with customer data.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            custTableView.setItems(getAllCustomers());
            idCol.setCellValueFactory(new PropertyValueFactory<>("custID"));
            nameCol.setCellValueFactory(new PropertyValueFactory<>("custName"));
            addressCol.setCellValueFactory(new PropertyValueFactory<>("custAddress"));
            FLDCol.setCellValueFactory(new PropertyValueFactory<>("custDivision"));
            postalCol.setCellValueFactory(new PropertyValueFactory<>("custPostal"));
            phoneCol.setCellValueFactory(new PropertyValueFactory<>("custPhone"));
        } catch (SQLException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
