package com.hospitalsystem.View.List;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.hospitalsystem.Controller.PatientController;
import com.hospitalsystem.Model.Patient;

public class ListDataPatient {
    Scanner scan = new Scanner(System.in);

    public void displayPatients(List<Patient> patients) {
        if (patients.isEmpty()) {
            System.out.println("No patients found with the given name.");
        } else {
            for (Patient patient : patients) {
                System.out.println("\n === Patient Data ===\n");
                System.out.println("|| ID: " + patient.getId());
                System.out.println("|| Name: " + patient.getName());
                System.out.println("|| CPF: " + patient.getCPF());
                System.out.println("|| Birth Date: " + patient.getBirthDate());
                System.out.println("|| Address: " + patient.getAddress());
                System.out.println("|| Phone: " + patient.getPhone());
                System.out.println("|| Email: " + patient.getEmail());
            }
        }
    }

    public void listPatientsByName(String patientName) {
        ListDataPatient listDataPatientMenu = new ListDataPatient();
        PatientController patientController = new PatientController();

        try {
            List<Patient> patients = patientController.listPatientsByName(patientName);
            listDataPatientMenu.displayPatients(patients);
            System.out.println("\nPress enter to continue...");
            scan.nextLine();
        } catch (SQLException e) {
            System.out.println("\n--- Erro ao listar pacientes: " + e.getMessage() + " ---\n");
        }
    }
}
