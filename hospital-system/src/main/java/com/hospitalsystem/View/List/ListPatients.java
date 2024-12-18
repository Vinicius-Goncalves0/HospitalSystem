package com.hospitalsystem.View.List;

import java.sql.SQLException;
import java.util.List;

import com.hospitalsystem.Controller.PatientController;
import com.hospitalsystem.Model.Patient;

public class ListPatients {
    public void listAllPatients() {
        PatientController patientController = new PatientController();

        try {
            List<Patient> patients = patientController.listAllPatients();
            System.out.println("\n=== Registered patients ===");
            for (Patient patient : patients) {
                System.out.println("|| ID: " + patient.getId() + " // " + "Name: " + patient.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
