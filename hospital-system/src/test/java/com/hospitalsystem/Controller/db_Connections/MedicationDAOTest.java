package com.hospitalsystem.Controller.db_Connections;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.hospitalsystem.Main;
import com.hospitalsystem.Model.Medication;
import com.hospitalsystem.Model.Appointment;
import com.hospitalsystem.Model.Patient;

public class MedicationDAOTest {

    Medication medication;
    Appointment appointment;
    Patient patient;
    MedicationDAO medicationDAO;

    @Before
    public void setUp() throws Exception {
        Main.setTestMode();
        this.medication = new Medication("MedicationA", "10mg", "Once a day", "DescriptionA", "Dr. Smith", "2024-01-01");
        this.appointment = new Appointment("2024-12-05 10:00:00", "Dr. Smith", "General Checkup");
        this.patient = new Patient("John Doe", "12345678901", "1990-01-01", "123 Main St", "555-1234", "john.doe@example.com", "No history");

        this.medicationDAO = new MedicationDAO();
        medicationDAO.addMedicationToAppointmentAndPatient(medication, appointment, patient); // Adding medication for tests
    }

    @Test
    public void testAddMedicationToAppointmentAndPatient() {
        assertDoesNotThrow(() -> medicationDAO.addMedicationToAppointmentAndPatient(medication, appointment, patient));
    }

    @Test
    public void testUpdateMedication() {
        medication.setDosage("20mg");
        assertDoesNotThrow(() -> medicationDAO.updateMedication(medication));
    }

    @Test
    public void testListAllMedications() throws SQLException {
        List<Medication> medications = medicationDAO.listAllMedications();
        assertTrue(medications.size() > 0);
    }

    @Test
    public void testDeletePatientMedication() throws SQLException {
        assertDoesNotThrow(() -> medicationDAO.deletePatientMedication(patient.getName(), medication.getId()));
    }
}