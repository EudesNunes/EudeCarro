package com.example.application.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.example.application.entity.Cliente;
import com.example.application.service.ClienteService;
import com.vaadin.flow.component.UI;

@Controller
public class ClienteController {

    @Autowired
    private ClienteService service;

    public List<Cliente> listar() {
        return service.getAll();
    }

    public Optional<Cliente> obter(Long id) {
        return service.obter(id);
    }

    public void save(Cliente cliente) {
        service.save(cliente);
        UI.getCurrent().navigate("clientes/listar");
    }

    public void deletar(Cliente cliente) {
        service.deletar(cliente);
    }

    public void update(Cliente cliente) {
        service.update(cliente);
        UI.getCurrent().navigate("clientes/listar");
    }
}
