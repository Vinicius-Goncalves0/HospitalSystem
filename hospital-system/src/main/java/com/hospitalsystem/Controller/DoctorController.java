package com.hospitalsystem.Controller;

import java.sql.SQLException;
import java.util.List;

import com.hospitalsystem.Controller.db_Connections.DoctorDAO;
import com.hospitalsystem.Model.Doctor;

public class DoctorController {

    private DoctorDAO doctorDAO;

    public DoctorController() {
        this.doctorDAO = new DoctorDAO();
    }

    public void addDoctor(Doctor doctor) throws SQLException {
        doctorDAO.addDoctor(doctor);
    }

    public Doctor findDoctorByName(String name) throws SQLException {
        return doctorDAO.findDoctorByName(name);
    }

    public void updateDoctor(Doctor doctor) throws SQLException {
        doctorDAO.updateDoctor(doctor);
    }

    public List<Doctor> listDoctorDataByName(String name) throws SQLException {
        return doctorDAO.listDoctorDataByName(name);
    }

    public List<Doctor> listAllDoctors() throws SQLException {
        return doctorDAO.listAllDoctors();
    }
}
