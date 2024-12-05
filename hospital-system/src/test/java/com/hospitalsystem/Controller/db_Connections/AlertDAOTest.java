package com.hospitalsystem.Controller.db_Connections;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.hospitalsystem.Main;
import com.hospitalsystem.Model.Alert;
import com.hospitalsystem.Model.Device;
import com.hospitalsystem.Model.Patient;

public class AlertDAOTest {
   
    Alert alert;
    Device device;
    Patient patient;
    List<Alert> alerts;

    AlertDAO alertDAO;

    @Before
    public void setUp() throws Exception {  
        Main.setTestMode();
        this.alert = new Alert("type", "message", "doctor", "data");
        this.device = new Device("type", "brand", "model", true, 10, 20, 5);
        this.patient = new Patient("name", "CPF", "birthDate", "address", "phone", "email", "histories");

        this.alertDAO = new AlertDAO();
        alerts = alertDAO.listAllAlerts();
    }
    

    @Test
    public void testGenerateAlert() throws SQLException {
        assertDoesNotThrow(() -> alertDAO.generateAlert(alert, device, patient));
    }

    @Test
    public void testDeleteAlertById() throws SQLException {
        assertDoesNotThrow(() -> alertDAO.deleteAlertById(1));
    }

    @Test
    public void testListAllAlerts() throws SQLException {
        assertTrue(alerts.size() > 0);
    }
}