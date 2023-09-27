package com.example.application.views.main.veiculo;

import java.util.Optional;

import com.example.application.controller.VeiculoController;
import com.example.application.entity.Veiculo;
import com.example.application.enums.EnumStatus;
import com.example.application.enums.EnumTipo;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("CreateVeiculo")
@Route(value = "newVeiculo")
@Uses(Icon.class)
public class CreateVeiculoView extends Composite<VerticalLayout> {

    private final VeiculoController veiculoController;
    private Veiculo veiculoToEdit;

    private Select<EnumTipo> tipoVeiculoField = new Select<>();
    private Select<EnumStatus> statusField = new Select<>();
    private TextField marcaField = new TextField();
    private TextField combustivelField = new TextField();
    private TextField kmField = new TextField();
    private TextField modeloField = new TextField();
    private TextField renavanField = new TextField();
    private TextField placaField = new TextField();

    public CreateVeiculoView(VeiculoController veiculoController, Optional<Veiculo> veiculoOptional) {
        this.veiculoController = veiculoController;
        veiculoToEdit = veiculoOptional.orElse(null);

        // Configurar os Selects para os Enums
        tipoVeiculoField.setItems(EnumTipo.values());
        tipoVeiculoField.setValue(EnumTipo.values()[0]); 
        statusField.setItems(EnumStatus.values());
        statusField.setValue(EnumStatus.values()[0]);
        if (veiculoToEdit != null) {
            tipoVeiculoField.setValue(veiculoToEdit.getTipoVeiculo());
            marcaField.setValue(veiculoToEdit.getMarca());
            combustivelField.setValue(veiculoToEdit.getCombustivel());
            kmField.setValue(Long.toString(veiculoToEdit.getKm()));
            statusField.setValue(veiculoToEdit.getStatus());
            modeloField.setValue(veiculoToEdit.getModelo());
            renavanField.setValue(Long.toString(veiculoToEdit.getRenavan()));
            placaField.setValue(veiculoToEdit.getPlaca());
        }

        FormLayout formLayout = new FormLayout();
        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 2),  
                new FormLayout.ResponsiveStep("20em", 4)); 

        formLayout.addFormItem(tipoVeiculoField, "Tipo Veiculo");
        formLayout.addFormItem(marcaField, "Marca");
        formLayout.addFormItem(combustivelField, "Combustivel");
        formLayout.addFormItem(kmField, "KM");
        formLayout.addFormItem(statusField, "Status");
        formLayout.addFormItem(modeloField, "Modelo");
        formLayout.addFormItem(renavanField, "Renavan");
        formLayout.addFormItem(placaField, "Placa");

        H3 h3 = new H3("Cadastro de Veículos");

        Button buttonPrimary = new Button("Save");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        Button buttonSecondary = new Button("Cancel");

        VerticalLayout content = getContent();
        content.addClassName("has-padding");
        content.setSizeFull();

        VerticalLayout layoutColumn = new VerticalLayout(h3, formLayout);
        layoutColumn.setAlignItems(Alignment.CENTER);

        if (veiculoToEdit != null) {
            layoutColumn.setHorizontalComponentAlignment(Alignment.CENTER, h3);
            layoutColumn.setHorizontalComponentAlignment(Alignment.CENTER, formLayout);
        }

        HorizontalLayout buttonLayout = new HorizontalLayout(buttonPrimary, buttonSecondary);
        buttonLayout.setJustifyContentMode(JustifyContentMode.CENTER);

           buttonPrimary.addClickListener(event -> saveVeiculo());
        buttonSecondary.addClickListener(event -> UI.getCurrent().navigate("veiculos/listar"));

        content.add(layoutColumn, buttonLayout);
    }

    private void saveVeiculo() {
        EnumTipo tipoVeiculo = tipoVeiculoField.getValue();
        String marca = marcaField.getValue();
        String combustivel = combustivelField.getValue();
        String km = kmField.getValue();
        EnumStatus status = statusField.getValue();
        String modelo = modeloField.getValue();
        String renavan = renavanField.getValue();
        String placa = placaField.getValue();

        Veiculo veiculo = new Veiculo();
        veiculo.setTipoVeiculo(tipoVeiculo);
        veiculo.setMarca(marca);
        veiculo.setCombustivel(combustivel);
        veiculo.setKm(Long.parseLong(km));
        veiculo.setStatus(status);
        veiculo.setModelo(modelo);
        veiculo.setRenavan(Long.parseLong(renavan));
        veiculo.setPlaca(placa);

        veiculoController.save(veiculo);
    }
}
