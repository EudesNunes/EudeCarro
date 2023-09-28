package com.example.application.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.example.application.entity.Veiculo;
import com.example.application.service.VeiculoService;
import com.vaadin.flow.component.UI;

@Controller
public class VeiculoController {

    @Autowired
    private VeiculoService service;

    public List<Veiculo> listar() {
        return service.getAll();
    }

    public Optional<Veiculo> obter(Long id) {
        return service.obter(id);
    }

    public void save(Veiculo veiculo) {
        service.save(veiculo);
        UI.getCurrent().navigate("veiculos/listar");
    }

    public void deletar(Veiculo veiculo) {
        service.deletar(veiculo);
    }

    public void update(Veiculo veiculo) {
        service.update(veiculo);
        UI.getCurrent().navigate("veiculos/listar");
    }
}
