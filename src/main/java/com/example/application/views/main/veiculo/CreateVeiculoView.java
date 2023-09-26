package com.example.application.views.main.veiculo;

import java.util.Optional;

import com.example.application.controller.ClienteController;
import com.example.application.controller.VeiculoController;
import com.example.application.entity.Cliente;
import com.example.application.entity.Veiculo;
import com.example.application.enums.EnumStatus;
import com.example.application.enums.EnumTipo;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;

@PageTitle("CreateVeiculo")
@Route(value = "newVeiculo")
@Uses(Icon.class)
public class CreateVeiculoView extends Composite<VerticalLayout> {

    private final VeiculoController veiculoController;
    private Veiculo veiculoToEdit;

    private TextField tipoVeiculoField = new TextField();
    private TextField marcaField = new TextField();
    private TextField combustivelField = new TextField();
    private TextField kmField = new TextField();
    private TextField statusField = new TextField();
    private TextField modeloField = new TextField();
    private TextField renavanField = new TextField();
    private TextField placaField = new TextField();

    public CreateVeiculoView(VeiculoController veiculoController, Optional<Veiculo> veiculoOptional) {
        this.veiculoController = veiculoController;
        veiculoToEdit = veiculoOptional.orElse(null);

        if (veiculoToEdit != null) {
            tipoVeiculoField.setValue(Long.toString(veiculoToEdit.getTipoVeiculo().getValor()));
            marcaField.setValue(veiculoToEdit.getMarca());
            combustivelField.setValue(veiculoToEdit.getCombustivel());
            kmField.setValue(Long.toString(veiculoToEdit.getKm()));
            statusField.setValue(Long.toString(veiculoToEdit.getStatus().getValor()));
            modeloField.setValue(veiculoToEdit.getModelo());
            renavanField.setValue(Long.toString(veiculoToEdit.getRenavan()));
            placaField.setValue(veiculoToEdit.getPlaca());
            
        }

        HorizontalLayout layoutRow = new HorizontalLayout();
        VerticalLayout layoutColumn5 = new VerticalLayout();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        H3 h3 = new H3();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        VerticalLayout layoutColumn3 = new VerticalLayout();
        VerticalLayout layoutColumn4 = new VerticalLayout();
        Button buttonPrimary = new Button("Save");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        Button buttonSecondary = new Button("Cancel");

        getContent().setWidthFull();
        getContent().addClassName(Padding.LARGE);
        layoutRow.setWidthFull();
        layoutRow.setFlexGrow(1.0, layoutColumn5);
        layoutColumn5.setWidth(null);
        layoutRow.setFlexGrow(1.0, layoutColumn2);
        layoutColumn2.setWidth(null);
        h3.setText("Cadastro de Veiculos");
        layoutRow2.setWidthFull();
        layoutRow2.addClassName(Gap.LARGE);
        layoutRow2.setFlexGrow(1.0, layoutColumn3);
        layoutColumn3.setWidth(null);



      tipoVeiculoField.setLabel("Tipo Veiculo");
      tipoVeiculoField.setWidthFull();
        
       marcaField.setLabel("Marca");
       marcaField.setWidthFull();

       combustivelField.setLabel("Combustivel");
       combustivelField.setWidthFull();

       kmField.setLabel("KM");
       kmField.setWidthFull();


       statusField.setLabel("Status");
       statusField.setWidthFull();
       layoutRow2.setFlexGrow(1.0, layoutColumn4);
       layoutColumn4.setWidth(null);
       modeloField.setLabel("Modelo");
       modeloField.setWidthFull();

       renavanField.setLabel("Renavan");
       renavanField.setWidthFull();

       placaField.setLabel("Placa");
       placaField.setWidthFull();

       buttonPrimary.addClickListener(event -> saveVeiculo());
       buttonSecondary.addClickListener(event -> {
            UI.getCurrent().navigate("veiculos/listar");
        });

        

        getContent().add(layoutRow);
        layoutRow.add(layoutColumn5);
        layoutRow.add(layoutColumn2);
        layoutColumn2.add(h3);
        layoutColumn2.add(layoutRow2);
        layoutRow2.add(layoutColumn3);
        layoutColumn3.add(tipoVeiculoField);
        layoutColumn3.add(marcaField);
        layoutColumn3.add(combustivelField);
        layoutColumn3.add(kmField);
        layoutRow2.add(layoutColumn4);

        layoutColumn4.add(statusField);
        layoutColumn4.add(modeloField);
        layoutColumn4.add(renavanField);
        layoutColumn4.add(placaField);


    }

    private void saveVeiculo() {
        // Obtenha os valores dos campos
        String tipoVeiculo = tipoVeiculoField.getValue();       
        String marca = marcaField.getValue();
        String combustivel = combustivelField.getValue();
        String km = kmField.getValue();
        String status = statusField.getValue();
        String modelo = modeloField.getValue();
        String renavan = renavanField.getValue();
        String placa = placaField.getValue();

       

        // Crie uma instância de Cliente
        Veiculo veiculo = new Veiculo();
        veiculo.setTipoVeiculo(EnumTipo.getEnumTipoByValor(Long.parseLong(tipoVeiculo)));        
        veiculo.setMarca(marca);
        veiculo.setCombustivel(combustivel);
        veiculo.setKm(Long.parseLong(km));
        veiculo.setStatus(EnumStatus.getEnumStatusByValor(Long.parseLong(status))); 
        veiculo.setModelo(modelo);
        veiculo.setRenavan(Long.parseLong(renavan));
        veiculo.setPlaca(placa);


        // Chame o método save do ClienteController
        veiculoController.save(veiculo);
    }
}
