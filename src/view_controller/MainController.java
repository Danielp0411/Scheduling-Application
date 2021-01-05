/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view_controller;

import static dao.AppointmentDaoImpl.deleteAppointment;
import static dao.AppointmentDaoImpl.getAllAppointments;
import static dao.AppointmentDaoImpl.getAppointmentsByMonth;
import static dao.AppointmentDaoImpl.getAppointmentsByWeek;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Appointment;
import java.time.ZonedDateTime;
import javafx.scene.control.cell.PropertyValueFactory;
import static utils.Messages.AppointmentHandler;
import javax.swing.JOptionPane;

/**
 * FXML Controller for the Main screen. This class contains methods to displays a table of all appointments and buttons to go to other screens or take action.
 *
 * @author Daniel
 */
public class MainController implements Initializable {

    @FXML
    private TableView<Appointment> tableView;
    @FXML
    private TableColumn<Appointment, Integer> apptIDCol;
    @FXML
    private TableColumn<Appointment, Integer> custIDCol;
    @FXML
    private TableColumn<Appointment, String> titleCol;
    @FXML
    private TableColumn<Appointment, String> descriptionCol;
    @FXML
    private TableColumn<Appointment, String> locationCol;
    @FXML
    private TableColumn<Appointment, String> typeCol;
    @FXML
    private TableColumn<Appointment, ZonedDateTime> startCol;
    @FXML
    private TableColumn<Appointment, ZonedDateTime> endCol;
    @FXML
    private DatePicker datePicker;
    /**Initializes Id. */
    LocalDate ld = LocalDate.now();
    /**Initializes alreadyExecuted. */
    boolean alreadyExecuted = false;
    /**Initializes selectedAppointment. */
    private static Appointment selectedAppointment = null;

    /**
     * Returns selected appointment from the table.
     *
     * @return selectedAppointment as Appointment
     */
    public static Appointment getSelectedAppointment() {
        return selectedAppointment;
    }

    /**
     * Takes the user to the Customer screen.
     *
     * @param event clicking button (with image)
     * @throws IOException
     */
    @FXML
    private void customer(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/view_controller/Customer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Takes the user to the AppointmentAdd screen.
     *
     * @param event clicking button (with image)
     * @throws IOException
     */
    @FXML
    private void addAppt(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/view_controller/AppointmentAdd.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Validates an appointment has been selected and deletes the selected appointment from the database.
     * If no appointment has been selected, user gets an error message.
     *
     * @param event clicking button (with image)
     * @throws SQLException
     */
    @FXML
    private void delAppt(ActionEvent event) throws SQLException {
        selectedAppointment = tableView.getSelectionModel().getSelectedItem();

        if (selectedAppointment == null) {
            AppointmentHandler(1);
        } else {
            deleteAppointment(selectedAppointment.getApptID());
            tableView.setItems(getAllAppointments());
            JOptionPane.showMessageDialog(null, "Appointment ID: " + selectedAppointment.getApptID() + "\nType: "
                    + selectedAppointment.getApptType() + "\nSuccessfully deleted.", "Success", 2);
        }
    }

    /**
     * Validates an appointment has been selected and takes the user to the AppointmentUpdate screen.
     * If no appointment has been selected, user gets an error message.
     *
     * @param event clicking button (with image)
     * @throws IOException
     */
    @FXML
    private void updtAppt(ActionEvent event) throws IOException {
        selectedAppointment = tableView.getSelectionModel().getSelectedItem();

        if (selectedAppointment == null) {
            AppointmentHandler(2);
        } else {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = FXMLLoader.load(getClass().getResource("/view_controller/AppointmentUpdate.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    /**
     * Takes the user to the Reports screen.
     *
     * @param event clicking button (with image)
     * @throws IOException
     */
    @FXML
    private void reports(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/view_controller/Reports.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Sets table with all appointment data.
     *
     * @param event clicking button (with image)
     * @throws SQLException
     */
    @FXML
    private void viewAll(ActionEvent event) throws SQLException {
        tableView.setItems(getAllAppointments());
        setTable();
    }

    /**
     * Sets table with appointment data from the specified week. Default is current week.
     *
     * @param event clicking button (with image)
     * @throws SQLException
     */
    @FXML
    private void viewByWeek(ActionEvent event) throws SQLException {
        if (datePicker.getValue() == null) {
            tableView.setItems(getAppointmentsByWeek(ld));
        } else {
            tableView.setItems(getAppointmentsByWeek(datePicker.getValue()));
        }
        setTable();
    }

    /**
     * Sets table with appointment data from the specified month. Default is current month.
     *
     * @param event clicking button (with image)
     * @throws SQLException
     */
    @FXML
    private void viewByMonth(ActionEvent event) throws SQLException {
        if (datePicker.getValue() == null) {
            tableView.setItems(getAppointmentsByMonth(ld));
        } else {
            tableView.setItems(getAppointmentsByMonth(datePicker.getValue()));
        }
        setTable();
    }

    /**
     * Used by other methods to setup the table.
     *
     * @throws SQLException
     */
    private void setTable() throws SQLException {
        apptIDCol.setCellValueFactory(new PropertyValueFactory<>("apptID"));
        custIDCol.setCellValueFactory(new PropertyValueFactory<>("custID"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("apptTitle"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("apptDescription"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("apptLocation"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("apptStart"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("apptEnd"));
    }

    /**
     * Initializes the controller class. Sets table with appointment data.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb
    ) {
        datePicker.setShowWeekNumbers(true);

        try {
            tableView.setItems(getAllAppointments());
            setTable();
        } catch (SQLException ex) {
            System.out.println("Problem setting table." + ex.getMessage());
        }
    }

}
