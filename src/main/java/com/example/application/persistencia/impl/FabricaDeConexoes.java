package com.example.application.persistencia.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class FabricaDeConexoes {

    private final static String URL = "jdbc:sqlite:db/dblocadora.db";

    public static Connection obterConexao() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
