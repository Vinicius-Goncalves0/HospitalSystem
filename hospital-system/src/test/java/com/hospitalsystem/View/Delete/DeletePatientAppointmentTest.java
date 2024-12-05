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

import com.hospitalsystem.Controller.AppointmentController;

public class DeletePatientAppointmentTest {

    private final InputStream systemIn = System.in;
    private ByteArrayInputStream testIn;
    private DeletePatientAppointment deletePatientAppointment;
    private AppointmentController appointmentController;

    @BeforeEach
    public void setUp() {
        appointmentController = Mockito.mock(AppointmentController.class);
        deletePatientAppointment = new DeletePatientAppointment() {
            @Override
            public void deleteAppointment(String patientName) {
                @SuppressWarnings("resource")
                Scanner scan = new Scanner(testIn);
                System.setIn(systemIn);
                try {
                    appointmentController.deletePatientAppointmentByID(patientName, scan.nextInt());
                } catch (SQLException e) {
                    System.out.println("\n--- Error deleting appointment: " + e.getMessage() + " ---\n");
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
    public void testDeleteAppointment_Success() throws SQLException {
        String patientName = "John Doe";
        String input = "1";

        provideInput(input);
        
        doNothing().when(appointmentController).deletePatientAppointmentByID(patientName, 1);

        assertDoesNotThrow(() -> deletePatientAppointment.deleteAppointment(patientName));
        verify(appointmentController, times(1)).deletePatientAppointmentByID(patientName, 1);
    }

    @Test
    public void testDeleteAppointment_SQLException() throws SQLException {
        String patientName = "John Doe";
        String input = "1";

        provideInput(input);
        
        doThrow(new SQLException()).when(appointmentController).deletePatientAppointmentByID(patientName, 1);

        deletePatientAppointment.deleteAppointment(patientName);
    }
}