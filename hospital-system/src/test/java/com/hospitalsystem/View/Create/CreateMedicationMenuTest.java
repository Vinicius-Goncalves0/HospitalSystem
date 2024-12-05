package com.hospitalsystem.View.Create;

import com.hospitalsystem.Controller.MedicationController;
import com.hospitalsystem.Controller.PatientController;
import com.hospitalsystem.Controller.AppointmentController;
import com.hospitalsystem.Controller.db_Connections.DoctorDAO;
import com.hospitalsystem.Model.Doctor;
import com.hospitalsystem.Model.Medication;
import com.hospitalsystem.Model.Patient;
import com.hospitalsystem.Model.Appointment;
import com.hospitalsystem.View.List.ListPatientAppointmentMenu;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class CreateMedicationMenuTest {
    @Mock
    private MedicationController medicationController;

    @Mock
    private PatientController patientController;

    @Mock
    private AppointmentController appointmentController;

    @Mock
    private DoctorDAO doctorDAO;

    @Mock
    private ListPatientAppointmentMenu listPatientAppointmentMenu;

    @InjectMocks
    private CreateMedicationMenu createMedicationMenu;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        createMedicationMenu = new CreateMedicationMenu();
        createMedicationMenu.scan = new Scanner(new ByteArrayInputStream("".getBytes()));
        injectMocks();
    }

    private void injectMocks() {
        try {
            java.lang.reflect.Field medicationControllerField = CreateMedicationMenu.class.getDeclaredField("medicationController");
            medicationControllerField.setAccessible(true);
            medicationControllerField.set(createMedicationMenu, medicationController);

            java.lang.reflect.Field patientControllerField = CreateMedicationMenu.class.getDeclaredField("patientController");
            patientControllerField.setAccessible(true);
            patientControllerField.set(createMedicationMenu, patientController);

            java.lang.reflect.Field appointmentControllerField = CreateMedicationMenu.class.getDeclaredField("appointmentController");
            appointmentControllerField.setAccessible(true);
            appointmentControllerField.set(createMedicationMenu, appointmentController);

            java.lang.reflect.Field doctorDAOField = CreateMedicationMenu.class.getDeclaredField("doctorDAO");
            doctorDAOField.setAccessible(true);
            doctorDAOField.set(createMedicationMenu, doctorDAO);

            java.lang.reflect.Field listPatientAppointmentMenuField = CreateMedicationMenu.class.getDeclaredField("listPatientAppointmentMenu");
            listPatientAppointmentMenuField.setAccessible(true);
            listPatientAppointmentMenuField.set(createMedicationMenu, listPatientAppointmentMenu);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testCreateMedicationMenu() throws SQLException {
        String input = "1\nMed1\n50mg\nTwice a day\nFor pain relief\nDr. Smith\n2024-12-01\n";
        provideInput(input);

        Doctor doctor = new Doctor("Dr. Smith", "Cardiology", "12345", "123-456-7890", "doctor@example.com");
        Patient patient = new Patient("John Doe", "12345678900", "1990-01-01", "123 Main St", "1234567890", "johndoe@example.com", "No prior history.");
        Appointment appointment = new Appointment("2024-12-05 10:00:00", "Dr. Smith", "General Checkup");

        when(doctorDAO.findDoctorByName("Dr. Smith")).thenReturn(doctor);
        when(patientController.findPatientByName("John Doe")).thenReturn(patient);
        when(appointmentController.findAppointmentByID(1)).thenReturn(appointment);

        assertDoesNotThrow(() -> createMedicationMenu.createMedicationMenu("John Doe"));
        verify(medicationController, times(1)).addMedicationToAppointmentAndPatient(any(Medication.class), eq(appointment), eq(patient));
    }

    private void provideInput(String data) {
        InputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
        createMedicationMenu.scan = new Scanner(System.in);
    }
}