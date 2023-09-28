package com.example.application.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.example.application.entity.Locacao;
import com.example.application.service.LocacaoService;
import com.vaadin.flow.component.UI;

@Controller
public class LocacaoController {

    @Autowired
    private LocacaoService service;

    public List<Locacao> listar() {
        return service.getAll();
    }
    public Optional<Locacao> obter(Long id) {
        return service.obter(id);
    }
    public void save(Locacao locacao) {
        service.save(locacao);
        UI.getCurrent().navigate("locacao/listar");
    }

    public void deletar(Locacao locacao) {
        service.deletar(locacao);
    }

     public void update(Locacao locacao) {
        service.update(locacao);
        UI.getCurrent().navigate("locacao/listar");
    }
}
