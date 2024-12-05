package com.hospitalsystem.View.List;

import com.hospitalsystem.Controller.DeviceController;
import com.hospitalsystem.Model.Device;
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

public class ListDevicesPatientAndDetailsTest {

    @Mock
    private DeviceController deviceController;

    @InjectMocks
    private ListDevicesPatientAndDetails listDevicesPatientAndDetails;

    private Scanner scan;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        scan = new Scanner(new ByteArrayInputStream("".getBytes()));
    }

    @Test
    public void testListActiveDevicesByPatientName() throws SQLException {
        String input = "\n";
        provideInput(input);

        List<Device> devices = new ArrayList<>();
        devices.add(new Device(1, "Type1", "Brand1", "Model1", true, 50, 100, 10));
        when(deviceController.listActiveDevicesByPatientName("John")).thenReturn(devices);

        assertDoesNotThrow(() -> listDevicesPatientAndDetails.listActiveDevicesByPatientName("John"));
    }

    @Test
    public void testListInactiveDevicesByPatientName() throws SQLException {
        String input = "\n";
        provideInput(input);

        List<Device> devices = new ArrayList<>();
        devices.add(new Device(1, "Type1", "Brand1", "Model1", false, 50, 100, 10));
        when(deviceController.listInactiveDevicesByPatientName("John")).thenReturn(devices);

        assertDoesNotThrow(() -> listDevicesPatientAndDetails.listInactiveDevicesByPatientName("John"));
    }

    @Test
    public void testListDeviceByID() throws SQLException {
        String input = "\n";
        provideInput(input);

        List<Device> devices = new ArrayList<>();
        devices.add(new Device(1, "Type1", "Brand1", "Model1", true, 50, 100, 10));
        when(deviceController.listDeviceById(1)).thenReturn(devices);

        assertDoesNotThrow(() -> listDevicesPatientAndDetails.listDeviceByID(1));
    }

    private void provideInput(String data) {
        InputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
        scan = new Scanner(testIn);
        listDevicesPatientAndDetails.scan = scan;
    }
}