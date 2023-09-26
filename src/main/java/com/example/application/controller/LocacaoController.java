package com.example.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.application.service.LocacaoService;

@Controller
@RequestMapping("/locacoes")
public class LocacaoController {
    
    @Autowired
    private LocacaoService service;
}
