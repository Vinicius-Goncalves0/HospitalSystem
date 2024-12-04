package com.hospitalsystem.View.Update;

import java.util.Scanner;
import java.sql.SQLException;

import com.hospitalsystem.Controller.DeviceController;

public class UpdateDeviceStatus {
    Scanner scan = new Scanner(System.in);

    DeviceController deviceController = new DeviceController();

    public void updateDeviceStatusToActive(String patientName) {
        System.out.println("\nEnter the devices ID to update: ");
        int id = scan.nextInt();

        try {
            deviceController.activateDevice(patientName, id);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to update device status due to a database error.");
        }
    }

    public void updateDeviceStatusToInactive(String patientName) {
        System.out.println("\nEnter the devices ID to update: ");
        int id = scan.nextInt();

        try {
            deviceController.deactivateDevice(patientName, id);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to update device status due to a database error.");
        }
    }
}
