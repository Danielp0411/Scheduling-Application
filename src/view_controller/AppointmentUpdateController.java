/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view_controller;

import static dao.AppointmentDaoImpl.appointmentOverlap;
import static dao.AppointmentDaoImpl.updateAppointment;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static view_controller.MainController.getSelectedAppointment;
import model.Appointment;
import model.Contact;
import static dao.ContactDaoImpl.getAllContacts;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.SpinnerValueFactory;
import static utils.Messages.AppointmentHandler;

/**
 * FXML Controller for the AppointmentUpdate screen. This class contains methods for input validation and updating an appointment in the database.
 *
 * @author Daniel
 */
public class AppointmentUpdateController implements Initializable {

    @FXML
    private TextField apptID;
    @FXML
    private TextField apptTitle;
    @FXML
    private TextField apptDescription;
    @FXML
    private TextField apptLocation;
    @FXML
    private ComboBox<Contact> apptContactCombo;
    @FXML
    private TextField apptType;
    @FXML
    private TextField apptCustID;
    @FXML
    private TextField apptUserID;
    @FXML
    private Button submitButton;
    @FXML
    private DatePicker apptStartDate;
    @FXML
    private DatePicker apptEndDate;
    @FXML
    private Spinner<Integer> starth;
    @FXML
    private Spinner<Integer> endh;
    @FXML
    private Spinner<Integer> startm;
    @FXML
    private Spinner<Integer> endm;

    /**
     * Performs input validation and updates an appointment.
     *
     * @param event clicking the "submit" button
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    private void submit(ActionEvent event) throws IOException, SQLException {
        LocalDateTime officeOpenLDT = LocalDateTime.of(LocalDate.now(), LocalTime.of(8, 0));
        ZonedDateTime officeOpenZDT = officeOpenLDT.atZone(ZoneId.of("America/New_York"));
        ZonedDateTime officeOpenEST = officeOpenZDT.withZoneSameInstant(ZoneId.systemDefault());
        LocalTime officeOpenESTLT = officeOpenEST.toLocalTime();

        LocalDateTime officeCloseLDT = LocalDateTime.of(LocalDate.now(), LocalTime.of(22, 0));
        ZonedDateTime officeCloseZDT = officeCloseLDT.atZone(ZoneId.of("America/New_York"));
        ZonedDateTime officeCloseEST = officeCloseZDT.withZoneSameInstant(ZoneId.systemDefault());
        LocalTime officeCloseESTLT = officeCloseEST.toLocalTime();

        int id = Integer.parseInt(apptID.getText());
        String title = apptTitle.getText();
        String description = apptDescription.getText();
        String location = apptLocation.getText();
        String type = apptType.getText();
        //Input validation
        if (title.isEmpty()) {
            AppointmentHandler(3);
        } else if (description.isEmpty()) {
            AppointmentHandler(4);
        } else if (location.isEmpty()) {
            AppointmentHandler(5);
        } else if (apptContactCombo.getSelectionModel().getSelectedItem() == null) {
            AppointmentHandler(6);
        } else if (type.isEmpty()) {
            AppointmentHandler(7);
        } else if (apptStartDate.getValue() == null) {
            AppointmentHandler(8);
        } else if (apptEndDate.getValue() == null) {
            AppointmentHandler(9);
        } else if (apptEndDate.getValue().isBefore(apptStartDate.getValue())) {
            AppointmentHandler(10);
        } else if (apptCustID.getText().isEmpty()) {
            AppointmentHandler(11);
        } else if (apptUserID.getText().isEmpty()) {
            AppointmentHandler(12);
        } else {
            try {
                int custID = Integer.parseInt(apptCustID.getText());
                int userID = Integer.parseInt(apptUserID.getText());
                int contactID = apptContactCombo.getSelectionModel().getSelectedItem().getContactID();

                LocalDate startDate = apptStartDate.getValue();
                LocalTime startTime = LocalTime.parse(Integer.toString(starth.getValue()) + ":"
                        + Integer.toString(startm.getValue()), DateTimeFormatter.ofPattern("H:m"));

                LocalDate endDate = apptEndDate.getValue();
                LocalTime endTime = LocalTime.parse(Integer.toString(endh.getValue()) + ":"
                        + Integer.toString(endm.getValue()), DateTimeFormatter.ofPattern("H:m"));
                //Timezone conversions
                LocalDateTime startLDT = startDate.atTime(startTime);
                ZonedDateTime startZDT = startLDT.atZone(ZoneId.systemDefault());
                ZonedDateTime startUTC = startZDT.withZoneSameInstant(ZoneId.of("UTC"));
                Timestamp startTimestamp = Timestamp.valueOf(startUTC.toLocalDateTime());

                LocalDateTime endLDT = endDate.atTime(endTime);
                ZonedDateTime endZDT = endLDT.atZone(ZoneId.systemDefault());
                ZonedDateTime endUTC = endZDT.withZoneSameInstant(ZoneId.of("UTC"));
                Timestamp endTimestamp = Timestamp.valueOf(endUTC.toLocalDateTime());

                if (endLDT.isBefore(startLDT) || endLDT.isEqual(startLDT)) {
                    AppointmentHandler(13);
                } else if (startZDT.toLocalTime().isBefore(officeOpenESTLT) || startZDT.toLocalTime().isAfter(officeCloseESTLT)) {
                    AppointmentHandler(14);
                } else if (endZDT.toLocalTime().isBefore(officeOpenESTLT) || endZDT.toLocalTime().isAfter(officeCloseESTLT)) {
                    AppointmentHandler(14);
                    //Checks for appointment overlap
                } else if ((appointmentOverlap(startLDT.toLocalDate(), startUTC.toLocalTime(), endUTC.toLocalTime(), id)) == true) {
                    AppointmentHandler(15);
                } else {
                    Appointment appt = new Appointment(id, title, description, location, type, startTimestamp, endTimestamp, custID, userID, contactID);
                    updateAppointment(appt);
                    goBack(event);
                }
            } catch (NumberFormatException ex) {
                AppointmentHandler(16);
            }
        }
    }

    /**
     * Takes the user back to the Main screen.
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
     * Initializes the controller class. Sets all information from the selected appointment into their respective text fields, ComboBox, and spinners. Lambda
     * expression is used to get the contact where the contactID matches that of the selected appointment. Using a lambda expression here made the code easier
     * to read and was the most efficient way of getting the correct information.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Appointment selectedAppointment = getSelectedAppointment();
        apptID.setText(Integer.toString(selectedAppointment.getApptID()));
        apptTitle.setText(selectedAppointment.getApptTitle());
        apptDescription.setText(selectedAppointment.getApptDescription());
        apptLocation.setText(selectedAppointment.getApptLocation());
        apptType.setText(selectedAppointment.getApptType());
        apptCustID.setText(Integer.toString(selectedAppointment.getCustID()));
        apptUserID.setText(Integer.toString(selectedAppointment.getUserID()));
        apptStartDate.setValue(selectedAppointment.getApptStart().toLocalDateTime().toLocalDate());
        apptEndDate.setValue(selectedAppointment.getApptEnd().toLocalDateTime().toLocalDate());

        try {
            ObservableList<Contact> contact = getAllContacts().filtered(c -> {
                return c.getContactID() == selectedAppointment.getContactID();
            });
            apptContactCombo.setItems(getAllContacts());
            apptContactCombo.setValue(contact.get(0));
        } catch (SQLException ex) {
            System.out.println("Problem getting contact list." + ex.getMessage());
        }

        SpinnerValueFactory<Integer> h = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 24, selectedAppointment.getApptStart().toLocalDateTime().getHour(), 1);
        SpinnerValueFactory<Integer> h1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 24, selectedAppointment.getApptEnd().toLocalDateTime().getHour(), 1);
        SpinnerValueFactory<Integer> m = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 45, selectedAppointment.getApptStart().toLocalDateTime().getMinute(), 15);
        SpinnerValueFactory<Integer> m1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 45, selectedAppointment.getApptEnd().toLocalDateTime().getMinute(), 15);
        starth.setValueFactory(h);
        endh.setValueFactory(h1);
        startm.setValueFactory(m);
        endm.setValueFactory(m1);
    }

}
