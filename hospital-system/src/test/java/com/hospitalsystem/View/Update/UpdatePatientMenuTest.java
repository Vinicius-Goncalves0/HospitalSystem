package com.hospitalsystem.View.Update;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;  // Para assertEquals

import java.io.ByteArrayInputStream;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.hospitalsystem.Controller.PatientController;
import com.hospitalsystem.Model.Patient;

public class UpdatePatientMenuTest {

    UpdatePatientMenu updatePatientMenu;
    PatientController mockPatientController;

    @Before
    public void setUp() {
        // Mock do PatientController
        mockPatientController = mock(PatientController.class);

        // Simula a entrada do usuário para as perguntas sobre o paciente
        String simulatedInput = """
                João Silva
                123.456.789-00
                1990-01-01
                Rua Exemplo, 123
                (11) 91234-5678
                joao.silva@email.com
                """;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Instancia a classe real
        updatePatientMenu = new UpdatePatientMenu();

        // "Injeta" manualmente o mock do PatientController
        try {
            var field = UpdatePatientMenu.class.getDeclaredField("patientController");
            field.setAccessible(true);
            field.set(updatePatientMenu, mockPatientController);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao injetar dependência no teste.", e);
        }
    }

    @Test
    public void testUpdatePatientMenu() throws SQLException {
        // Simula a busca do paciente
        Patient existingPatient = new Patient("João Silva", "123.456.789-00", "1990-01-01", "Rua Velha, 45", "(11) 98765-4321", "joao@email.com", "historias");
        when(mockPatientController.findPatientByName("João Silva")).thenReturn(existingPatient);

        // Verifica se o método executa sem lançar exceções
        assertDoesNotThrow(() -> updatePatientMenu.updatePatientMenu("João Silva"));

        // Verifica se o método updatePatient foi chamado
        ArgumentCaptor<Patient> patientCaptor = ArgumentCaptor.forClass(Patient.class);
        verify(mockPatientController).updatePatient(patientCaptor.capture());

        // Obtém o paciente capturado
        Patient capturedPatient = patientCaptor.getValue();

        // Verifica se os dados do paciente foram atualizados corretamente
        assertEquals("João Silva", capturedPatient.getName());
        assertEquals("123.456.789-00", capturedPatient.getCPF());
        assertEquals("1990-01-01", capturedPatient.getBirthDate());
        assertEquals("Rua Exemplo, 123", capturedPatient.getAddress());
        assertEquals("(11) 91234-5678", capturedPatient.getPhone());
        assertEquals("joao.silva@email.com", capturedPatient.getEmail());
    }

    @Test
    public void testPatientNotFound() throws SQLException {
        // Simula a situação onde o paciente não é encontrado
        when(mockPatientController.findPatientByName("João Silva")).thenReturn(null);

        // Verifica se o método executa sem lançar exceções
        assertDoesNotThrow(() -> updatePatientMenu.updatePatientMenu("João Silva"));

        // Verifica que o método updatePatient não foi chamado, pois o paciente não foi encontrado
        verify(mockPatientController, never()).updatePatient(any(Patient.class));
    }
}
