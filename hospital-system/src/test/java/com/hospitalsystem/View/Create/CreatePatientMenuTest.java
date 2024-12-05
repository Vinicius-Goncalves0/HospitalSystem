package com.hospitalsystem.View.Create;

import com.hospitalsystem.Controller.db_Connections.PatientDAO;
import com.hospitalsystem.Model.Patient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class CreatePatientMenuTest {

    private PatientDAO patientDAO;

    @BeforeEach
    public void setUp() {
        // Instanciar o DAO antes de cada teste
        patientDAO = new PatientDAO();
    }

    @AfterEach
    public void tearDown() {
        // Remover os pacientes criados no teste para não poluir o banco de dados
        try {
            Patient patient = patientDAO.findPatientByName("John Doe");
            if (patient != null) {
                patientDAO.deletePatient(patient.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreatePatient() {
        // Criar dados do paciente
        String name = "John Doe";
        String cpf = "123.456.789-00";
        String birthDate = "1990-01-01";
        String histories = "No prior history.";
        String address = "123 Main St.";
        String phone = "123-456-7890";
        String email = "johndoe@example.com";

        // Criar o paciente
        Patient patient = new Patient(name, cpf, birthDate, address, phone, email, histories);

        // Executar o método para adicionar o paciente
        try {
            patientDAO.addPatient(patient);

            // Verificar se o paciente foi adicionado corretamente
            Patient retrievedPatient = patientDAO.findPatientByName(name);
            assertNotNull(retrievedPatient, "Patient should have been added to the database.");
            assertEquals(name, retrievedPatient.getName(), "Patient name should match.");
            assertEquals(cpf, retrievedPatient.getCPF(), "Patient CPF should match.");
            assertEquals(birthDate, retrievedPatient.getBirthDate(), "Patient birth date should match.");
            assertEquals(histories, retrievedPatient.getHistories(), "Patient history should match.");
            assertEquals(address, retrievedPatient.getAddress(), "Patient address should match.");
            assertEquals(phone, retrievedPatient.getPhone(), "Patient phone should match.");
            assertEquals(email, retrievedPatient.getEmail(), "Patient email should match.");
        } catch (SQLException e) {
            fail("Test failed due to SQL exception: " + e.getMessage());
        }
    }
}