package com.hospitalsystem.View.Update;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.hospitalsystem.Controller.DeviceController;

public class UpdateDeviceValueTest {

    UpdateDeviceValue updateDeviceValue;
    DeviceController mockDeviceController;

    @Before
    public void setUp() {
        // Mock do DeviceController
        mockDeviceController = mock(DeviceController.class);

        // Redireciona o System.in para simular entrada do usuário
        String simulatedInput = "50\n"; // Simula o valor digitado
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Instancia a classe real
        updateDeviceValue = new UpdateDeviceValue();

        // "Injeta" manualmente o mock do DeviceController (substituindo a instância real)
        try {
            var field = UpdateDeviceValue.class.getDeclaredField("deviceController");
            field.setAccessible(true);
            field.set(updateDeviceValue, mockDeviceController);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao injetar dependência no teste.", e);
        }
    }

    @Test
    public void testUpdateDeviceValue() {
        String patientName = "John Doe";
        int deviceId = 1;

        // Verifica se o método executa sem lançar exceções
        assertDoesNotThrow(() -> updateDeviceValue.updateDeviceValue(patientName, deviceId));

        // Verifica que o DeviceController foi chamado corretamente
        try {
            verify(mockDeviceController).updateDeviceValue(patientName, deviceId, 50);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdateDeviceValueRandom() {
        String patientName = "Jane Doe";
        int deviceId = 2;

        // Espia a classe UpdateDeviceValue para observar a chamada do método
        UpdateDeviceValue spyUpdateDeviceValue = spy(updateDeviceValue);

        // Verifica se o método executa sem lançar exceções
        assertDoesNotThrow(() -> spyUpdateDeviceValue.updateDeviceValueRandom(patientName, deviceId));

        // Não podemos prever o valor gerado, então verificamos apenas se o método foi chamado
        try {
            verify(mockDeviceController).updateDeviceValue(eq(patientName), eq(deviceId), anyInt());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
