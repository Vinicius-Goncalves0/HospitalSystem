package com.hospitalsystem.Controller.db_Connections;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.hospitalsystem.Main;
import com.hospitalsystem.Model.Device;
import com.hospitalsystem.Model.Patient;

public class DeviceDAOTest {

    Device device;
    Patient patient;
    DeviceDAO deviceDAO;

    @Before
    public void setUp() throws Exception {
        Main.setTestMode();
        this.device = new Device("TypeA", "BrandA", "ModelA", true, 100, 120, 80);
        this.patient = new Patient("John Doe", "12345678901", "1990-01-01", "123 Main St", "555-1234", "john.doe@example.com", "No history");

        this.deviceDAO = new DeviceDAO();
        deviceDAO.addDeviceToPatient(device, patient); // Adding device to patient for tests
    }

    @Test
    public void testAddDeviceToPatient() throws SQLException {
        assertDoesNotThrow(() -> deviceDAO.addDeviceToPatient(device, patient));
    }

    @Test
    public void testDeletePatientDevice() throws SQLException {
        assertDoesNotThrow(() -> deviceDAO.deletePatientDevice(patient.getName(), device.getId()));
    }

    @Test
    public void testListAllDevices() throws SQLException {
        List<Device> devices = deviceDAO.listAllDevices();
        assertTrue(devices.size() > 0);
    }
}
