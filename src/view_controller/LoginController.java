/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view_controller;

import static dao.AppointmentDaoImpl.getAllAppointments;
import static dao.UserDaoImpl.findUser;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import javax.swing.JOptionPane;
import static utils.Messages.loginError;

/**
 * FXML Controller for the Login screen. This class contains methods to validate login information.
 *
 * @author Daniel
 */
public class LoginController implements Initializable {

    @FXML
    private Text loginPanel;
    @FXML
    private TextField usernameBox;
    @FXML
    private TextField passwordBox;
    @FXML
    private Button loginButton;
    @FXML
    private Button closeButton;
    @FXML
    private Text username;
    @FXML
    private Text password;
    @FXML
    private Label userLocation;

    /**
     * Performs input validation and logs all attempts in file. 
     * If validated, takes user to Main screen. 
     * If not validated, gives user an error in their system locale language.
     *
     * @param event clicking the "login" button
     * @throws IOException
     */
    @FXML
    private void login(ActionEvent event) throws IOException {
        String user = usernameBox.getText();
        String pass = passwordBox.getText();

        FileWriter loginActivityFW = new FileWriter("login_activity.txt", true);
        PrintWriter loginActivityPW = new PrintWriter(loginActivityFW);

        if (user.isEmpty()) {
            loginError(1);
        } else if (pass.isEmpty()) {
            loginError(2);
        } else {
            try {
                Timestamp loginTS = Timestamp.valueOf(LocalDateTime.now());
                if (findUser(user, pass) == true) {
                    loginActivityPW.println("User " + user + " successfully logged in at " + loginTS + " (UTC)");

                    Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    Parent scene = FXMLLoader.load(getClass().getResource("/view_controller/Main.fxml"));
                    stage.setScene(new Scene(scene));
                    stage.show();
                    apptAlert();

                } else {
                    loginActivityPW.println("User " + user + " gave invalid log-in at " + loginTS + " (UTC)");
                    loginError(3);
                }
            } catch (SQLException e) {
                System.out.println("Problem finding user in database." + e.getMessage());
            }
        }
        loginActivityPW.close();
    }

    /**
     * Closes the application.
     *
     * @param event clicking the "close" button
     */
    @FXML
    private void close(ActionEvent event) {
        System.exit(0);
    }

    /**
     * Alerts the user whether there is an appointment soon.
     *
     * @throws SQLException
     */
    private void apptAlert() throws SQLException {
        boolean apptSoon = false;
        for (int i = 0; i < getAllAppointments().size(); i++) {
            if (LocalDate.now().isEqual(getAllAppointments().get(i).getApptStart().toLocalDateTime().toLocalDate())) {
                LocalTime startLT = getAllAppointments().get(i).getApptStart().toLocalDateTime().toLocalTime();
                LocalTime currentLT = LocalTime.now();

                Long timeDiff = ChronoUnit.MINUTES.between(startLT, currentLT) * -1;
                int apptID = getAllAppointments().get(i).getApptID();
                Timestamp apptStart = getAllAppointments().get(i).getApptStart();

                if (timeDiff + 1 < 15 && timeDiff + 1 >= 0) {
                    JOptionPane.showMessageDialog(null, "You have an appointment in: " + timeDiff
                            + " Minute(s)\nAppointment ID: " + apptID + "\nDate & Time: " + apptStart, "Appointment Soon", 2);
                    apptSoon = true;
                }
            }
        }
        if (apptSoon == false) {
            JOptionPane.showMessageDialog(null, "You do not have any appointments within the next 15 minutes.", "No Appointments Soon", 2);
        }
    }

    /**
     * Initializes the controller class. Sets the labels according to the language of the users system.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rb = ResourceBundle.getBundle("utils/Lang", Locale.getDefault());
        if (Locale.getDefault().getLanguage().equals("en") || Locale.getDefault().getLanguage().equals("fr")) {
            loginPanel.setText(rb.getString("loginPanel"));
            username.setText(rb.getString("username"));
            password.setText(rb.getString("password"));
            loginButton.setText(rb.getString("login"));
            closeButton.setText(rb.getString("close"));
        }
        userLocation.setText(ZoneId.systemDefault().toString());
    }

}
