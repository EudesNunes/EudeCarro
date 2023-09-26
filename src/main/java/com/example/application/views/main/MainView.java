package com.example.application.views.main;

import com.example.application.views.main.cliente.ClienteListView;
import com.example.application.views.main.componentes.NavBar;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@PageTitle("Lista de Clientes")
public class MainView extends VerticalLayout {
    private final ClienteListView clienteGrid;

    @Autowired
    public MainView(ClienteListView clienteGrid) {
        NavBar navBar = new NavBar();
        add(navBar);
        this.clienteGrid = clienteGrid;
        setSizeFull();
        add(clienteGrid);
        Button meuBotao = new Button("Novo Cliente");
        meuBotao.addClickListener(event -> {   UI.getCurrent().navigate("newCliente");});
        add(meuBotao);
    }

   

}