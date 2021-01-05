/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view_controller;

import static dao.AppointmentDaoImpl.getApptTypeCount;
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
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointment;

/**
 * FXML Controller for the ApptTypeReport screen. This class contains methods for displaying the amount of appointment types that occur in a specified month.
 *
 * @author Daniel
 */
public class ApptTypeReportController implements Initializable {

    @FXML
    private TableView<Appointment> typeTable;
    @FXML
    private TableColumn<Appointment, String> typeCol;
    @FXML
    private TableColumn<Appointment, Integer> countCol;
    @FXML
    private RadioButton janRadio;
    @FXML
    private ToggleGroup months;
    @FXML
    private RadioButton febRadio;
    @FXML
    private RadioButton marRadio;
    @FXML
    private RadioButton aprRadio;
    @FXML
    private RadioButton mayRadio;
    @FXML
    private RadioButton junRadio;
    @FXML
    private RadioButton julRadio;
    @FXML
    private RadioButton augRadio;
    @FXML
    private RadioButton sepRadio;
    @FXML
    private RadioButton octRadio;
    @FXML
    private RadioButton novRadio;
    @FXML
    private RadioButton decRadio;

    /**
     * Gets the selected month (radio button) and calls the setTable method.
     *
     * @param event clicking a radio button
     * @throws SQLException
     */
    @FXML
    private void pullTypeTable(ActionEvent event) throws SQLException {
        typeTable.setItems(null);
        if (janRadio.isSelected()) {
            setTable(1);
        } else if (febRadio.isSelected()) {
            setTable(2);
        } else if (marRadio.isSelected()) {
            setTable(3);
        } else if (aprRadio.isSelected()) {
            setTable(4);
        } else if (mayRadio.isSelected()) {
            setTable(5);
        } else if (junRadio.isSelected()) {
            setTable(6);
        } else if (julRadio.isSelected()) {
            setTable(7);
        } else if (augRadio.isSelected()) {
            setTable(8);
        } else if (sepRadio.isSelected()) {
            setTable(9);
        } else if (octRadio.isSelected()) {
            setTable(10);
        } else if (novRadio.isSelected()) {
            setTable(11);
        } else if (decRadio.isSelected()) {
            setTable(12);
        }
    }

    /**
     * Sets table with appointment type and how many occur in the specified month.
     *
     * @param i specified month as int
     * @throws SQLException
     */
    private void setTable(int i) throws SQLException {
        typeTable.setItems(getApptTypeCount(i));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("apptType"));
        countCol.setCellValueFactory(new PropertyValueFactory<>("apptTypeCount"));

    }

    /**
     * Takes the user back to the Reports screen.
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
     * Initializes the controller class. Nothing needed to be prepared.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
