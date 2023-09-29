package com.example.application.views.main.veiculo;

import com.example.application.controller.VeiculoController;
import com.example.application.entity.Veiculo;
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

@Route("veiculos/listar")
public class VeiculoListView extends VerticalLayout {
    private Grid<Veiculo> grid;
    private List<Veiculo> veiculos;
    private ListDataProvider<Veiculo> dataProvider;

    public VeiculoListView(VeiculoController veiculoController) {
        grid = new Grid<>(Veiculo.class);
        NavBar navBar = new NavBar();
        add(navBar);

        veiculos = (List<Veiculo>) veiculoController.listar();

        dataProvider = DataProvider.ofCollection(veiculos);
        grid.setDataProvider(dataProvider);
        grid.setColumns("id", "tipoVeiculo", "marca", "combustivel", "km", "status", "modelo", "renavan", "placa");

        grid.addComponentColumn(veiculo -> {
            Button editarButton = new Button("Editar");
            editarButton.addClickListener(e -> {

                String customUrl = "newVeiculo/" + veiculo.getId();
                UI.getCurrent().navigate(customUrl);
            });
            return editarButton;
        }).setHeader("Editar");

        grid.addComponentColumn(veiculo -> {
            Button excluirButton = new Button("Excluir");
            excluirButton.addClickListener(e -> {
                ConfirmationDialog confirmationDialog = new ConfirmationDialog("Confirmação",
                        "Deseja excluir este veiculo?");
                confirmationDialog.open();

                confirmationDialog.addConfirmationListener(event -> {
                    if (event.isConfirmed()) {
                        veiculoController.deletar(veiculo);
                        veiculos = (List<Veiculo>) veiculoController.listar();
                        dataProvider = DataProvider.ofCollection(veiculos);
                        grid.setDataProvider(dataProvider);
                        grid.getDataProvider().refreshAll();
                    }
                });
            });

            return excluirButton;
        }).setHeader("Excluir");

        add(grid);
        Button novoClienteButton = new Button("Novo Veículo");
        novoClienteButton.addClickListener(e -> UI.getCurrent().navigate("newVeiculo/0"));
        add(novoClienteButton);
    }

}