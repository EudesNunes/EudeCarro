package com.example.application.service;

import java.util.List;
import java.util.Optional;

import com.example.application.entity.Veiculo;

public interface VeiculoService {

    List<Veiculo> getAll();

    void save(Veiculo veiculo);

    void deletar(Veiculo veiculo);

    void update(Veiculo veiculo);

    Optional<Veiculo> obter(Long id);

}
