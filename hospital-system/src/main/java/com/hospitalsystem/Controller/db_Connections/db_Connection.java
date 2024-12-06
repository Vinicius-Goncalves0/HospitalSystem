package com.hospitalsystem.Controller.db_Connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class db_Connection {

    private static final String [] URL = {"jdbc:mysql://localhost:3306/hospital_system", "jdbc:mysql://localhost:3306/hospital_system_test"};
    private static final String [] USER = {"root", "root"};
    private static final String [] PASSWORD = {"Hj2vZuCL*<7DX#c/", "Hj2vZuCL*<7DX#c/"};

    public static Connection getConnection(int base) throws SQLException {
        return DriverManager.getConnection(URL[base], USER[base], PASSWORD[base]);
    }
}
