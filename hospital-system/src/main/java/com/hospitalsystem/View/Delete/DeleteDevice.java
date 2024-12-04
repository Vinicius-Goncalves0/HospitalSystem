package com.hospitalsystem.View.Delete;

import java.sql.SQLException;
import java.util.Scanner;

import com.hospitalsystem.Controller.DeviceController;


public class DeleteDevice {

    @SuppressWarnings("resource")
    public void deleteDevice(String patientName) {
        Scanner scan = new Scanner(System.in);
        DeviceController deviceController = new DeviceController();

        System.out.println("\nDevice ID:");
        int deviceId = scan.nextInt();

        try {
            deviceController.deletePatientDevice(patientName, deviceId);
        } catch (SQLException e) {
            System.out.println("\n--- Error deleting device: " + e.getMessage() + " ---\n");
        }
    }
}
