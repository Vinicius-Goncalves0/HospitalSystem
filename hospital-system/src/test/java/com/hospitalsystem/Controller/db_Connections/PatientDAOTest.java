package com.hospitalsystem.Controller.db_Connections;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.hospitalsystem.Main;
import com.hospitalsystem.Model.Patient;

public class PatientDAOTest {

    Patient patient;
    PatientDAO patientDAO;

    @Before
    public void setUp() throws Exception {
        Main.setTestMode();
        this.patient = new Patient("John Doe", "12345678901", "1990-01-01", "123 Main St", "555-1234", "john.doe@example.com", "No history");

        this.patientDAO = new PatientDAO();
        patientDAO.addPatient(patient); // Adding patient for tests
    }

    @Test
    public void testAddPatient() throws SQLException {
        assertDoesNotThrow(() -> patientDAO.addPatient(patient));
    }

    @Test
    public void testListPatientsByName() throws SQLException {
        List<Patient> patients = patientDAO.listPatientsByName("John");
        assertTrue(patients.size() > 0);
    }

    @Test
    public void testUpdatePatient() throws SQLException {
        patient.setPhone("555-5678");
        assertDoesNotThrow(() -> patientDAO.updatePatient(patient));
    }
}