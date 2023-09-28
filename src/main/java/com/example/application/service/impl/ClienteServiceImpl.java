package com.example.application.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.application.entity.Cliente;
import com.example.application.persistencia.ClienteDAO;
import com.example.application.persistencia.LocacaoDAO;
import com.example.application.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteDAO clienteDAO;
     @Autowired
    private LocacaoDAO locacaoDAO;

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
        if(locacaoDAO.existeCliente(cliente.getId()) == false){
            clienteDAO.remover(cliente);            
        }
    }

    @Override
    public Optional<Cliente> obter(Long id) {
        if (id == null || id <= 0) {
            return Optional.empty();
        }
        try {
            return clienteDAO.obter(id);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public void update(Cliente cliente) {
        clienteDAO.alterar(cliente);
    }

}
