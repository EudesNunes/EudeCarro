package com.example.application.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.application.entity.Cliente;
import com.example.application.entity.Veiculo;
import com.example.application.persistencia.VeiculoDAO;
import com.example.application.service.VeiculoService;

@Service
public class VeiculoServiceImpl implements VeiculoService {

    @Autowired
    private VeiculoDAO veiculoDAO;

    @Override
    public List<Veiculo> getAll() {
        return veiculoDAO.listar();
    }

    @Override
    public void save(Veiculo veiculo) {
       veiculoDAO.inserir(veiculo);
    }

    @Override
    public void deletar(Veiculo veiculo) {
        veiculoDAO.remover(veiculo);
    }
    

}
