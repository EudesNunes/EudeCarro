package com.example.application.persistencia;

import java.util.List;
import java.util.Optional;


import com.example.application.entity.Veiculo;

public interface VeiculoDAO {
    List<Veiculo> listar();

    Optional<Veiculo> obter(Long id);

    boolean inserir(Veiculo veiculo);

    boolean alterar(Veiculo veiculo);

    boolean remover(Veiculo veiculo);
}
