/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view_controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller for the Reports screen.  This class contains methods to display buttons to go to other screens.
 *
 * @author Daniel
 */
public class ReportsController implements Initializable {
    /**Takes the user back to the ApptTypeReport screen.
     * 
     * @param event clicking the "Appointment Type Report" button
     * @throws IOException 
     */
    @FXML
    private void apptTypeReport(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/view_controller/ApptTypeReport.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /**Takes the user back to the ContactScheduleReport screen.
     * 
     * @param event clicking the "Contact Schedule" button
     * @throws IOException 
     */
    @FXML
    private void contactSchedule(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/view_controller/ContactScheduleReport.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    /**Takes the user back to the ApptsPerDayReport screen.
     * 
     * @param event clicking the "# of Appointments/Day" button
     * @throws IOException 
     */
    @FXML
    private void apptsDay(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Parent scene = FXMLLoader.load(getClass().getResource("/view_controller/ApptsPerDayReport.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
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
     * Initializes the controller class. Nothing needed to be prepared.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
