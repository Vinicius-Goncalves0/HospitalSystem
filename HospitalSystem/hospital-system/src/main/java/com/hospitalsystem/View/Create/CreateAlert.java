package com.hospitalsystem.View.Create;

import java.sql.SQLException;
import java.util.Scanner;

import com.hospitalsystem.Controller.AlertController;
import com.hospitalsystem.Controller.PatientController;
import com.hospitalsystem.Controller.DeviceController;
import com.hospitalsystem.Model.Alert;
import com.hospitalsystem.Model.Device;
import com.hospitalsystem.Model.Doctor;
import com.hospitalsystem.Model.Patient;
import com.hospitalsystem.Controller.db_Connections.DoctorDAO;
import com.hospitalsystem.View.List.ListPatientAppointmentMenu;

public class CreateAlert {
    PatientController patientController = new PatientController();
    DeviceController deviceController = new DeviceController();

    DoctorDAO doctorDAO = new DoctorDAO();
    ListPatientAppointmentMenu listPatientAppointmentMenu = new ListPatientAppointmentMenu();

    private AlertController alertController;

    public CreateAlert() {
        this.alertController = new AlertController();
    }

    @SuppressWarnings("resource")
    public void createAlert(String patientName, int deviceId) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("\n=== Create alert ===\n");
        System.out.println("|| Type of alert: ");
        String type = scanner.nextLine();
        System.out.println("|| Alert message: ");
        String message = scanner.nextLine();
        System.out.println("|| Doctor's name: ");
        String doctorAlert = scanner.nextLine();
        System.out.println("|| Alert date: ");
        String data = scanner.nextLine();

        try {
            Doctor doctor = doctorDAO.findDoctorByName(doctorAlert);
            if (doctor != null) {

                try {
                    Patient patient = patientController.findPatientByName(patientName);
                    Device device = deviceController.findDeviceByID(deviceId);

                    if (patient != null) {
                        Alert alert = new Alert(type, message, doctorAlert, data);
                        alertController.generateAlert(alert, device, patient);

                        System.out.println("Alert generate successfully.");
                    } else {
                        System.out.println("Patient not found.");
                    }
                } catch (SQLException e) {
                    System.out.println("Error generating alert: " + e.getMessage());
                }

            } else {
                System.out.println("\n--- Doctor " + doctorAlert + " not found in system ---\n");
            }
        } catch (SQLException e) {
            System.out.println("\n--- Error accessing the doctor: " + doctorAlert + " " + e.getMessage() + " ---\n");
        }
    }
}
