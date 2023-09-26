package com.example.application.persistencia;

import java.util.List;
import java.util.Optional;


import com.example.application.entity.Cliente;

public interface ClienteDAO {
    List<Cliente> listar();

    Optional<Cliente> obter(Long id);

    boolean inserir(Cliente cliente);

    boolean alterar(Cliente cliente);

    boolean remover(Cliente cliente);
}
