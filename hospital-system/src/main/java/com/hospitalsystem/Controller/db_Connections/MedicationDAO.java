package com.hospitalsystem.Controller.db_Connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hospitalsystem.Model.Medication;
import com.hospitalsystem.Model.Patient;
import com.hospitalsystem.Model.Appointment;

// Class to connect to the database
public class MedicationDAO {

    // Add a medication to appointment and patient
    public void addMedicationToAppointmentAndPatient(Medication medication, Appointment appointment, Patient patient) {
        String medicationSql = "INSERT INTO medications (name, dosage, frequency, description, doctor, prescription_date) VALUES (?, ?, ?, ?, ?, ?)";
        String medicationToAppointmentSql = "INSERT INTO appointment_medications (appointment_id, medication_id) VALUES (?, ?)";
        String patientToMedicationSql = "INSERT INTO patient_medications (patient_id, medication_id) VALUES (?, ?)";
        
        try (Connection conn = db_Connection.getConnection()) {

            conn.setAutoCommit(false);

            try (PreparedStatement medicationStmt = conn.prepareStatement(medicationSql,
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
                medicationStmt.setString(1, medication.getMedicationName());
                medicationStmt.setString(2, medication.getDosage());
                medicationStmt.setString(3, medication.getFrequency());
                medicationStmt.setString(4, medication.getDescription());
                medicationStmt.setString(5, medication.getDoctor());
                medicationStmt.setString(6, medication.getPrescriptionDate());

                medicationStmt.executeUpdate();

                ResultSet generatedKeys = medicationStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    medication.setId(generatedKeys.getInt(1));
                }
            }

            try (PreparedStatement relationStmt = conn.prepareStatement(medicationToAppointmentSql)) {
                relationStmt.setInt(1, appointment.getId());
                relationStmt.setInt(2, medication.getId());

                relationStmt.executeUpdate();
            }

            try (PreparedStatement relationStmt = conn.prepareStatement(patientToMedicationSql)) {
                relationStmt.setInt(1, patient.getId());
                relationStmt.setInt(2, medication.getId());

                relationStmt.executeUpdate();
            }

            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // List medications from a patient
    public List<Medication> listMedicationsByPatientName(String patientName) throws SQLException {
        List<Medication> medications = new ArrayList<>();
        String sql = "SELECT m.id, m.name AS medication_name, m.dosage, m.frequency, m.description, m.doctor, m.prescription_date "
                +
                "FROM hospital_system.patients p " +
                "JOIN hospital_system.patient_medications pm ON p.id = pm.patient_id " +
                "JOIN hospital_system.medications m ON pm.medication_id = m.id " +
                "WHERE p.name = ?";

        try (Connection conn = db_Connection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, patientName);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Medication medication = new Medication(
                            rs.getInt("id"),
                            rs.getString("medication_name"),
                            rs.getString("dosage"),
                            rs.getString("frequency"),
                            rs.getString("description"),
                            rs.getString("doctor"),
                            rs.getString("prescription_date"));
                    medications.add(medication);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error listing medications: " + e.getMessage());
        }

        return medications;
    }

    // List medications from an appointment
    public List<Medication> listMedicationsByAppointmentId(int appointmentId) throws SQLException {
        List<Medication> medications = new ArrayList<>();
        String sql = "SELECT m.id, m.name AS medication_name, m.dosage, m.frequency, m.description, m.doctor, m.prescription_date "
                + "FROM hospital_system.appointment_medications am "
                + "JOIN hospital_system.medications m ON am.medication_id = m.id "
                + "WHERE am.appointment_id = ?";
    
        try (Connection conn = db_Connection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, appointmentId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Medication medication = new Medication(
                            rs.getInt("id"),
                            rs.getString("medication_name"),
                            rs.getString("dosage"),
                            rs.getString("frequency"),
                            rs.getString("description"),
                            rs.getString("doctor"),
                            rs.getString("prescription_date"));
                    medications.add(medication);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error listing medications: " + e.getMessage());
        }
    
        return medications;
    }

    // Check if a medication belongs to a patient
    public boolean isMedicationOwnedByPatient(String patientName, int medicationId) throws SQLException {
        String sql = "SELECT COUNT(*) AS count " +
                     "FROM hospital_system.patients p " +
                     "JOIN hospital_system.patient_medications pm ON p.id = pm.patient_id " +
                     "WHERE p.name = ? AND pm.medication_id = ?";

        try (Connection conn = db_Connection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, patientName);
            stmt.setInt(2, medicationId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("count") > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error checking medication ownership: " + e.getMessage());
        }

        return false;
    }

    // Delete medication from a patient
    public void deletePatientMedication(String patientName, int medicationId) throws SQLException {
        if (!isMedicationOwnedByPatient(patientName, medicationId)) {
            System.out.println("Medication does not belong to the patient!");
            return;
        }

        String getPatientIdSql = "SELECT id FROM hospital_system.patients WHERE name = ?";
        String deletePatientMedicationSql = "DELETE FROM hospital_system.patient_medications WHERE patient_id = ? AND medication_id = ?";
        String deleteMedicationSql = "DELETE FROM hospital_system.medications WHERE id = ?";

        Connection conn = null;
        PreparedStatement getPatientIdStmt = null;
        PreparedStatement deletePatientMedicationStmt = null;
        PreparedStatement deleteMedicationStmt = null;
        ResultSet rs = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = db_Connection.getConnection();

            getPatientIdStmt = conn.prepareStatement(getPatientIdSql);
            getPatientIdStmt.setString(1, patientName);
            rs = getPatientIdStmt.executeQuery();

            if (rs.next()) {
                int patientId = rs.getInt("id");

                deletePatientMedicationStmt = conn.prepareStatement(deletePatientMedicationSql);
                deletePatientMedicationStmt.setInt(1, patientId);
                deletePatientMedicationStmt.setInt(2, medicationId);
                deletePatientMedicationStmt.executeUpdate();

                deleteMedicationStmt = conn.prepareStatement(deleteMedicationSql);
                deleteMedicationStmt.setInt(1, medicationId);
                deleteMedicationStmt.executeUpdate();

                System.out.println("Medication deleted successfully for patient!");
            } else {
                System.out.println("Patient not found!");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Error deleting medication for patient: " + e.getMessage());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (getPatientIdStmt != null) {
                getPatientIdStmt.close();
            }
            if (deletePatientMedicationStmt != null) {
                deletePatientMedicationStmt.close();
            }
            if (deleteMedicationStmt != null) {
                deleteMedicationStmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}