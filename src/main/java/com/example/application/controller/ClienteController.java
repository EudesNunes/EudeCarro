package com.example.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.application.entity.Cliente;
import com.example.application.service.ClienteService;
import com.vaadin.flow.component.UI;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping("/listar")
    public List<Cliente> listar() {
        return service.getAll();
    }

    @PostMapping
    public void save(Cliente cliente) {
        service.save(cliente);
        UI.getCurrent().navigate("clientes/listar");
    }

    @PostMapping("/excluir")
    public void deletar(Cliente cliente) {
        service.deletar(cliente);
    }
}
