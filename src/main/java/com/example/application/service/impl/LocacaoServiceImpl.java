package com.example.application.service.impl;

import java.util.List;
import java.util.Optional;

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

   @Override
   public void deletar(Locacao locacao) {
      locacaoDAO.remover(locacao);

   }

   @Override
   public Optional<Locacao> obter(Long id) {
      if (id == null || id <= 0) {
         return Optional.empty();
       }
       try {
         return locacaoDAO.obter(id);
       } catch (Exception e) {
         return Optional.empty();
       }
   }

   @Override
   public void update(Locacao locacao) {
      locacaoDAO.alterar(locacao);
   }

}
