package com.example.application.views.main.componentes;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;

@Route
public class NavBar extends HorizontalLayout {
    public NavBar() {
        Anchor home = new Anchor("", "Home");
        Anchor cliente = new Anchor("clientes/listar", "Cliente");
        Anchor veiculo = new Anchor("veiculos/listar", "Veiculo"); 
        Anchor locacao = new Anchor("locacao/listar", "Locação");


        add(home, cliente, veiculo,locacao);
    }
}