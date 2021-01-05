/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view_controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import static dao.AppointmentDaoImpl.getApptCountByDay;

/**
 * FXML Controller for the ApptsPerDayReport. This class contains methods to display a bar chart of the busiest days for appointments.
 *
 * @author Daniel
 */
public class ApptsPerDayReportController implements Initializable {

    @FXML
    private BarChart<String, Integer> apptsDayBar;
    
    /**Takes the user back to the reports screen.
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

    /**
     * Initializes the controller class. Sets bar chart with appointment data.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            XYChart.Series<String, Integer> series = new XYChart.Series<>();
            series.setName("Appointments/Day");
            series.getData().add(new XYChart.Data<>("Sunday", getApptCountByDay("Sunday")));
            series.getData().add(new XYChart.Data<>("Monday", getApptCountByDay("Monday")));
            series.getData().add(new XYChart.Data<>("Tuesday", getApptCountByDay("Tuesday")));
            series.getData().add(new XYChart.Data<>("Wednesday", getApptCountByDay("Wednesday")));
            series.getData().add(new XYChart.Data<>("Thursday", getApptCountByDay("Thursday")));
            series.getData().add(new XYChart.Data<>("Friday", getApptCountByDay("Friday")));
            series.getData().add(new XYChart.Data<>("Saturday", getApptCountByDay("Saturday")));
            apptsDayBar.getData().add(series);
        } catch (SQLException ex) {
            System.out.println("Problem loading BarChart data." + ex.getMessage());
        }
    }

}
