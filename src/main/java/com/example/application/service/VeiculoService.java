package com.example.application.service;

import java.util.List;

import com.example.application.entity.Cliente;
import com.example.application.entity.Veiculo;

public interface VeiculoService {

    List<Veiculo> getAll();

    void save(Veiculo veiculo);

    void deletar(Veiculo veiculo);

}
