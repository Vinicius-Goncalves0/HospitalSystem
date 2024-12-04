package com.hospitalsystem.Controller;

import java.sql.SQLException;
import java.util.List;

import com.hospitalsystem.Main;
import com.hospitalsystem.Controller.db_Connections.AlertDAO;
import com.hospitalsystem.Model.Alert;
import com.hospitalsystem.Model.Device;
import com.hospitalsystem.Model.Patient;

public class AlertController {

    private AlertDAO alertDAO;

    public AlertController() {
        this.alertDAO = new AlertDAO();
    }

    public void generateAlert(Alert alert, Device device, Patient patient)  throws SQLException {
        alertDAO.generateAlert(alert, device, patient);
    }

    public void deletePatientAlert(String patientName, int alertId) throws SQLException {
        alertDAO.deletePatientAlert(patientName, alertId);
    }

    public List<Alert> listAllAlerts() throws SQLException {
        return alertDAO.listAllAlerts();
    }

    public List<Alert> listAlertsByPatientId(int patientId) throws SQLException {
        return alertDAO.listAlertsByPatientId(patientId);
    }

    public void deleteAlertById(int alertId) throws SQLException {
        alertDAO.deleteAlertById(alertId);
    }

}
