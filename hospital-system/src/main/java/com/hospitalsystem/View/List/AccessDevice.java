package com.hospitalsystem.View.List;

import java.util.Scanner;

import com.hospitalsystem.Controller.Monitoring;
import com.hospitalsystem.Model.Device;
import com.hospitalsystem.View.Create.CreateAlert;
import com.hospitalsystem.View.Update.UpdateDeviceValue;

public class AccessDevice {
    Scanner scan = new Scanner(System.in);
    UpdateDeviceValue updateDeviceValue = new UpdateDeviceValue();
    CreateAlert createAlert = new CreateAlert();
    ListDevicesPatientAndDetails listDevicesPatientAndDetails = new ListDevicesPatientAndDetails();

    public void displayAccessDeviceMenu(Device device, String patientName, int deviceId) {
        Monitoring monitoring = new Monitoring();
        ListAllAlerts listAllAlerts = new ListAllAlerts();

        while (true) {
            System.out.println("\n === Device ===\n");
            System.out.println("|| DEVICE: " + device.getType());
            String status = device.isActive() == true ? "Active" : "Disabled";
            System.out.println("|| STATUS: " + status + "\n");

            System.out.println("1. View device details");
            System.out.println("2. Monitoring Alerts");
            System.out.println("3. Create Alerts");
            System.out.println("4. Update Reference Values");
            System.out.println("5. Update Reference Values (For test: Random)");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            if (scan.hasNextInt()) {
                int choice = scan.nextInt();
                scan.nextLine();

                switch (choice) {
                    case 1:
                        listDevicesPatientAndDetails.listDeviceByID(deviceId);
                        break;
                    case 2:
                        monitoring.generatePatientAlert();
                        listAllAlerts.displayAllAlerts();
                        break;
                    case 3:
                        createAlert.createAlert(patientName, deviceId);
                        break;
                    case 4:
                        updateDeviceValue.updateDeviceValue(patientName, deviceId);
                        break;
                    case 5:
                        updateDeviceValue.updateDeviceValueRandom(patientName, deviceId);
                        break;
                    case 0:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scan.nextLine();
            }
        }
    }
}
