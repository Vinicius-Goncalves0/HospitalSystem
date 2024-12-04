package com.hospitalsystem.View.Update;

import java.util.Scanner;
import java.sql.SQLException;

import com.hospitalsystem.Controller.PatientController;
import com.hospitalsystem.Model.Patient;

public class UpdatePatientMenu {
    Scanner scan = new Scanner(System.in);
    private PatientController patientController;

    public UpdatePatientMenu() {
        this.patientController = new PatientController();
    }

    public void updatePatientMenu(String patientName) {
        System.out.println("\n=== Update Patient ===\n");
        System.out.println("Patient's new name: ");
        String name = scan.nextLine();
        System.out.println("Patient's new CPF: ");
        String cpf = scan.nextLine();
        System.out.println("Patient's new birth date: ");
        String birthDate = scan.nextLine();
        System.out.println("Patient's new address: ");
        String address = scan.nextLine();
        System.out.println("Patient's new phone: ");
        String phone = scan.nextLine();
        System.out.println("Patient's new email: ");
        String email = scan.nextLine();

        try {
            System.out.println("\nSearching for patient: " + patientName);
            Patient patient = patientController.findPatientByName(patientName);

            if (patient != null) {
                System.out.println("Patient found: " + patient.getName());
                patient.setName(name);
                patient.setCPF(cpf);
                patient.setBirthDate(birthDate);
                patient.setAddress(address);
                patient.setPhone(phone);
                patient.setEmail(email);

                System.out.println("Updating patient: " + patient.getName());
                patientController.updatePatient(patient);
                System.out.println("\nPatient updated successfully!");
            } else {
                System.out.println("Patient not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating patient: " + e.getMessage());
        }
    }
}
