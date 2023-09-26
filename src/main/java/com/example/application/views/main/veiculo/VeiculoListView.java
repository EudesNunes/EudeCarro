package com.example.application.views.main.veiculo;


import org.springframework.beans.factory.annotation.Autowired;
import com.example.application.controller.ClienteController;
import com.example.application.controller.VeiculoController;
import com.example.application.entity.Cliente;
import com.example.application.entity.Veiculo;
import com.example.application.enums.EnumStatus;
import com.example.application.enums.EnumTipo;
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

@Route("veiculos/listar")
public class VeiculoListView extends VerticalLayout {
    private final VeiculoController veiculoController;
    private Grid<Veiculo> grid;

    @Autowired
    public VeiculoListView(VeiculoController veiculoController) {
        this.veiculoController = veiculoController;

        grid = new Grid<>(Veiculo.class);
        NavBar navBar = new NavBar();
        add(navBar);

        // Obtenha a lista de clientes do controller
        List<Veiculo> veiculos = (List<Veiculo>) veiculoController.listar();

        // Crie um DataProvider a partir da lista de clientes
        ListDataProvider<Veiculo> dataProvider = DataProvider.ofCollection(veiculos);
        grid.setDataProvider(dataProvider);
        grid.setColumns("id","tipoVeiculo","marca","combustivel","km","status","modelo","renavan","placa");

        grid.addComponentColumn(veiculo -> {
            Button editarButton = new Button("Editar");
            editarButton.addClickListener(e -> {
                Optional<Veiculo> veiculoOptional = Optional.of(veiculo);

                CreateVeiculoView createVeiculoView = new CreateVeiculoView(veiculoController, veiculoOptional);
                
                UI.getCurrent().add(createVeiculoView);

            });
            return editarButton;
        }).setHeader("Editar");

        grid.addComponentColumn(veiculo -> {
            Button excluirButton = new Button("Excluir");
            excluirButton.addClickListener(e -> {
                // Show a confirmation dialog
                ConfirmationDialog confirmationDialog = new ConfirmationDialog("Confirmação",
                        "Deseja excluir este veiculo?");
                confirmationDialog.open();

                // Handle the user's choice
                confirmationDialog.addConfirmationListener(event -> {
                    if (event.isConfirmed()) {
                        veiculoController.deletar(veiculo);
                        // Refresh the data provider after deletion
                        grid.getDataProvider().refreshAll();
                    }
                });
            });

            return excluirButton;
        }).setHeader("Excluir");

        add(grid);
        Button novoClienteButton = new Button("Novo Veiculo");
        novoClienteButton.addClickListener(e -> UI.getCurrent().navigate("newVeiculo"));
        add(novoClienteButton);
    }

}