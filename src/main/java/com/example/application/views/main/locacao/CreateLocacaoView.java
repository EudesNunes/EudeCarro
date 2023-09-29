package com.example.application.views.main.locacao;

import java.time.LocalDate;
import java.time.ZoneId;
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
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("CreateLocacao")
@Route(value = "newLocacao/:id")
@Uses(Icon.class)
public class CreateLocacaoView extends Composite<VerticalLayout> implements BeforeEnterObserver {

    @Autowired
    private final LocacaoController locacaoController;
    private Locacao locacaoToEdit;
    private String locacaoId;

    private DatePicker dataSaidaField = new DatePicker();
    private DatePicker dataPrevDevField = new DatePicker();
    private DatePicker dataDevField = new DatePicker();
    private NumberField valorField = new NumberField();
    private Select<Veiculo> veiculoAlocadoField = new Select<>();
    private Select<Cliente> clienteAlocadoField = new Select<>();

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        locacaoId = event.getRouteParameters().get("id").orElse(null);

        if (locacaoId != null) {
            Long id = Long.parseLong(locacaoId);
            locacaoToEdit = locacaoController.obter(id).orElse(null);

            if (locacaoToEdit != null) {

                java.util.Date dataSaidaUtil = locacaoToEdit.getDataSaida();
                java.sql.Date dataSaidaSql = new java.sql.Date(dataSaidaUtil.getTime());
                LocalDate dataSaida = dataSaidaSql.toLocalDate();
                dataSaidaField.setValue(dataSaida);

                java.util.Date dataPrevDevUtil = locacaoToEdit.getDataSaida();
                java.sql.Date dataPrevDevSql = new java.sql.Date(dataPrevDevUtil.getTime());
                LocalDate dataPrevDev = dataPrevDevSql.toLocalDate();
                dataPrevDevField.setValue(dataPrevDev);

                java.util.Date dataDevUtil = locacaoToEdit.getDataSaida();
                java.sql.Date dataDevSql = new java.sql.Date(dataDevUtil.getTime());
                LocalDate dataDev = dataDevSql.toLocalDate();
                dataDevField.setValue(dataDev);

                valorField.setValue(locacaoToEdit.getValor());
                clienteAlocadoField.setValue(locacaoToEdit.getCliente());

                veiculoAlocadoField.setValue(locacaoToEdit.getVeiculoAlocado());

            }
        }
    }

    public CreateLocacaoView(LocacaoController locacaoController, VeiculoController veiculoController,
            ClienteController clienteController,
            Optional<Locacao> locacaoOptional) {
        this.locacaoController = locacaoController;
        locacaoToEdit = locacaoOptional.orElse(null);

        veiculoAlocadoField.setItemLabelGenerator(veiculo -> veiculo.getId() + " - " + veiculo.getModelo());
        List<Veiculo> veiculosDisponiveis = veiculoController.listar();
        veiculoAlocadoField.setItems(veiculosDisponiveis);
        veiculoAlocadoField.setValue(veiculosDisponiveis.get(0));

        clienteAlocadoField.setItemLabelGenerator(cliente -> cliente.getId() + " - " + cliente.getNome());
        List<Cliente> clienteDisponiveis = clienteController.listar();
        clienteAlocadoField.setItems(clienteDisponiveis);
        clienteAlocadoField.setValue(clienteDisponiveis.get(0));

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

        if (locacaoToEdit == null) {
            locacaoController.save(locacao);
        } else {
            locacao.setId(locacaoToEdit.getId());
            locacaoController.update(locacao);
        }
    }

}
