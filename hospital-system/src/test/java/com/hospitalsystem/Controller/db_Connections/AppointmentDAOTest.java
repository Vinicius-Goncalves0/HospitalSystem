package com.hospitalsystem.Controller.db_Connections;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.hospitalsystem.Main;
import com.hospitalsystem.Model.Appointment;
import com.hospitalsystem.Model.Patient;

public class AppointmentDAOTest {

    Appointment appointment;
    Patient patient;
    AppointmentDAO appointmentDAO;

    List<Appointment> appointments;

    @Before
    public void setUp() throws Exception {
        Main.setTestMode();
        this.appointment = new Appointment("2024-12-05 10:00:00", "Dr. Smith", "General Checkup");
        this.patient = new Patient("John Doe", "12345678901", "1990-01-01", "123 Main St", "555-1234", "john.doe@example.com", "No history");

        this.appointmentDAO = new AppointmentDAO();
        appointments = appointmentDAO.listAllAppointments();
    }

    @Test
    public void testAddAppointmentToPatient() throws SQLException {
        assertDoesNotThrow(() -> appointmentDAO.addAppointmentToPatient(appointment, patient));
    }

    @Test
    public void testDeletePatientAppointmentById() throws SQLException {
        assertDoesNotThrow(() -> appointmentDAO.deletePatientAppointmentById(patient.getName(), appointment.getId()));
    }

    @Test
    public void testListAllAppointments() throws SQLException {
        assertTrue(appointments.size() > 0);
    }
}