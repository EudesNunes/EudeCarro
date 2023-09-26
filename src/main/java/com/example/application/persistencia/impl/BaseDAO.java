package com.example.application.persistencia.impl;

import java.sql.Connection;
import java.sql.SQLException;

class BaseDAO {
      protected Connection obterConexao() throws SQLException {
        return FabricaDeConexoes.obterConexao();
    }
}
