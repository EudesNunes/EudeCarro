package com.example.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.application.entity.Veiculo;
import com.example.application.service.VeiculoService;
import com.vaadin.flow.component.UI;

@Controller
@RequestMapping("/veiculos")
public class VeiculoController {
    
    @Autowired
    private VeiculoService service;

    @GetMapping("/listar")
    public List<Veiculo> listar() {
        return service.getAll();
    }

    @PostMapping
    public void save(Veiculo veiculo) {
        service.save(veiculo);
        UI.getCurrent().navigate("veiculos/listar");
    }

    @PostMapping("/excluir")
    public void deletar(Veiculo veiculo) {
        service.deletar(veiculo);
    }
}
