package com.hospitalsystem.View.List;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

public class ListDataPatientTest {

    @Mock
    private PatientController patientController;

    @InjectMocks
    private ListDataPatient listDataPatient;

    private Scanner scan;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        scan = new Scanner(new ByteArrayInputStream("".getBytes()));
    }

    @Test
    public void testListPatientsByName() throws SQLException {
        String input = "\n";
        provideInput(input);

        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient("John Doe", "12345678901", "1990-01-01", "123 Main St", "555-1234", "john.doe@example.com", "No history"));
        when(patientController.listPatientsByName("John")).thenReturn(patients);

        assertDoesNotThrow(() -> listDataPatient.listPatientsByName("John"));
    }

    private void provideInput(String data) {
        InputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
        scan = new Scanner(testIn);
        listDataPatient.scan = scan;
    }
}