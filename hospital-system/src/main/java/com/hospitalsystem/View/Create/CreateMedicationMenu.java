package com.hospitalsystem.View.Create;

import java.sql.SQLException;
import java.util.Scanner;

import com.hospitalsystem.Controller.MedicationController;
import com.hospitalsystem.Controller.PatientController;
import com.hospitalsystem.Controller.AppointmentController;
import com.hospitalsystem.Controller.db_Connections.DoctorDAO;
import com.hospitalsystem.Model.Doctor;
import com.hospitalsystem.Model.Medication;
import com.hospitalsystem.Model.Patient;
import com.hospitalsystem.View.List.ListDoctor;
import com.hospitalsystem.View.List.ListPatientAppointmentMenu;
import com.hospitalsystem.Model.Appointment;

public class CreateMedicationMenu {
    Scanner scan = new Scanner(System.in);
    private MedicationController medicationController;
    private PatientController patientController;
    private AppointmentController appointmentController;
    DoctorDAO doctorDAO = new DoctorDAO();
    ListPatientAppointmentMenu listPatientAppointmentMenu = new ListPatientAppointmentMenu();
    ListDoctor listDoctor = new ListDoctor();
   
    

    public CreateMedicationMenu() {
        this.medicationController = new MedicationController();
        this.patientController = new PatientController();
        this.appointmentController = new AppointmentController();
    }

    public void createMedicationMenu(String patientName) {
        System.out.println("For which appointment do you want to add the prescription?");
        listPatientAppointmentMenu.listShortAppointmentsByPatientName(patientName);
        System.out.print("\n=== Create Medication ===\n");
        System.out.println("|| Appointment ID: ");
        int appointmentID = scan.nextInt();
        scan.nextLine();
        System.out.println("|| Medication name: ");
        String medicationName = scan.nextLine();
        System.out.println("|| Medication dosage: ");
        String dosage = scan.nextLine();
        System.out.println("|| Medication frequency: ");
        String frequency = scan.nextLine();
        System.out.println("|| Medication description: ");
        String description = scan.nextLine();
        listDoctor.listAllDoctors();
        System.out.println("|| Doctor name: ");
        String medicationDoctorName = scan.nextLine();
        System.out.println("|| Prescription date: ");
        String prescriptionDate = scan.nextLine();

        try {
            Doctor doctor = doctorDAO.findDoctorByName(medicationDoctorName);
            if (doctor != null) {

                try {
                    Patient patient = patientController.findPatientByName(patientName);
                    Appointment appointment = appointmentController.findAppointmentByID(appointmentID);

                    if (patient != null) {
                        Medication medication = new Medication(medicationName, dosage, frequency, description, medicationDoctorName, prescriptionDate);
                        medicationController.addMedicationToAppointmentAndPatient(medication, appointment, patient);

                        System.out.println("Medication added successfully.");
                    } else {
                        System.out.println("Patient not found.");
                    }
                } catch (SQLException e) {
                    System.out.println("Error adding medication patient: " + e.getMessage());
                }

            } else {
                System.out.println("\n--- Doctor " + medicationDoctorName + " not found in system ---\n");
            }
        } catch (SQLException e) {
            System.out.println("\n--- Error accessing the doctor: " + medicationDoctorName + " " + e.getMessage() + " ---\n");
        }
    }

    public void createMedicationMenuForAppointment(String patientName, int appointmentID) {
        System.out.print("\n=== Create Medication ===\n");
        System.out.println("|| Medication name: ");
        String medicationName = scan.nextLine();
        System.out.println("|| Medication dosage: ");
        String dosage = scan.nextLine();
        System.out.println("|| Medication frequency: ");
        String frequency = scan.nextLine();
        System.out.println("|| Medication description: ");
        String description = scan.nextLine();
        listDoctor.listAllDoctors();
        System.out.println("|| Doctor name: ");
        String medicationDoctorName = scan.nextLine();
        System.out.println("|| Prescription date: ");
        String prescriptionDate = scan.nextLine();

        try {
            Doctor doctor = doctorDAO.findDoctorByName(medicationDoctorName);
            if (doctor != null) {

                try {
                    Patient patient = patientController.findPatientByName(patientName);
                    Appointment appointment = appointmentController.findAppointmentByID(appointmentID);

                    if (patient != null) {
                        Medication medication = new Medication(medicationName, dosage, frequency, description, medicationDoctorName, prescriptionDate);
                        medicationController.addMedicationToAppointmentAndPatient(medication, appointment, patient);

                        System.out.println("Medication added successfully.");
                    } else {
                        System.out.println("Patient not found.");
                    }
                } catch (SQLException e) {
                    System.out.println("Error adding medication patient: " + e.getMessage());
                }

            } else {
                System.out.println("\n--- Doctor " + medicationDoctorName + " not found in system ---\n");
            }
        } catch (SQLException e) {
            System.out.println("\n--- Error accessing the doctor: " + medicationDoctorName + " " + e.getMessage()
                    + " ---\n");
        }
    }
}