package com.hospitalsystem.View.Create;

import com.hospitalsystem.Controller.db_Connections.DoctorDAO;
import com.hospitalsystem.Model.Doctor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class CreateDoctorMenuTest {

    private DoctorDAO doctorDAO;

    @BeforeEach
    public void setUp() {
        // Instanciar o DAO antes de cada teste
        doctorDAO = new DoctorDAO();
    }

    @AfterEach
    public void tearDown() {
        // Remover os doutores criados no teste para não poluir o banco de dados
        try {
            Doctor doctor = doctorDAO.findDoctorByName("Dr. John Doe");
            if (doctor != null) {
                doctorDAO.deleteDoctor(doctor.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCreateDoctor() {
        // Criar dados do doutor
        String name = "Dr. John Doe";
        String specialty = "Cardiology";
        String crm = "12345";
        String phone = "1234567890";
        String email = "doctor@example.com";

        // Criar o doutor
        Doctor doctor = new Doctor(name, specialty, crm, phone, email);

        // Executar o método para adicionar o doutor
        try {
            doctorDAO.addDoctor(doctor);

            // Verificar se o doutor foi adicionado corretamente
            Doctor retrievedDoctor = doctorDAO.findDoctorByName(name);
            assertNotNull(retrievedDoctor, "Doctor should have been added to the database.");
            assertEquals(name, retrievedDoctor.getName(), "Doctor name should match.");
            assertEquals(specialty, retrievedDoctor.getSpecialty(), "Doctor specialty should match.");
            assertEquals(crm, retrievedDoctor.getCrm(), "Doctor CRM should match.");
            assertEquals(phone, retrievedDoctor.getPhone(), "Doctor phone should match.");
            assertEquals(email, retrievedDoctor.getEmail(), "Doctor email should match.");
        } catch (SQLException e) {
            fail("Test failed due to SQL exception: " + e.getMessage());
        }
    }
}