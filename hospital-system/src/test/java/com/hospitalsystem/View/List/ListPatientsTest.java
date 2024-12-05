package com.hospitalsystem.View.List;

import com.hospitalsystem.Controller.PatientController;
import com.hospitalsystem.Model.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

public class ListPatientsTest {

    @Mock
    private PatientController patientController;

    @InjectMocks
    private ListPatients listPatients;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListAllPatients() throws SQLException {
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("John Doe", "12345678901", "1990-01-01", "123 Main St", "555-1234", "john.doe@example.com", "No history"));
        when(patientController.listAllPatients()).thenReturn(patients);

        assertDoesNotThrow(() -> listPatients.listAllPatients());
    }
}