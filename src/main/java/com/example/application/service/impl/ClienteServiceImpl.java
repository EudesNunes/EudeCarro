package com.example.application.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.application.entity.Cliente;
import com.example.application.persistencia.ClienteDAO;
import com.example.application.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

     @Autowired
    private ClienteDAO clienteDAO;

    @Override
    public List<Cliente> getAll() {
        return clienteDAO.listar();
    }

    @Override
    public void save(Cliente cliente) {
       clienteDAO.inserir(cliente);
    }

    @Override
    public void deletar(Cliente cliente) {
        clienteDAO.remover(cliente);
    }
    
}
