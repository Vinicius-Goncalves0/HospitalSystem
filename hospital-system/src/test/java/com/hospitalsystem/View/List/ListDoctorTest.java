package com.hospitalsystem.View.List;

import com.hospitalsystem.Controller.DoctorController;
import com.hospitalsystem.Model.Doctor;
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

public class ListDoctorTest {

    @Mock
    private DoctorController doctorController;

    @InjectMocks
    private ListDoctor listDoctor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListAllDoctors() throws SQLException {
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor("Dr. Smith", "Cardiology", "123456", "555-1234", "dr.smith@example.com"));
        when(doctorController.listAllDoctors()).thenReturn(doctors);

        assertDoesNotThrow(() -> listDoctor.listAllDoctors());
    }
}