package com.hospitalsystem.Controller;

import java.sql.SQLException;
import java.util.List;

import com.hospitalsystem.Model.Appointment;
import com.hospitalsystem.Model.Medication;
import com.hospitalsystem.Model.Patient;
import com.hospitalsystem.Controller.db_Connections.MedicationDAO;

public class MedicationController {
    private MedicationDAO medicationDAO;

    public MedicationController() {
        this.medicationDAO = new MedicationDAO();
    }

    public void addMedicationToAppointmentAndPatient(Medication medication, Appointment appointment, Patient patient) throws SQLException {
        medicationDAO.addMedicationToAppointmentAndPatient(medication, appointment, patient);
    }

    public List<Medication> listMedicationsByPatientName(String patientName) throws SQLException {
        return medicationDAO.listMedicationsByPatientName(patientName);
    }

    public List<Medication> listMedicationsByAppointmentId(int appointmentId) throws SQLException {
        return medicationDAO.listMedicationsByAppointmentId(appointmentId);
    }

    public void deletePatientMedication(String patientName, int medicationId) throws SQLException {
        medicationDAO.deletePatientMedication(patientName, medicationId);
    }
}