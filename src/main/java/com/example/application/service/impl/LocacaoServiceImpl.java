package com.example.application.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.application.entity.Locacao;
import com.example.application.persistencia.LocacaoDAO;
import com.example.application.service.LocacaoService;

@Service
public class LocacaoServiceImpl implements LocacaoService {

    @Autowired
    private LocacaoDAO locacaoDAO;

    @Override
    public List<Locacao> getAll() {
       return locacaoDAO.listar();
    }

    @Override
    public void save(Locacao locacao) {
       locacaoDAO.inserir(locacao);
    }

}
