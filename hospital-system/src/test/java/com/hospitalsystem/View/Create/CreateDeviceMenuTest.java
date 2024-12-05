package com.hospitalsystem.View.Create;

import com.hospitalsystem.Controller.DeviceController;
import com.hospitalsystem.Controller.PatientController;
import com.hospitalsystem.Model.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

public class CreateDeviceMenuTest {
    @Mock
    private DeviceController deviceController;

    @Mock
    private PatientController patientController;

    @InjectMocks
    private CreateDeviceMenu createDeviceMenu;

    private Scanner scan;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        scan = new Scanner(new ByteArrayInputStream("".getBytes()));
        createDeviceMenu.scan = scan;
    }

    @Test
    public void testCreateDeviceMenu() throws SQLException {
        String input = "Type1\nBrand1\nModel1\n100\n50\ntrue\n";
        provideInput(input);

        Patient patient = new Patient("John Doe", "12345678901", "1990-01-01", "123 Main St", "555-1234", "john.doe@example.com", "No history");

        when(patientController.findPatientByName("John Doe")).thenReturn(patient);

        assertDoesNotThrow(() -> createDeviceMenu.createDeviceMenu("John Doe"));
    }

    private void provideInput(String data) {
        InputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
        createDeviceMenu.scan = new Scanner(System.in);
    }
}