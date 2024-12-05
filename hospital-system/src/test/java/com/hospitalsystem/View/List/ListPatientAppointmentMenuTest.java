package com.hospitalsystem.View.List;

import com.hospitalsystem.Controller.AppointmentController;
import com.hospitalsystem.Model.Appointment;
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

public class ListPatientAppointmentMenuTest {

    @Mock
    private AppointmentController appointmentController;

    @InjectMocks
    private ListPatientAppointmentMenu listPatientAppointmentMenu;

    private Scanner scan;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        scan = new Scanner(new ByteArrayInputStream("".getBytes()));
    }

    @Test
    public void testListShortAppointmentsByPatientName() throws SQLException {
        String input = "\n";
        provideInput(input);

        List<Appointment> appointments = new ArrayList<>();
        appointments.add(new Appointment(1, "2024-12-05 10:00", "Dr. Smith", "Diagnosis"));
        when(appointmentController.listAppointmentByPatientName("John")).thenReturn(appointments);

        assertDoesNotThrow(() -> listPatientAppointmentMenu.listShortAppointmentsByPatientName("John"));
    }

    @Test
    public void testListAppointmentsByPatientName() throws SQLException {
        String input = "\nY\nN\n";
        provideInput(input);

        List<Appointment> appointments = new ArrayList<>();
        appointments.add(new Appointment(1, "2024-12-05 10:00", "Dr. Smith", "Diagnosis"));
        when(appointmentController.listAppointmentByPatientName("John")).thenReturn(appointments);

        assertDoesNotThrow(() -> listPatientAppointmentMenu.listAppointmentsByPatientName("John"));
    }

    private void provideInput(String data) {
        InputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
        scan = new Scanner(testIn);
        listPatientAppointmentMenu.scan = scan;
    }
}