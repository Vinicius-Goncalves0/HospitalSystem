package com.hospitalsystem.View.Create;

import java.sql.SQLException;
import java.util.Scanner;

import com.hospitalsystem.Controller.AppointmentController;
import com.hospitalsystem.Controller.PatientController;
import com.hospitalsystem.Controller.db_Connections.DoctorDAO;
import com.hospitalsystem.Model.Doctor;
import com.hospitalsystem.Model.Appointment;
import com.hospitalsystem.Model.Patient;
import com.hospitalsystem.View.List.ListDoctor;

public class CreateAppointmentMenuByPatient {
    Scanner scan = new Scanner(System.in);
    private AppointmentController appointmentController;
    private PatientController patientController;
    CreateMedicationMenu createMedicationMenu = new CreateMedicationMenu();
    ListDoctor listDoctor = new ListDoctor();
    DoctorDAO doctorDAO = new DoctorDAO();

    public CreateAppointmentMenuByPatient() {
        this.appointmentController = new AppointmentController();
        this.patientController = new PatientController();
    }

    public void createAppointmentMenuByPatient(String patientName) {
        System.out.print("\n=== Create Appointment ===\n");
        System.out.println("|| Appointment data and time: ");
        String appointmentDataTime = scan.nextLine();
        listDoctor.listAllDoctorsForSpecialty();
        System.out.println("|| Appointment's doctor name: ");
        String appointmentDoctorName = scan.nextLine();
        System.out.println("|| Diagnosis: ");
        String diagnosis = scan.nextLine();

        try {
            Doctor doctor = doctorDAO.findDoctorByName(appointmentDoctorName);
            if (doctor != null) {

                try {
                    Patient patient = patientController.findPatientByName(patientName);
        
                    if (patient != null) {
                        Appointment appointment = new Appointment(appointmentDataTime, appointmentDoctorName, diagnosis);
                        appointmentController.addAppointmentToPatient(appointment, patient);
                        System.out.println("\nAppointment registered successfully.");
                    } else {
                        System.out.println("Patient not found.");
                    }
                } catch (SQLException e) {
                    System.out.println("Error registering appointment patient: " + e.getMessage());
                }

            } else {
                System.out.println("\n--- Doctor " + appointmentDoctorName + " not found in system ---\n");
            }
        } catch (SQLException e) {
            System.out.println("\n--- Error accessing the doctor: " + appointmentDoctorName + " " + e.getMessage()
                    + " ---\n");
        }
    }

}
