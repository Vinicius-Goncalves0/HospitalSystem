package com.hospitalsystem.View.Update;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.hospitalsystem.Controller.DeviceController;

public class UpdateDeviceStatusTest {

    UpdateDeviceStatus updateDeviceStatus;
    DeviceController mockDeviceController;

    @Before
    public void setUp() {
        mockDeviceController = mock(DeviceController.class);

        String simulatedInput = "1\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        updateDeviceStatus = new UpdateDeviceStatus();
        updateDeviceStatus.deviceController = mockDeviceController;
    }

    @Test
    public void testUpdateDeviceStatusToActive() {
        String patientName = "John Doe";

        assertDoesNotThrow(() -> updateDeviceStatus.updateDeviceStatusToActive(patientName));

        try {
            verify(mockDeviceController).activateDevice(patientName, 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdateDeviceStatusToInactive() {
        String patientName = "Jane Doe";

        assertDoesNotThrow(() -> updateDeviceStatus.updateDeviceStatusToInactive(patientName));

        try {
            verify(mockDeviceController).deactivateDevice(patientName, 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
