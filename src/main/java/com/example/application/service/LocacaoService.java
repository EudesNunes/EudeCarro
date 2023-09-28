package com.example.application.service;

import java.util.List;
import java.util.Optional;

import com.example.application.entity.Locacao;

public interface LocacaoService {

    List<Locacao> getAll();

    void save(Locacao locacao);

    void deletar(Locacao locacao);

    void update(Locacao locacao);

    Optional<Locacao> obter(Long id);

}
