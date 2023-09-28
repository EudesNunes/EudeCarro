package com.example.application.views.main.cliente;

import com.example.application.controller.ClienteController;
import com.example.application.entity.Cliente;
import com.example.application.views.main.componentes.ConfirmationDialog;
import com.example.application.views.main.componentes.NavBar;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.router.Route;
import java.util.List;

@Route("clientes/listar")
public class ClienteListView extends VerticalLayout {
    private Grid<Cliente> grid;
    private List<Cliente> clientes;
    private ListDataProvider<Cliente> dataProvider;

    public ClienteListView(ClienteController clienteController) {
        grid = new Grid<>(Cliente.class);
        NavBar navBar = new NavBar();
        add(navBar);

        clientes = (List<Cliente>) clienteController.listar();

        dataProvider = DataProvider.ofCollection(clientes);
        grid.setDataProvider(dataProvider);
        grid.setColumns("id", "nome", "email", "telefone", "cpf", "numCnh");

        grid.addComponentColumn(cliente -> {
            Button editarButton = new Button("Editar");
            editarButton.addClickListener(e -> {

                String customUrl = "newCliente/" + cliente.getId();
                UI.getCurrent().navigate(customUrl);

            });
            return editarButton;
        }).setHeader("Editar");

        grid.addComponentColumn(cliente -> {
            Button excluirButton = new Button("Excluir");
            excluirButton.addClickListener(e -> {
                ConfirmationDialog confirmationDialog = new ConfirmationDialog("Confirmação",
                        "Deseja excluir este cliente?");
                confirmationDialog.open();

                confirmationDialog.addConfirmationListener(event -> {
                    if (event.isConfirmed()) {
                        clienteController.deletar(cliente);
                        clientes = (List<Cliente>) clienteController.listar();
                        dataProvider = DataProvider.ofCollection(clientes);
                        grid.setDataProvider(dataProvider);
                        grid.getDataProvider().refreshAll();
                    }
                });
            });

            return excluirButton;
        }).setHeader("Excluir");

        add(grid);
        Button novoClienteButton = new Button("Novo Cliente");
        novoClienteButton.addClickListener(e -> UI.getCurrent().navigate("newCliente/0"));
        add(novoClienteButton);
    }

}