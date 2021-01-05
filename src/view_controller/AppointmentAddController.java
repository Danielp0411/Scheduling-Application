/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view_controller;

import static dao.AppointmentDaoImpl.addAppointment;
import static dao.AppointmentDaoImpl.apptIDGen;
import static dao.ContactDaoImpl.getAllContacts;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Appointment;
import model.Contact;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import static utils.Messages.AppointmentHandler;
import static dao.AppointmentDaoImpl.appointmentOverlap;

/**
 * FXML Controller for the AppointmentAdd screen. This class contains methods for input validation and adding an appointment to the database.
 *
 * @author Daniel
 */
public class AppointmentAddController implements Initializable {

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
    private Spinner<Integer> startm;
    @FXML
    private Spinner<Integer> endh;
    @FXML
    private Spinner<Integer> endm;

    /**
     * Performs input validation and creates a new appointment.
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
        } else if (apptContactCombo.getSelectionModel().isEmpty()) {
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
                    Appointment a = new Appointment(id, title, description, location, type, startTimestamp, endTimestamp, custID, userID, contactID);
                    addAppointment(a);
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
     * Initializes the controller class. Sets appointment ID, contact combo box, and spinner values (for time).
     */
    @Override
    public void initialize(URL url, ResourceBundle rb
    ) {
        try {
            apptID.setText(Integer.toString(apptIDGen()));
            apptContactCombo.setItems(getAllContacts());
            SpinnerValueFactory<Integer> h = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 24, 0, 1);
            SpinnerValueFactory<Integer> h1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 24, 0, 1);
            SpinnerValueFactory<Integer> m = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 45, 0, 15);
            SpinnerValueFactory<Integer> m1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 45, 0, 15);
            starth.setValueFactory(h);
            endh.setValueFactory(h1);
            startm.setValueFactory(m);
            endm.setValueFactory(m1);

        } catch (SQLException ex) {
            Logger.getLogger(AppointmentAddController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
}
