package com.example.application.service;
import java.util.List;


import com.example.application.entity.Cliente;


public interface ClienteService {
    List<Cliente> getAll();
    
    void save(Cliente cliente);

    void deletar(Cliente cliente);
}
