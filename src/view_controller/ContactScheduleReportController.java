/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view_controller;

import static dao.AppointmentDaoImpl.getAllAppointments;
import static dao.ContactDaoImpl.getAllContacts;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import model.Appointment;
import java.sql.Timestamp;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Contact;

/**
 * FXML Controller for the ContactScheduleReport screen. This class contains methods to display the schedule for a specified contact.
 *
 * @author Daniel
 */
public class ContactScheduleReportController implements Initializable {

    @FXML
    private ComboBox<Contact> contactCombo;
    @FXML
    private TableView<Appointment> contactApptTable;
    @FXML
    private TableColumn<Appointment, Integer> apptIDCol;
    @FXML
    private TableColumn<Appointment, Integer> custIDCol;
    @FXML
    private TableColumn<Appointment, String> titleCol;
    @FXML
    private TableColumn<Appointment, String> descriptionCol;
    @FXML
    private TableColumn<Appointment, String> typeCol;
    @FXML
    private TableColumn<Appointment, Timestamp> startCol;
    @FXML
    private TableColumn<Appointment, Timestamp> endCol;
    
    /**Takes the user back to the Reports screen.
     * 
     * @param event clicking the back arrow
     * @throws IOException 
     */
    @FXML
    private void goBack(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/view_controller/Reports.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /**Sets appointment data associated with selected contact.
     * 
     * @param event ComboBox selection
     * @throws SQLException 
     */
    @FXML
    private void pullTableData(ActionEvent event) throws SQLException {
        int selectedContact = contactCombo.getValue().getContactID();
        ObservableList<Appointment> assocdAppts = getAllAppointments().filtered(a -> {
            return a.getContactID() == selectedContact;
        });
        contactApptTable.setItems(assocdAppts);
        apptIDCol.setCellValueFactory(new PropertyValueFactory<>("apptID"));
        custIDCol.setCellValueFactory(new PropertyValueFactory<>("custID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("apptDescription"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("apptStart"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("apptEnd"));
    }

    /**
     * Initializes the controller class. Sets contact ComboBox.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            contactCombo.setItems(getAllContacts());
        } catch (SQLException ex) {
            System.out.println("Problem getting contacts." + ex.getMessage());
        }
    }

}
