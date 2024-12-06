package com.hospitalsystem.View.List;

import java.sql.SQLException;
import java.util.List;

import com.hospitalsystem.Controller.DoctorController;
import com.hospitalsystem.Model.Doctor;

public class ListDoctor {
    public void listAllDoctors() {
        DoctorController doctorController = new DoctorController();

        try {
            List<Doctor> doctors = doctorController.listAllDoctors();
            System.out.println("\n=== Registered doctors ===\n");
            for (Doctor doctor : doctors) {
                System.out.println("|| ID: " + doctor.getId() + " // " + "Name: " + doctor.getName() + " // " + "Specialty: " + doctor.getSpecialty());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listAllDoctorsForSpecialty() {
        DoctorController doctorController = new DoctorController();

        try {
            List<Doctor> doctors = doctorController.listAllDoctors();
            System.out.println("\n=== Registered doctors ===");
            for (Doctor doctor : doctors) {
                System.out.println("|| " + "Name: " + doctor.getName() + " // " + "Specialty: " + doctor.getSpecialty());
                System.out.println("");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
