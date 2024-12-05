package com.hospitalsystem.View.List;

import com.hospitalsystem.Controller.DoctorController;
import com.hospitalsystem.Model.Doctor;
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

public class ListDataDoctorTest {

    @Mock
    private DoctorController doctorController;

    @InjectMocks
    private ListDataDoctor listDataDoctor;

    private Scanner scan;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        scan = new Scanner(new ByteArrayInputStream("".getBytes()));
    }

    @Test
    public void testListDoctorsByName() throws SQLException {
        String input = "\n";
        provideInput(input);

        List<Doctor> doctors = new ArrayList<>();
        doctors.add(new Doctor("Dr. Smith", "Cardiology", "123456", "555-1234", "dr.smith@example.com"));
        when(doctorController.listDoctorDataByName("John")).thenReturn(doctors);

        assertDoesNotThrow(() -> listDataDoctor.listDoctorsByName("John"));
    }

    private void provideInput(String data) {
        InputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
        scan = new Scanner(testIn);
        listDataDoctor.scan = scan;
    }
}