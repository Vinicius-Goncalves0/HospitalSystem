package com.hospitalsystem.View.Update;

import java.sql.SQLException;
import java.util.Scanner;

import com.hospitalsystem.Controller.MedicationController;
import com.hospitalsystem.Model.Medication;

public class UpdateMedicationMenu {
    private MedicationController medicationController;

    public UpdateMedicationMenu() {
        this.medicationController = new MedicationController();
    }

    public void updateMedication() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter Medication ID: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.print("Enter Medication Name: ");
            String name = scanner.nextLine();

            System.out.print("Enter Dosage: ");
            String dosage = scanner.nextLine();

            System.out.print("Enter Frequency: ");
            String frequency = scanner.nextLine();

            System.out.print("Enter Description: ");
            String description = scanner.nextLine();

            System.out.print("Enter Doctor: ");
            String doctor = scanner.nextLine();

            System.out.print("Enter Prescription Date (dd/mm/aaaa): ");
            String prescriptionDate = scanner.nextLine();

            Medication medication = new Medication(id, name, dosage, frequency, description, doctor, prescriptionDate);
            medicationController.updateMedication(medication);

            System.out.println("Medication updated successfully!");

        } catch (SQLException e) {
            System.out.println("Error updating medication: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
