package com.example.application.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.application.entity.Veiculo;
import com.example.application.persistencia.LocacaoDAO;
import com.example.application.persistencia.VeiculoDAO;
import com.example.application.service.VeiculoService;

@Service
public class VeiculoServiceImpl implements VeiculoService {

  @Autowired
  private VeiculoDAO veiculoDAO;
  @Autowired
  private LocacaoDAO locacaoDAO;

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
    if (locacaoDAO.existeVeiculo(veiculo.getId()) == false) {
      veiculoDAO.remover(veiculo);
    }
  }

  @Override
  public Optional<Veiculo> obter(Long id) {
    if (id == null || id <= 0) {
      return Optional.empty();
    }
    try {
      return veiculoDAO.obter(id);
    } catch (Exception e) {
      return Optional.empty();
    }
  }

  @Override
  public void update(Veiculo veiculo) {
    veiculoDAO.alterar(veiculo);
  }

}
