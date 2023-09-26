package com.example.application.views.main.cliente;

import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Optional;

@Route("clientes/listar")
public class ClienteListView extends VerticalLayout {
    private final ClienteController clienteController;
    private Grid<Cliente> grid;

    @Autowired
    public ClienteListView(ClienteController clienteController) {
        this.clienteController = clienteController;

        grid = new Grid<>(Cliente.class);
        NavBar navBar = new NavBar();
        add(navBar);

        // Obtenha a lista de clientes do controller
        List<Cliente> clientes = (List<Cliente>) clienteController.listar();

        // Crie um DataProvider a partir da lista de clientes
        ListDataProvider<Cliente> dataProvider = DataProvider.ofCollection(clientes);
        grid.setDataProvider(dataProvider);
        grid.setColumns("id", "nome", "email", "telefone", "cpf", "numCnh");

        grid.addComponentColumn(cliente -> {
            Button editarButton = new Button("Editar");
            editarButton.addClickListener(e -> {
                Optional<Cliente> clienteOptional = Optional.of(cliente);

                CreateClienteView createClienteView = new CreateClienteView(clienteController, clienteOptional);
                
                UI.getCurrent().add(createClienteView);

            });
            return editarButton;
        }).setHeader("Editar");

        grid.addComponentColumn(cliente -> {
            Button excluirButton = new Button("Excluir");
            excluirButton.addClickListener(e -> {
                // Show a confirmation dialog
                ConfirmationDialog confirmationDialog = new ConfirmationDialog("Confirmação",
                        "Deseja excluir este cliente?");
                confirmationDialog.open();

                // Handle the user's choice
                confirmationDialog.addConfirmationListener(event -> {
                    if (event.isConfirmed()) {
                        clienteController.deletar(cliente);
                        // Refresh the data provider after deletion
                        grid.getDataProvider().refreshAll();
                    }
                });
            });

            return excluirButton;
        }).setHeader("Excluir");

        add(grid);
        Button novoClienteButton = new Button("Novo Cliente");
        novoClienteButton.addClickListener(e -> UI.getCurrent().navigate("newCliente"));
        add(novoClienteButton);
    }

}