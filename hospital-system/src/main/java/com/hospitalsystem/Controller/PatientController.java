package com.hospitalsystem.Controller;

import java.sql.SQLException;
import java.util.List;

import com.hospitalsystem.Model.Patient;
import com.hospitalsystem.Controller.db_Connections.PatientDAO;

public class PatientController {
    private PatientDAO patientDAO;

    public PatientController() {
        this.patientDAO = new PatientDAO();
    }

    public void addPatient(Patient patient) throws SQLException {
        patientDAO.addPatient(patient);
    }

    public Patient findPatientByName(String name) throws SQLException {
        return patientDAO.findPatientByName(name);
    }

    public void updatePatient(Patient patient) throws SQLException {
        patientDAO.updatePatient(patient);
    }

    public List<Patient> listPatientsByName(String name) throws SQLException {
        return patientDAO.listPatientsByName(name);
    }

    public List<Patient> listAllPatients() throws SQLException {
        return patientDAO.listAllPatients();
    }

    public void deletePatient(int patientId) throws SQLException {
        patientDAO.deletePatient(patientId);
    }
}