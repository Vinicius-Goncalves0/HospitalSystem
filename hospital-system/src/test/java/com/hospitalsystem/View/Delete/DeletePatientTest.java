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

import com.hospitalsystem.Controller.db_Connections.PatientDAO;

public class DeletePatientTest {

    private final InputStream systemIn = System.in;
    private ByteArrayInputStream testIn;
    private DeletePatient deletePatient;
    private PatientDAO patientDAO;

    @BeforeEach
    public void setUp() {
        patientDAO = Mockito.mock(PatientDAO.class);
        deletePatient = new DeletePatient() {
            @Override
            public void deletePatient(int patientId) {
                Scanner scan = new Scanner(testIn);
                System.setIn(systemIn);
                System.out.println("You are about to delete a patient from the database. Are you sure you want to proceed?");
                System.out.println("Type 'Yes, I want to delete.' to confirm, or any other key to cancel.");
                String confirmation = scan.nextLine();

                if (confirmation.equals("Yes, I want to delete.")) {
                    try {
                        patientDAO.deletePatient(patientId);
                        System.out.println("Patient and all associated data successfully deleted.");
                    } catch (SQLException e) {
                        System.out.println("\n--- Error deleting the patient: " + e.getMessage() + " ---\n");
                    }
                }
                scan.close();
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
    public void testDeletePatient_Success() throws SQLException {
        int patientId = 1;
        String input = "Yes, I want to delete.";

        provideInput(input);

        doNothing().when(patientDAO).deletePatient(patientId);

        assertDoesNotThrow(() -> deletePatient.deletePatient(patientId));
        verify(patientDAO, times(1)).deletePatient(patientId);
    }

    @Test
    public void testDeletePatient_Cancel() throws SQLException {
        int patientId = 1;
        String input = "no";

        provideInput(input);
        
        assertDoesNotThrow(() -> deletePatient.deletePatient(patientId));
        verify(patientDAO, times(0)).deletePatient(patientId);
    }

    @Test
    public void testDeletePatient_SQLException() throws SQLException {
        int patientId = 1;
        String input = "Yes, I want to delete.";

        provideInput(input);

        doThrow(new SQLException()).when(patientDAO).deletePatient(patientId);

        deletePatient.deletePatient(patientId);
    }
}