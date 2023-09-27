package com.example.application.views.main.locacao;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.application.controller.ClienteController;
import com.example.application.controller.LocacaoController;
import com.example.application.controller.VeiculoController;
import com.example.application.entity.Locacao;
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

@Route("locacao/listar")
public class LocacaoListView extends VerticalLayout {
    private final LocacaoController locacaoController;
    private Grid<Locacao> grid;

    @Autowired
    public LocacaoListView(LocacaoController locacaoController,VeiculoController VeiculoController, ClienteController clienteController) {
        this.locacaoController = locacaoController;

        grid = new Grid<>(Locacao.class);
        NavBar navBar = new NavBar();
        add(navBar);

        List<Locacao> locacoens = (List<Locacao>) locacaoController.listar();

        ListDataProvider<Locacao> dataProvider = DataProvider.ofCollection(locacoens);
        grid.setDataProvider(dataProvider);
        grid.setColumns("id","dataSaida","dataPrevDev","dataDev","valor","veiculoAlocado","cliente");
        
        grid.addComponentColumn(locacao -> {
            Button editarButton = new Button("Editar");
            editarButton.addClickListener(e -> {
                Optional<Locacao> locacaoOptional = Optional.of(locacao);
                CreateLocacaoView createLocacaoView = new CreateLocacaoView(locacaoController, VeiculoController, clienteController, locacaoOptional);
                
                UI.getCurrent().add(createLocacaoView);
            });
            return editarButton;
        }).setHeader("Editar");

        grid.addComponentColumn(locacao -> {
            Button excluirButton = new Button("Excluir");
            excluirButton.addClickListener(e -> {
                // Show a confirmation dialog
                ConfirmationDialog confirmationDialog = new ConfirmationDialog("Confirmação",
                        "Deseja excluir essa Locação?");
                confirmationDialog.open();

                // Handle the user's choice
                confirmationDialog.addConfirmationListener(event -> {
                    if (event.isConfirmed()) {
                        locacaoController.deletar(locacao);
                        // Refresh the data provider after deletion
                        grid.getDataProvider().refreshAll();
                    }
                });
            });

            return excluirButton;
        }).setHeader("Excluir");

        add(grid);
        Button novoClienteButton = new Button("Nova Locação");
        novoClienteButton.addClickListener(e -> UI.getCurrent().navigate("newLocacao"));
        add(novoClienteButton);
    }

}