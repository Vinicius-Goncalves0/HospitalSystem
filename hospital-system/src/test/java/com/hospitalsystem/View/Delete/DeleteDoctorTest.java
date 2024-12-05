package com.hospitalsystem.View.Delete;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hospitalsystem.Controller.db_Connections.DoctorDAO;

public class DeleteDoctorTest {
    
    private final InputStream systemIn = System.in;
    private ByteArrayInputStream testIn;
    @SuppressWarnings("unused")
    private DoctorDAO doctorDAO;

    @Before
    public void setUp() {
        doctorDAO = new DoctorDAO();
    }

    @After
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @Test
    public void testDeleteDoctor_Success() throws SQLException {
        int doctorId = 1;
        String input = "Yes, I want to delete.";

        provideInput(input);
        DeleteDoctor deleteDoctor = new DeleteDoctor();

        assertDoesNotThrow(() -> deleteDoctor.deleteDoctor(doctorId));
    }

    @Test
    public void testDeleteDoctor_Cancel() {
        int doctorId = 1;
        String input = "no";

        provideInput(input);
        DeleteDoctor deleteDoctor = new DeleteDoctor();

        deleteDoctor.deleteDoctor(doctorId);
    }

    @Test
    public void testDeleteDoctor_SQLException() {
        int doctorId = 1;
        String input = "Yes, I want to delete.";

        provideInput(input);
        DeleteDoctor deleteDoctor = new DeleteDoctor();

        try {
            deleteDoctor.deleteDoctor(doctorId);
        } catch (Exception e) {
            assertTrue(e instanceof SQLException);
        }
    }
}