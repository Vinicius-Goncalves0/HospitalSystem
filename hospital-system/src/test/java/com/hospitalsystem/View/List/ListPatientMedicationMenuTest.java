package com.hospitalsystem.View.List;

import com.hospitalsystem.Controller.MedicationController;
import com.hospitalsystem.Model.Medication;
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

public class ListPatientMedicationMenuTest {

    @Mock
    private MedicationController medicationController;

    @InjectMocks
    private ListPatientMedicationMenu listPatientMedicationMenu;

    private Scanner scan;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        scan = new Scanner(new ByteArrayInputStream("".getBytes()));
    }

    @Test
    public void testListMedicationsByAppointmentID() throws SQLException {
        String input = "\n";
        provideInput(input);

        List<Medication> medications = new ArrayList<>();
        medications.add(new Medication(1, "Medication1", "10mg", "2 times daily", "Description", "Dr. Smith", "2024-12-05"));
        when(medicationController.listMedicationsByAppointmentId(1)).thenReturn(medications);

        assertDoesNotThrow(() -> listPatientMedicationMenu.listMedicationsByAppointmentID(1));
    }

    @Test
    public void testListMedicationsByPatientName() throws SQLException {
        String input = "\n";
        provideInput(input);

        List<Medication> medications = new ArrayList<>();
        medications.add(new Medication(1, "Medication1", "10mg", "2 times daily", "Description", "Dr. Smith", "2024-12-05"));
        when(medicationController.listMedicationsByPatientName("John")).thenReturn(medications);

        assertDoesNotThrow(() -> listPatientMedicationMenu.listMedicationsByPatientName("John"));
    }

    private void provideInput(String data) {
        InputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
        scan = new Scanner(testIn);
        listPatientMedicationMenu.scan = scan;
    }
}