package com.example.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.application.entity.Cliente;
import com.example.application.entity.Locacao;
import com.example.application.service.LocacaoService;
import com.vaadin.flow.component.UI;

@Controller
@RequestMapping("/locacoes")
public class LocacaoController {

    @Autowired
    private LocacaoService service;

    @GetMapping("/listar")
    public List<Locacao> listar() {
        return service.getAll();
    }

    @PostMapping
    public void save(Locacao locacao) {
        service.save(locacao);
        UI.getCurrent().navigate("locacao/listar");
    }

    @PostMapping("/excluir")
    public void deletar(Locacao locacao) {
        service.deletar(locacao);
    }
}
