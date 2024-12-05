package com.hospitalsystem.View.Delete;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.hospitalsystem.Controller.DeviceController;

public class DeleteDeviceTest {

    private final InputStream systemIn = System.in;
    private ByteArrayInputStream testIn;
    @SuppressWarnings("unused")
    private DeviceController deviceController;

    @Before
    public void setUp() {
        deviceController = new DeviceController();
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
    public void testDeleteDevice_Success() throws SQLException {
        String patientName = "John Doe";
        String input = "1"; // Simulate user input for device ID

        provideInput(input);
        DeleteDevice deleteDevice = new DeleteDevice();

        assertDoesNotThrow(() -> deleteDevice.deleteDevice(patientName));
    }

    @Test
    public void testDeleteDevice_SQLException() {
        String patientName = "John Doe";
        String input = "1";

        provideInput(input);
        DeleteDevice deleteDevice = new DeleteDevice();

        try {
            deleteDevice.deleteDevice(patientName);
        } catch (Exception e) {
            assertTrue(e instanceof SQLException);
        }
    }
}