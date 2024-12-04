package com.hospitalsystem.Controller.db_Connections;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import com.hospitalsystem.Model.Alert;
import com.hospitalsystem.Model.Device;
import com.hospitalsystem.Model.Patient;

public class AlertDAOTest {

    @Test
    public void testGenerateAlert() throws SQLException {
        Alert alert = new Alert("type", "message", "doctor", "data");
        Device device = new Device("type", "brand", "model", true, 10, 20, 5);
        Patient patient = new Patient("name", "CPF", "birthDate", "address", "phone", "email", "histories");

        AlertDAO alertDAO = new AlertDAO();

        if (alertDAO.generateAlert(alert, device, patient)) {
            System.out.println("Alert generated successfully");
        } else {
            System.out.println("Error generating alert");
        }
    }

    @Test
    public void testIsAlertOwnedByPatient() throws SQLException {
        AlertDAO alertDAO = new AlertDAO();
        String patientName = "name";
        int alertId = 1;

        boolean result = alertDAO.isAlertOwnedByPatient(patientName, alertId);
        if (result) {
            System.out.println("Alert belongs to the patient");
        } else {
            System.out.println("Alert does not belong to the patient");
        }
    }

    @Test
    public void testDeletePatientAlert() throws SQLException {
        AlertDAO alertDAO = new AlertDAO();
        String patientName = "name";
        int alertId = 1;

        alertDAO.deletePatientAlert(patientName, alertId);
        System.out.println("Patient alert deleted successfully");
    }

    @Test
    public void testDeleteAlertById() throws SQLException {
        AlertDAO alertDAO = new AlertDAO();
        int alertId = 1;

        alertDAO.deleteAlertById(alertId);
        System.out.println("Alert deleted successfully");
    }

    @Test
    public void testListAllAlerts() throws SQLException {
        AlertDAO alertDAO = new AlertDAO();

        List<Alert> alerts = alertDAO.listAllAlerts();
        if (!alerts.isEmpty()) {
            System.out.println("Alerts listed successfully");
        } else {
            System.out.println("No alerts found");
        }
    }

    @Test
    public void testGetPatientIdByAlertId() throws SQLException {
        AlertDAO alertDAO = new AlertDAO();
        int alertId = 1;

        int patientId = alertDAO.getPatientIdByAlertId(alertId);
        if (patientId > 0) {
            System.out.println("Patient ID found: " + patientId);
        } else {
            System.out.println("No patient ID found for the alert");
        }
    }

    @Test
    public void testListAlertsByPatientId() throws SQLException {
        AlertDAO alertDAO = new AlertDAO();
        int patientId = 1;

        List<Alert> alerts = alertDAO.listAlertsByPatientId(patientId);
        if (!alerts.isEmpty()) {
            System.out.println("Alerts listed successfully for patient ID: " + patientId);
        } else {
            System.out.println("No alerts found for the patient ID");
        }
    }

    @Test
    public void testAlertExists() throws SQLException {
        AlertDAO alertDAO = new AlertDAO();
        String message = "message";
        String deviceType = "type";

        boolean result = alertDAO.alertExists(message, deviceType);
        if (result) {
            System.out.println("Alert exists");
        } else {
            System.out.println("Alert does not exist");
        }
    }
}