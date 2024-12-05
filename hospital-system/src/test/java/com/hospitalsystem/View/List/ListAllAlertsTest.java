package com.hospitalsystem.View.List;

import com.hospitalsystem.Controller.AlertController;
import com.hospitalsystem.Controller.db_Connections.AlertDAO;
import com.hospitalsystem.Controller.db_Connections.PatientDAO;
import com.hospitalsystem.Model.Alert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

public class ListAllAlertsTest {

    @Mock
    private AlertController alertController;

    @Mock
    private PatientDAO patientDAO;

    @Mock
    private AlertDAO alertDAO;

    @InjectMocks
    private ListAllAlerts listAllAlerts;

    private Scanner scan;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        scan = new Scanner(new ByteArrayInputStream("".getBytes()));
    }

    @Test
    public void testListAlertsByPatientId() throws SQLException {
        String input = "\n";
        provideInput(input);

        List<Alert> alerts = new ArrayList<>();
        alerts.add(new Alert(1, "Type1", "Message1", "Doctor1", "Date1"));
        when(alertController.listAlertsByPatientId(1)).thenReturn(alerts);
        when(patientDAO.findPatientNameByID(1)).thenReturn("John Doe");

        assertDoesNotThrow(() -> listAllAlerts.listAlertsByPatientId(1));
    }

    @Test
    public void testDisplayAllAlerts() throws SQLException {
        String input = "\n";
        provideInput(input);

        List<Alert> alerts = new ArrayList<>();
        alerts.add(new Alert(1, "Type1", "Message1", "Doctor1", "Date1"));
        when(alertController.listAllAlerts()).thenReturn(alerts);
        when(alertDAO.getPatientIdByAlertId(1)).thenReturn(1);
        when(patientDAO.findPatientNameByID(1)).thenReturn("John Doe");

        assertDoesNotThrow(() -> listAllAlerts.displayAllAlerts());
    }

    private void provideInput(String data) {
        InputStream testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
        scan = new Scanner(testIn);
        listAllAlerts.scan = scan;
    }
}