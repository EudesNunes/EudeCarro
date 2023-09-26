package com.example.application.persistencia;

import java.util.List;
import java.util.Optional;


import com.example.application.entity.Locacao;


public interface LocacaoDAO {
    List<Locacao> listar();

    Optional<Locacao> obter(Long id);

    boolean inserir(Locacao locacao);

    boolean alterar(Locacao locacao);

    boolean remover(Locacao locacao);
}
