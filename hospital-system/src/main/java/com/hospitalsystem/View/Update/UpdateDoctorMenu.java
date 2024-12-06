package com.hospitalsystem.View.Update;

import java.util.Scanner;
import java.sql.SQLException;

import com.hospitalsystem.Controller.DeviceController;
import com.hospitalsystem.Controller.DoctorController;
import com.hospitalsystem.Model.Doctor;

public class UpdateDoctorMenu {
    Scanner scan = new Scanner(System.in);
    private DoctorController doctorController;
    public DeviceController deviceController;

    public UpdateDoctorMenu() {
        this.doctorController = new DoctorController();
    }

    public void updateDoctorMenu(String doctorName) {
        System.out.println("\n=== Update Doctor ===\n");
        System.out.println("|| Doctor's new name: ");
        String name = scan.nextLine();
        System.out.println("|| Doctor's new specialty: ");
        String specialty = scan.nextLine();
        System.out.println("|| Doctor's new CRM: ");
        String crm = scan.nextLine();
        System.out.println("|| Doctor's new phone ((51) 91234-5678): ");
        String phone = scan.nextLine();
        System.out.println("|| Doctor's new email: ");
        String email = scan.nextLine();

        try {
            System.out.println("\nSearching for doctor: " + doctorName);
            Doctor doctor = doctorController.findDoctorByName(doctorName);

            if (doctor != null) {
                System.out.println("Doctor found: " + doctor.getName());
                doctor.setName(name);
                doctor.setSpecialty(specialty);
                doctor.setCrm(crm);
                doctor.setPhone(phone);
                doctor.setEmail(email);

                System.out.println("Updating doctor: " + doctor.getName());
                doctorController.updateDoctor(doctor);
                System.out.println("\nDoctor updated successfully!");
            } else {
                System.out.println("Doctor not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating doctor: " + e.getMessage());
        }
    }
}
