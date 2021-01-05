/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view_controller;

import static dao.CountryDaoImpl.getAllCountries;
import static dao.CustomerDaoImpl.addCustomer;
import static dao.FLDDaoImpl.getAllFLDs;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Customer;
import model.FLD;
import model.Country;
import static utils.Messages.customerHandler;

/**
 * FXML Controller for the CustomerAdd screen. This class contains methods for input validation and adding a customer to the database.
 *
 * @author Daniel
 */
public class CustomerAddController implements Initializable {

    @FXML
    private TextField custID;
    @FXML
    private TextField custName;
    @FXML
    private TextField custAddress;
    @FXML
    private ComboBox<Country> custCountryCombo;
    @FXML
    private ComboBox<FLD> custFLDCombo;
    @FXML
    private TextField custPostal;
    @FXML
    private TextField custPhone;
    @FXML
    private Button submitButton;

    /**
     * Sets associated first level division data in ComboBox.
     * Lambda expression is used to get all first level divisions where the countryID matches that of the selected country.
     * Using a lambda expression here made the code easier to read and is simpler than running another SQL query or for loop to get the correct list.
     *
     * @param event country ComboBox selection
     * @throws SQLException
     */
    @FXML
    private void pullFLDs(ActionEvent event) throws SQLException {
        custFLDCombo.setPromptText(null);
        ObservableList<FLD> FLDsInCountry = getAllFLDs().filtered(f -> {
            return f.getCountryID() == custCountryCombo.getSelectionModel().getSelectedItem().getCountryID();
        });
        custFLDCombo.setItems(FLDsInCountry);
    }

    /**
     * Performs input validation and creates a new customer.
     *
     * @param event clicking the "submit" button
     * @throws IOException
     */
    @FXML
    private void submit(ActionEvent event) throws IOException {
        int id = Integer.parseInt(this.custID.getText());
        String name = this.custName.getText();
        String address = this.custAddress.getText();
        String postal = this.custPostal.getText();
        String phone = this.custPhone.getText();

        if (name.isEmpty()) {
            customerHandler(5);
        } else if (address.isEmpty()) {
            customerHandler(6);
        } else if (custCountryCombo.getSelectionModel().isEmpty()) {
            customerHandler(7);
        } else if (custFLDCombo.getSelectionModel().isEmpty()) {
            customerHandler(8);
        } else if (postal.isEmpty()) {
            customerHandler(9);
        } else if (phone.isEmpty()) {
            customerHandler(10);
        } else {
            int divID = this.custFLDCombo.getSelectionModel().getSelectedItem().getDivisionID();
            Customer c = new Customer(id, name, address, postal, phone, divID);
            addCustomer(c);
            goBack(event);
        }
    }

    /**
     * Takes the user back to the Customer screen.
     *
     * @param event clicking the back arrow
     * @throws IOException
     */
    @FXML
    private void goBack(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/view_controller/Customer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    /**
     * Initializes the controller class. Sets customer ID and country ComboBox.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            custID.setText(Integer.toString(dao.CustomerDaoImpl.custIDGen()));
            custCountryCombo.setItems(getAllCountries());
        } catch (SQLException ex) {
            Logger.getLogger(CustomerAddController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
