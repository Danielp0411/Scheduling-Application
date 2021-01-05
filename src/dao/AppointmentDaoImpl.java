/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointment;

/**
 * This class is used to create, retrieve, update, and delete appointment data from the database.
 *
 * @author Daniel
 */
public class AppointmentDaoImpl {

    /**
     * Retrieves all appointments.
     *
     * @return all appointment data as ObservableList
     * @throws SQLException
     */
    public static ObservableList<Appointment> getAllAppointments() throws SQLException {
        ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
        DBConnection.getConnection();
        String SQLStmt = "SELECT * FROM appointments";
        DBQuery.makeQuery(SQLStmt);
        ResultSet rs = DBQuery.getResult();

        while (rs.next()) {
            Appointment appt = Results(rs);
            allAppointments.add(appt);
        }
        return allAppointments;
    }

    /**
     * Retrieves all appointments that are in a specified month.
     *
     * @param date date of appointment as LocalDate
     * @return appointments by month as ObservableList
     * @throws SQLException
     */
    public static ObservableList<Appointment> getAppointmentsByMonth(LocalDate date) throws SQLException {
        ObservableList<Appointment> appointmentsByMonth = FXCollections.observableArrayList();
        DBConnection.getConnection();
        String SQLStmt = "SELECT * FROM appointments WHERE month(start) = month('" + date + "')";
        DBQuery.makeQuery(SQLStmt);
        ResultSet rs = DBQuery.getResult();

        while (rs.next()) {
            Appointment appt = Results(rs);
            appointmentsByMonth.add(appt);
        }
        return appointmentsByMonth;
    }

    /**
     * Retrieves all appointments that are in a specified week.
     *
     * @param date date of appointment as LocalDate
     * @return appointments by week as ObservableList
     * @throws SQLException
     */
    public static ObservableList<Appointment> getAppointmentsByWeek(LocalDate date) throws SQLException {
        ObservableList<Appointment> appointmentsByWeek = FXCollections.observableArrayList();
        DBConnection.getConnection();
        String SQLStmt = "SELECT * FROM appointments WHERE Week(start) = Week('" + date + "', 0)";
        DBQuery.makeQuery(SQLStmt);
        ResultSet rs = DBQuery.getResult();

        while (rs.next()) {
            Appointment appt = Results(rs);
            appointmentsByWeek.add(appt);
        }
        return appointmentsByWeek;
    }

    /**
     * Retrieves all appointment types and how many of each type occur in a specified month.
     *
     * @param month month of appointment as int
     * @return appointment type count as ObservableList
     * @throws SQLException
     */
    public static ObservableList<Appointment> getApptTypeCount(int month) throws SQLException {
        ObservableList<Appointment> ApptTypeCount = FXCollections.observableArrayList();
        DBConnection.getConnection();
        String SQLStmt = "SELECT Type, COUNT(*) FROM appointments WHERE month(Start) = '" + month + "' GROUP BY Type HAVING COUNT(*) >= 1";
        DBQuery.makeQuery(SQLStmt);
        ResultSet rs = DBQuery.getResult();

        while (rs.next()) {
            Appointment appt = new Appointment(rs.getString(1), rs.getInt(2));
            ApptTypeCount.add(appt);
        }
        return ApptTypeCount;
    }

    /**
     * Retrieves how many appointments occur on a specified day of the week.
     *
     * @param day day of appointment as String
     * @return int count
     * @throws SQLException
     */
    public static int getApptCountByDay(String day) throws SQLException {
        int count = 0;
        DBConnection.getConnection();
        String SQLStmt = "SELECT COUNT(*) FROM appointments WHERE dayname(start) = '" + day + "'";
        DBQuery.makeQuery(SQLStmt);
        ResultSet rs = DBQuery.getResult();

        while (rs.next()) {
            count = rs.getInt(1);
        }
        return count;
    }

    /**
     * Creates an appointment using user-entered information.
     *
     * @param a appointment to create
     */
    public static void addAppointment(Appointment a) {
        DBConnection.getConnection();

        String SQLStmt = "INSERT INTO appointments (Appointment_ID, Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) "
                + "VALUES ('" + a.getApptID() + "', '" + a.getApptTitle() + "', '" + a.getApptDescription() + "', '"
                + a.getApptLocation() + "', '" + a.getApptType() + "', '" + a.getApptStart() + "', '"
                + a.getApptEnd() + "', '" + a.getCustID() + "', '" + a.getUserID() + "', '" + a.getContactID() + "')";
        DBQuery.makeQuery(SQLStmt);
        ResultSet rs = DBQuery.getResult();
    }

    /**
     * Updates an appointment using user-entered information.
     *
     * @param a appointment to update
     */
    public static void updateAppointment(Appointment a) {
        DBConnection.getConnection();

        String SQLStmt = "UPDATE appointments SET Appointment_ID = '" + a.getApptID() + "', Title = '" + a.getApptTitle()
                + "', Description = '" + a.getApptDescription() + "', Location = '" + a.getApptLocation() + "', Type = '"
                + a.getApptType() + "', Start = '" + a.getApptStart() + "', End = '" + a.getApptEnd() + "', Customer_ID = '" + a.getCustID()
                + "', User_ID = '" + a.getUserID() + "', Contact_ID = '" + a.getContactID() + "' WHERE Appointment_ID = '" + a.getApptID() + "'";

        DBQuery.makeQuery(SQLStmt);
        ResultSet rs = DBQuery.getResult();
    }

    /**
     * Deletes an appointment.
     *
     * @param apptID appointment id to delete as int
     */
    public static void deleteAppointment(int apptID) {
        DBConnection.getConnection();
        String SQLStmt = "DELETE FROM appointments WHERE Appointment_ID = '" + apptID + "'";
        DBQuery.makeQuery(SQLStmt);
        ResultSet rs = DBQuery.getResult();
    }

    /**Checks if a new appointment overlaps a previous appointment.
     * 
     * @param date date of new appointment
     * @param start start time of new appointment
     * @param end end time of new appointment
     * @return true if overlap occurs, false if overlap does not occur
     * @throws SQLException 
     */
    public static boolean appointmentOverlap(LocalDate date, LocalTime start, LocalTime end, int apptID) throws SQLException {
        DBConnection.getConnection();
        String SQLStmt = "SELECT * FROM appointments WHERE date(start) = '" + date
                + "' AND ('" + start + "' between time(Start) AND time(End) OR '" + end + "' between time(Start) AND time(End) "
                + "OR '" + start + "' < time(Start) AND '" + end + "' > time(End)) AND Appointment_ID != '" + apptID + "'";
        DBQuery.makeQuery(SQLStmt);
        ResultSet rs = DBQuery.getResult();
        return rs.next();
    }

    /**
     * Used by other methods in this class to format data retrieved from the database.
     *
     * @param rs Result of database query as ResultSet
     * @return Appointment appt
     * @throws SQLException
     */
    private static Appointment Results(ResultSet rs) throws SQLException {
        int apptID = rs.getInt(1);
        String apptTitle = rs.getString(2);
        String apptDescription = rs.getString(3);
        String apptLocation = rs.getString(4);
        String apptType = rs.getString(5);

        ZonedDateTime startTime = Timestamp.valueOf(rs.getString(6)).toLocalDateTime().atZone(ZoneId.of("UTC"));
        ZonedDateTime startTimeLocal = startTime.withZoneSameInstant(ZoneId.systemDefault());
        Timestamp startTimeLocalTS = Timestamp.valueOf(startTimeLocal.toLocalDateTime());

        ZonedDateTime endTime = Timestamp.valueOf(rs.getString(7)).toLocalDateTime().atZone(ZoneId.of("UTC"));
        ZonedDateTime endTimeLocal = endTime.withZoneSameInstant(ZoneId.systemDefault());
        Timestamp endTimeLocalTS = Timestamp.valueOf(endTimeLocal.toLocalDateTime());

        int custID = rs.getInt(12);
        int userID = rs.getInt(13);
        int contactID = rs.getInt(14);

        Appointment appt = new Appointment(apptID, apptTitle, apptDescription, apptLocation, apptType, startTimeLocalTS, endTimeLocalTS, custID, userID, contactID);
        return appt;
    }

    /**
     * Generates an appointment ID.
     *
     * @return appointment id as int
     * @throws SQLException
     */
    public static int apptIDGen() throws SQLException {
        int e = 0;
        for (int i = 0; i < getAllAppointments().size(); i++) {
            e = getAllAppointments().get(i).getApptID();
        }
        return e + 1;
    }
}
