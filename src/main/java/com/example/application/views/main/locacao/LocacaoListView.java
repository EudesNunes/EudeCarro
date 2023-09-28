package com.example.application.views.main.locacao;

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

@Route("locacao/listar")
public class LocacaoListView extends VerticalLayout {
    private Grid<Locacao> grid;
    private List<Locacao> locacoens;
    private ListDataProvider<Locacao> dataProvider;

    public LocacaoListView(LocacaoController locacaoController, VeiculoController VeiculoController,
            ClienteController clienteController) {
        grid = new Grid<>(Locacao.class);
        NavBar navBar = new NavBar();
        add(navBar);

        locacoens = (List<Locacao>) locacaoController.listar();

        dataProvider = DataProvider.ofCollection(locacoens);
        grid.setDataProvider(dataProvider);
        grid.setColumns("id", "dataSaida", "dataPrevDev", "dataDev", "valor", "veiculoAlocado", "cliente");

        grid.addComponentColumn(locacao -> {
            Button editarButton = new Button("Editar");
            editarButton.addClickListener(e -> {

                String customUrl = "newLocacao/" + locacao.getId();
                UI.getCurrent().navigate(customUrl);
            });
            return editarButton;
        }).setHeader("Editar");

        grid.addComponentColumn(locacao -> {
            Button excluirButton = new Button("Excluir");
            excluirButton.addClickListener(e -> {
                ConfirmationDialog confirmationDialog = new ConfirmationDialog("Confirmação",
                        "Deseja excluir essa Locação?");
                confirmationDialog.open();

                confirmationDialog.addConfirmationListener(event -> {
                    if (event.isConfirmed()) {
                        locacaoController.deletar(locacao);
                        locacoens = (List<Locacao>) locacaoController.listar();
                        dataProvider = DataProvider.ofCollection(locacoens);
                        grid.setDataProvider(dataProvider);
                        grid.getDataProvider().refreshAll();
                    }
                });
            });

            return excluirButton;
        }).setHeader("Excluir");

        add(grid);
        Button novoClienteButton = new Button("Nova Locação");
        novoClienteButton.addClickListener(e -> UI.getCurrent().navigate("newLocacao/0"));
        add(novoClienteButton);
    }

}