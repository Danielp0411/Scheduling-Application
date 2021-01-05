/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view_controller;

import static dao.CountryDaoImpl.getAllCountries;
import static dao.CustomerDaoImpl.updateCustomer;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Country;
import model.Customer;
import model.FLD;
import static view_controller.CustomerController.getSelectedCustomer;
import static utils.Messages.customerHandler;

/**
 * FXML Controller for the CustomerUpdate screen. This class contains methods for input validation and updating a customer in the database.
 *
 * @author Daniel
 */
public class CustomerUpdateController implements Initializable {

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
    @FXML
    private TableView<Customer> tableView;
    @FXML
    private TableColumn<Customer, Integer> idCol;
    @FXML
    private TableColumn<Customer, String> nameCol;
    @FXML
    private TableColumn<Customer, String> addressCol;
    @FXML
    private TableColumn<Customer, Integer> FLDCol;
    @FXML
    private TableColumn<Customer, String> postalCol;
    @FXML
    private TableColumn<Customer, String> phoneCol;

    /**
     * Performs input validation and updates a customer.
     *
     * @param event
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
            customerHandler(4);
        } else if (address.isEmpty()) {
            customerHandler(5);
        } else if (custCountryCombo.getSelectionModel().getSelectedItem() == null) {
            customerHandler(6);
        } else if (custFLDCombo.getSelectionModel().getSelectedItem() == null) {
            customerHandler(7);
        } else if (postal.isEmpty()) {
            customerHandler(8);
        } else if (phone.isEmpty()) {
            customerHandler(9);
        } else {
            int divID = this.custFLDCombo.getSelectionModel().getSelectedItem().getDivisionID();
            Customer c = new Customer(id, name, address, postal, phone, divID);
            updateCustomer(c);
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
     * Sets associated first level division data in ComboBox.
     * Lambda expression is used to get all first level divisions where the countryID matches that of the selected country.
     * Using a lambda expression here made the code easier to read and is simpler than running another SQL query or for loop to get the correct list.
     *
     * @param event country ComboBox selection
     * @throws SQLException
     */
    @FXML
    private void pullFLD(ActionEvent event) throws SQLException {
        ObservableList<FLD> FLDsInCountry = getAllFLDs().filtered(f -> {
            return f.getCountryID() == custCountryCombo.getSelectionModel().getSelectedItem().getCountryID();
        });
        custFLDCombo.setValue(null);
        custFLDCombo.setItems(FLDsInCountry);
    }

    /**
     * Initializes the controller class. Sets all information from the selected customer into their respective text fields and ComboBoxes.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Customer selectedCustomer = getSelectedCustomer();
            custID.setText(Integer.toString(selectedCustomer.getCustID()));
            custName.setText(selectedCustomer.getCustName());
            custAddress.setText(selectedCustomer.getCustAddress());
            custPostal.setText(selectedCustomer.getCustPostal());
            custPhone.setText(selectedCustomer.getCustPhone());

            ObservableList<FLD> FLDs = getAllFLDs().filtered(f -> {
                return f.getDivisionID() == selectedCustomer.getCustDivID();
            });
            custFLDCombo.setValue(FLDs.get(0));

            ObservableList<Country> countryOfFLD = getAllCountries().filtered(c -> {
                return c.getCountryID() == custFLDCombo.getSelectionModel().getSelectedItem().getCountryID();
            });
            custCountryCombo.setValue(countryOfFLD.get(0));
            custCountryCombo.setItems(getAllCountries());

            ObservableList<FLD> FLDsInCountry = getAllFLDs().filtered(f -> {
                return f.getCountryID() == custCountryCombo.getSelectionModel().getSelectedItem().getCountryID();
            });
            custFLDCombo.setItems(FLDsInCountry);

        } catch (SQLException ex) {
            Logger.getLogger(CustomerUpdateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
