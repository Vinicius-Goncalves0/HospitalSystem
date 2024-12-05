package com.hospitalsystem.View.List;

import com.hospitalsystem.Controller.Monitoring;
import com.hospitalsystem.Model.Device;
import com.hospitalsystem.View.Create.CreateAlert;
import com.hospitalsystem.View.Update.UpdateDeviceValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class AccessDeviceTest {

    @Mock
    private UpdateDeviceValue updateDeviceValue;

    @Mock
    private CreateAlert createAlert;

    @Mock
    private ListDevicesPatientAndDetails listDevicesPatientAndDetails;

    @Mock
    private Monitoring monitoring;

    @InjectMocks
    private AccessDevice accessDevice;

    private Scanner scan;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        scan = new Scanner(new ByteArrayInputStream("".getBytes()));
        accessDevice.scan = scan;
    }

    @Test
    public void testDisplayAccessDeviceMenu() {
        String input = "1\n0\n";
        provideInput(input);

        Device device = new Device(1, "Type1", "Brand1", "Model1", true, 50, 100, 10);

        assertDoesNotThrow(() -> accessDevice.displayAccessDeviceMenu(device, "John Doe", 1));
    }

    private void provideInput(String data) {
        InputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
        scan = new Scanner(testIn);
        accessDevice.scan = scan;
    }
}