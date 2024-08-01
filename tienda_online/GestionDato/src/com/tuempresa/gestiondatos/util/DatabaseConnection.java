package com.tuempresa.gestiondatos.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:2003/tienda_online";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgresql.2003";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
