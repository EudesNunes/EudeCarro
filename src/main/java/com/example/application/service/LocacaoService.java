package com.example.application.service;

import java.util.List;


import com.example.application.entity.Locacao;


public interface LocacaoService {

    List<Locacao> getAll();

    void save(Locacao locacao);
}
