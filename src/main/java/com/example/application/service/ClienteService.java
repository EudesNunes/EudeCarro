package com.example.application.service;

import java.util.List;
import java.util.Optional;

import com.example.application.entity.Cliente;

public interface ClienteService {

    List<Cliente> getAll();

    void save(Cliente cliente);

    void deletar(Cliente cliente);

    void update(Cliente cliente);

    Optional<Cliente> obter(Long id);

}
