package com.example.application.views.main.locacao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.application.controller.ClienteController;
import com.example.application.controller.LocacaoController;
import com.example.application.controller.VeiculoController;
import com.example.application.entity.Cliente;
import com.example.application.entity.Locacao;
import com.example.application.entity.Veiculo;
import com.example.application.enums.EnumTipo;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.time.LocalDate;
import java.time.ZoneId;

@PageTitle("CreateLocacao")
@Route(value = "newLocacao")
@Uses(Icon.class)
public class CreateLocacaoView extends Composite<VerticalLayout> {

    @Autowired
    private final LocacaoController locacaoController;
    private Locacao locacaoToEdit;

    private DatePicker dataSaidaField = new DatePicker();
    private DatePicker dataPrevDevField = new DatePicker();
    private DatePicker dataDevField = new DatePicker();
    private NumberField valorField = new NumberField();
    private Select<Veiculo> veiculoAlocadoField = new Select<>();
    private Select<Cliente> clienteAlocadoField = new Select<>();

    public CreateLocacaoView(LocacaoController locacaoController, VeiculoController veiculoController,
            ClienteController clienteController,
            Optional<Locacao> locacaoOptional) {
        this.locacaoController = locacaoController;
        locacaoToEdit = locacaoOptional.orElse(null);

        veiculoAlocadoField.setLabel("Veículo alocado");
        veiculoAlocadoField.setItemLabelGenerator(veiculo -> veiculo.getId() + " - " + veiculo.getModelo());
        List<Veiculo> veiculosDisponiveis = veiculoController.listar();
        veiculoAlocadoField.setItems(veiculosDisponiveis);

        clienteAlocadoField.setLabel("Cliente alocado");
        clienteAlocadoField.setItemLabelGenerator(cliente -> cliente.getId() + " - " + cliente.getNome());
        List<Cliente> clienteDisponiveis = clienteController.listar();
        clienteAlocadoField.setItems(clienteDisponiveis);

        if (locacaoToEdit != null) {
            dataSaidaField
                    .setValue(locacaoToEdit.getDataSaida().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            dataPrevDevField
                    .setValue(locacaoToEdit.getDataPrevDev().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            dataDevField.setValue(locacaoToEdit.getDataDev().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            valorField.setValue(locacaoToEdit.getValor());

        }

        FormLayout formLayout = new FormLayout();
        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 2),
                new FormLayout.ResponsiveStep("20em", 4));

        formLayout.addFormItem(dataSaidaField, "Data da Saída");
        formLayout.addFormItem(dataPrevDevField, "Data prevista da Devolução");
        formLayout.addFormItem(dataDevField, "Data real da Devolução");
        formLayout.addFormItem(valorField, "Valor");
        formLayout.addFormItem(veiculoAlocadoField, "Veículo alocado");
        formLayout.addFormItem(clienteAlocadoField, "Cliente alocado");

        H3 h3 = new H3("Cadastro da Locação");

        Button buttonPrimary = new Button("Save");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        Button buttonSecondary = new Button("Cancel");

        VerticalLayout content = getContent();
        content.addClassName("has-padding");
        content.setSizeFull();

        VerticalLayout layoutColumn = new VerticalLayout(h3, formLayout);
        layoutColumn.setAlignItems(Alignment.CENTER);

        if (locacaoToEdit != null) {
            layoutColumn.setHorizontalComponentAlignment(Alignment.CENTER, h3);
            layoutColumn.setHorizontalComponentAlignment(Alignment.CENTER, formLayout);
        }

        HorizontalLayout buttonLayout = new HorizontalLayout(buttonPrimary, buttonSecondary);
        buttonLayout.setJustifyContentMode(JustifyContentMode.CENTER);

        buttonPrimary.addClickListener(event -> savelocacao());
        buttonSecondary.addClickListener(event -> UI.getCurrent().navigate("locacao/listar"));

        content.add(layoutColumn, buttonLayout);
    }

    private void savelocacao() {
        LocalDate dataDev = dataDevField.getValue();
        LocalDate dataPrevDev = dataPrevDevField.getValue();
        LocalDate dataSaida = dataSaidaField.getValue();
        double valor = valorField.getValue();
        Veiculo veiculo = veiculoAlocadoField.getValue();
        Cliente cliente = clienteAlocadoField.getValue();

        Date dataDevDate = Date.from(dataDev.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date dataPrevDevDate = Date.from(dataPrevDev.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date dataSaidaDate = Date.from(dataSaida.atStartOfDay(ZoneId.systemDefault()).toInstant());
        
        Locacao locacao = new Locacao();
        locacao.setDataDev(dataDevDate);
        locacao.setDataPrevDev(dataPrevDevDate);
        locacao.setDataSaida(dataSaidaDate);
        locacao.setValor(valor);
        locacao.setVeiculoAlocado(veiculo);
        locacao.setCliente(cliente);

        locacaoController.save(locacao);
    }

}
