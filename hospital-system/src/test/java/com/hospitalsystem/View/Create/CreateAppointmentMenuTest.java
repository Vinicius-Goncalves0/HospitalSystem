package com.hospitalsystem.View.Create;

import com.hospitalsystem.Controller.AppointmentController;
import com.hospitalsystem.Controller.PatientController;
import com.hospitalsystem.Controller.db_Connections.DoctorDAO;
import com.hospitalsystem.Model.Doctor;
import com.hospitalsystem.Model.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.sql.SQLException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

public class CreateAppointmentMenuTest {
    @Mock
    private PatientController patientController;

    @Mock
    private AppointmentController appointmentController;

    @Mock
    private DoctorDAO doctorDAO;

    @Mock
    private CreateMedicationMenu createMedicationMenu;

    @InjectMocks
    private CreateAppointmentMenu createAppointmentMenu;

    private Scanner scan;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        scan = new Scanner(new ByteArrayInputStream("".getBytes()));
        createAppointmentMenu.scan = scan;
    }

    @Test
    public void testCreateAppointmentMenu() throws SQLException {
        String input = "2024-12-05 10:00\nDr. Smith\nDiagnosis\nN\n";
        provideInput(input);

        Doctor doctor = new Doctor("Dr. Smith", "Cardiology", "123456", "555-1234", "dr.smith@example.com");
        Patient patient = new Patient("John Doe", "12345678901", "1990-01-01", "123 Main St", "555-1234", "john.doe@example.com", "No history");
        
        when(doctorDAO.findDoctorByName("Dr. Smith")).thenReturn(doctor);
        when(patientController.findPatientByName("John Doe")).thenReturn(patient);

        assertDoesNotThrow(() -> createAppointmentMenu.createAppointmentMenu("John Doe"));
    }

    private void provideInput(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        createAppointmentMenu.scan = new Scanner(System.in);
    }
}