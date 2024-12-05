package com.hospitalsystem.View.Create;

import com.hospitalsystem.Controller.AlertController;
import com.hospitalsystem.Controller.PatientController;
import com.hospitalsystem.Controller.DeviceController;
import com.hospitalsystem.Controller.db_Connections.DoctorDAO;
import com.hospitalsystem.Model.Device;
import com.hospitalsystem.Model.Doctor;
import com.hospitalsystem.Model.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

public class CreateAlertTest {
    @Mock
    private PatientController patientController;

    @Mock
    private DeviceController deviceController;

    @Mock
    private DoctorDAO doctorDAO;

    @Mock
    private AlertController alertController;

    @InjectMocks
    private CreateAlert createAlert;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateAlert() throws SQLException {
        String input = "Test Type\nTest Message\nDr. Smith\n2024-12-05\n";
        provideInput(input);

        Doctor doctor = new Doctor("Dr. Smith", "Cardiology", "123456", "555-1234", "dr.smith@example.com");
        Patient patient = new Patient("John Doe", "12345678901", "1990-01-01", "123 Main St", "555-1234", "john.doe@example.com", "No history");
        Device device = new Device(1, "Type1", "Brand1", "Model1", true, 50, 100, 10);

        when(doctorDAO.findDoctorByName("Dr. Smith")).thenReturn(doctor);
        when(patientController.findPatientByName("John Doe")).thenReturn(patient);
        when(deviceController.findDeviceByID(1)).thenReturn(device);

        assertDoesNotThrow(() -> createAlert.createAlert("John Doe", 1));
    }

    private void provideInput(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        createAlert = new CreateAlert();
    }
}