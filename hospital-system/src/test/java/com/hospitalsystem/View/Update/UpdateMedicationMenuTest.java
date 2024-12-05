package com.hospitalsystem.View.Update;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayInputStream;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.hospitalsystem.Controller.MedicationController;
import com.hospitalsystem.Model.Medication;

public class UpdateMedicationMenuTest {

    UpdateMedicationMenu updateMedicationMenu;
    MedicationController mockMedicationController;

    @Before
    public void setUp() {
        // Mock do MedicationController
        mockMedicationController = mock(MedicationController.class);

        // Redireciona o System.in para simular entradas do usuário
        String simulatedInput = """
                1
                Paracetamol
                500mg
                2 times a day
                Pain relief
                Dr. John Doe
                2024-01-01
                """;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Instancia a classe real
        updateMedicationMenu = new UpdateMedicationMenu();

        // "Injeta" manualmente o mock do MedicationController
        try {
            var field = UpdateMedicationMenu.class.getDeclaredField("medicationController");
            field.setAccessible(true);
            field.set(updateMedicationMenu, mockMedicationController);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao injetar dependência no teste.", e);
        }
    }

    @Test
    public void testUpdateMedication() {
        // Verifica se o método executa sem lançar exceções
        assertDoesNotThrow(() -> updateMedicationMenu.updateMedication());

        // Captura o argumento passado para o método updateMedication
        ArgumentCaptor<Medication> medicationCaptor = ArgumentCaptor.forClass(Medication.class);
        try {
            verify(mockMedicationController).updateMedication(medicationCaptor.capture());
        } catch (SQLException e) {
            e.printStackTrace();
            fail("SQLException was thrown: " + e.getMessage());
        }

        // Obtém o Medication capturado
        Medication capturedMedication = medicationCaptor.getValue();

        // Verifica se os valores do Medication são os esperados
        assertEquals(1, capturedMedication.getId());
        assertEquals("Paracetamol", capturedMedication.getMedicationName());
        assertEquals("500mg", capturedMedication.getDosage());
        assertEquals("2 times a day", capturedMedication.getFrequency());
        assertEquals("Pain relief", capturedMedication.getDescription());
        assertEquals("Dr. John Doe", capturedMedication.getDoctor());
        assertEquals("2024-01-01", capturedMedication.getPrescriptionDate());
    }
}
