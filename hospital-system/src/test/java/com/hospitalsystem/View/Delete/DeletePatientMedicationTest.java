package com.hospitalsystem.View.Delete;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.hospitalsystem.Controller.MedicationController;


public class DeletePatientMedicationTest {

    private final InputStream systemIn = System.in;
    private ByteArrayInputStream testIn;
    private DeletePatientMedication deletePatientMedication;
    private MedicationController medicationController;

    @BeforeEach
    public void setUp() {
        medicationController = Mockito.mock(MedicationController.class);
        deletePatientMedication = new DeletePatientMedication() {
            @Override
            public void deleteMedication(String patientName) {
                @SuppressWarnings("resource")
                Scanner scan = new Scanner(testIn);
                System.setIn(systemIn);
                try {
                    medicationController.deletePatientMedication(patientName, scan.nextInt());
                } catch (SQLException e) {
                    System.out.println("\n--- Error deleting medicine: " + e.getMessage() + " ---\n");
                }
            }
        };
    }

    @AfterEach
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @Test
    public void testDeleteMedication_Success() throws SQLException {
        String patientName = "John Doe";
        String input = "1";

        provideInput(input);
        
        doNothing().when(medicationController).deletePatientMedication(patientName, 1);

        assertDoesNotThrow(() -> deletePatientMedication.deleteMedication(patientName));
        verify(medicationController, times(1)).deletePatientMedication(patientName, 1);
    }

    @Test
    public void testDeleteMedication_SQLException() throws SQLException {
        String patientName = "John Doe";
        String input = "1";

        provideInput(input);
        
        doThrow(new SQLException()).when(medicationController).deletePatientMedication(patientName, 1);

        deletePatientMedication.deleteMedication(patientName);
    }
}