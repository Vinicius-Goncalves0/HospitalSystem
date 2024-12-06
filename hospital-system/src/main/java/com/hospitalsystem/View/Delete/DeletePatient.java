package com.hospitalsystem.View.Delete;

import java.sql.SQLException;
import java.util.Scanner;

import com.hospitalsystem.Controller.PatientController;

public class DeletePatient {

    @SuppressWarnings("resource")
    public void deletePatient(int patientId) {
        Scanner scan = new Scanner(System.in);
        PatientController patientController = new PatientController();

        System.out.println("You are about to delete a patient from the database. Are you sure you want to proceed?");
        System.out.println("Type 'Yes, I want to delete.' to confirm, or any other key to cancel.");
        String confirmation = scan.nextLine();

        if (confirmation.equals("Yes, I want to delete.")){
            try {
                patientController.deletePatient(patientId);
                System.out.println("Patient and all associated data successfully deleted.");
            } catch (SQLException e) {
                System.out.println("\n--- Error deleting the patient: " + e.getMessage() + " ---\n");
            }
        }
    }
}