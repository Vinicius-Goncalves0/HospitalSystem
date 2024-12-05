package com.hospitalsystem.Controller.db_Connections;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.hospitalsystem.Controller.Monitoring;
import com.hospitalsystem.Model.Device;
import com.hospitalsystem.Model.Patient;

public class MonitoringTest {

    Monitoring monitoring;
    DeviceDAO deviceDAO;
    PatientDAO patientDAO;
    AlertDAO alertDAO;

    @Before
    public void setUp() throws Exception {
        this.deviceDAO = Mockito.mock(DeviceDAO.class);
        this.patientDAO = Mockito.mock(PatientDAO.class);
        this.alertDAO = Mockito.mock(AlertDAO.class);
        this.monitoring = new Monitoring(deviceDAO, patientDAO, alertDAO);
    }

    @Test
    public void testGeneratePatientAlert() throws SQLException {
        // Mocking devices
        Device device1 = new Device("TypeA", "BrandA", "ModelA", true, 100, 120, 80);
        Device device2 = new Device("TypeB", "BrandB", "ModelB", true, 100, 120, 80);

        Mockito.when(deviceDAO.listActiveDevices()).thenReturn(List.of(device1, device2));
        Mockito.when(deviceDAO.getPatientIdByDeviceId(device1.getId())).thenReturn(1);
        Mockito.when(deviceDAO.getPatientIdByDeviceId(device2.getId())).thenReturn(2);

        Patient patient1 = new Patient("John Doe", "12345678901", "1990-01-01", "123 Main St", "555-1234", "john.doe@example.com", "No history");
        Patient patient2 = new Patient("Jane Doe", "98765432109", "1992-01-01", "456 Main St", "555-5678", "jane.doe@example.com", "No history");

        Mockito.when(patientDAO.findPatientById(1)).thenReturn(patient1);
        Mockito.when(patientDAO.findPatientById(2)).thenReturn(patient2);

        Mockito.when(alertDAO.alertExists(Mockito.anyString(), Mockito.anyString())).thenReturn(false);

        assertDoesNotThrow(() -> monitoring.generatePatientAlert());
    }
}