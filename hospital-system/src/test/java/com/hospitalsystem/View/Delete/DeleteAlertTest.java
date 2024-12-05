package com.hospitalsystem.View.Delete;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hospitalsystem.Controller.AlertController;

public class DeleteAlertTest {

    private final InputStream systemIn = System.in;
    private ByteArrayInputStream testIn;
    @SuppressWarnings("unused")
    private AlertController alertController;

    @Before
    public void setUp() {
        alertController = new AlertController();
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
    public void testDeleteAlert_Success() throws SQLException {
        String patientName = "John Doe";
        String input = "1";

        provideInput(input);
        DeleteAlert deleteAlert = new DeleteAlert();

        assertDoesNotThrow(() -> deleteAlert.deleteAlert(patientName));
    }

    @Test
    public void testDeleteAlert_SQLException() {
        String patientName = "John Doe";
        String input = "1";

        provideInput(input);
        DeleteAlert deleteAlert = new DeleteAlert();

        try {
            deleteAlert.deleteAlert(patientName);
        } catch (Exception e) {
            assertTrue(e instanceof SQLException);
        }
    }
}