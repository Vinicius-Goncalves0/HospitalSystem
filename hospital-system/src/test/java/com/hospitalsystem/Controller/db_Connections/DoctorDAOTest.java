package com.hospitalsystem.Controller.db_Connections;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.hospitalsystem.Main;
import com.hospitalsystem.Model.Doctor;

public class DoctorDAOTest {

    Doctor doctor;
    DoctorDAO doctorDAO;

    @Before
    public void setUp() throws Exception {
        Main.setTestMode();
        this.doctor = new Doctor("Dr. Smith", "Cardiology", "123456", "555-1234", "dr.smith@example.com");

        this.doctorDAO = new DoctorDAO();
        doctorDAO.addDoctor(doctor); // Adding doctor for tests
    }

    @Test
    public void testAddDoctor() throws SQLException {
        assertDoesNotThrow(() -> doctorDAO.addDoctor(doctor));
    }

    @Test
    public void testUpdateDoctor() throws SQLException {
        doctor.setPhone("555-5678");
        assertDoesNotThrow(() -> doctorDAO.updateDoctor(doctor));
    }

    @Test
    public void testListDoctorDataByName() throws SQLException {
        List<Doctor> doctors = doctorDAO.listDoctorDataByName("Smith");
        assertTrue(doctors.size() > 0);
    }

    @Test
    public void testDeleteDoctor() throws SQLException {
        assertDoesNotThrow(() -> doctorDAO.deleteDoctor(doctor.getId()));
    }
}