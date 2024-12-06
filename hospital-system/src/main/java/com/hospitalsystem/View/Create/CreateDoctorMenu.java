package com.hospitalsystem.View.Create;

import java.sql.SQLException;
import java.util.Scanner;

import com.hospitalsystem.Controller.db_Connections.DoctorDAO;
import com.hospitalsystem.Model.Doctor;

public class CreateDoctorMenu {

    public DoctorDAO createDoctorDAO() {
        return new DoctorDAO();
    }

    Scanner scan = new Scanner(System.in);

    public void createDoctorMenu() {
        System.out.print("\n=== Create Doctor ===\n");
        System.out.println("|| Doctor's name: ");
        String name = scan.nextLine();
        System.out.println("|| Doctor's specialty: ");
        String specialty = scan.nextLine();
        System.out.println("|| Doctor's CRM (CRM/SP 123456): ");
        String crm = scan.nextLine();
        System.out.println("|| Doctor's phone ((51) 91234-5678): ");
        String phone = scan.nextLine();
        System.out.println("|| Doctor's email: ");
        String email = scan.nextLine();

        Doctor doctor = new Doctor(name, specialty, crm, phone, email);

        DoctorDAO doctorDAO = new DoctorDAO();
        try {
            doctorDAO.addDoctor(doctor);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
