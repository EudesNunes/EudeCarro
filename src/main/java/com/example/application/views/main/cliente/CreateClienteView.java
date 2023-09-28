package com.example.application.views.main.cliente;

import java.util.Optional;

import com.example.application.controller.ClienteController;
import com.example.application.entity.Cliente;
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
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.vaadin.flow.theme.lumo.LumoUtility.Padding;

@PageTitle("CreateCliente")
@Route(value = "newCliente/:id")
@Uses(Icon.class)
public class CreateClienteView extends Composite<VerticalLayout> implements BeforeEnterObserver {

    private final ClienteController clienteController;
    private Cliente clienteToEdit;
    private String clienteId;

    private TextField nomeField = new TextField();
    private TextField cpfField = new TextField();
    private EmailField emailField = new EmailField();
    private TextField cnhField = new TextField();
    private TextField telefoneField = new TextField();

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        clienteId = event.getRouteParameters().get("id").orElse(null);

        if (clienteId != null) {
            Long id = Long.parseLong(clienteId);
            clienteToEdit = clienteController.obter(id).orElse(null);

            if (clienteToEdit != null) {

                nomeField.setValue(clienteToEdit.getNome());
                cpfField.setValue(Long.toString(clienteToEdit.getCpf()));
                emailField.setValue(clienteToEdit.getEmail());
                telefoneField.setValue(clienteToEdit.getTelefone());
                cnhField.setValue(Long.toString(clienteToEdit.getNumCnh()));
            }
        }
    }

    public CreateClienteView(ClienteController clienteController, Optional<Cliente> clienteOptional) {
        this.clienteController = clienteController;
        clienteToEdit = clienteOptional.orElse(null);

        HorizontalLayout layoutRow = new HorizontalLayout();
        VerticalLayout layoutColumn5 = new VerticalLayout();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        H3 h3 = new H3();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        VerticalLayout layoutColumn3 = new VerticalLayout();
        VerticalLayout layoutColumn4 = new VerticalLayout();
        HorizontalLayout layoutRow3 = new HorizontalLayout();
        HorizontalLayout layoutRow4 = new HorizontalLayout();
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
        h3.setText("Cadastro de Clientes");
        layoutRow2.setWidthFull();
        layoutRow2.addClassName(Gap.LARGE);
        layoutRow2.setFlexGrow(1.0, layoutColumn3);
        layoutColumn3.setWidth(null);
        nomeField.setLabel("Nome");
        nomeField.setWidthFull();
        cpfField.setLabel("CPF");
        cpfField.setWidthFull();
        emailField.setLabel("Email");
        emailField.setWidthFull();
        layoutRow2.setFlexGrow(1.0, layoutColumn4);
        layoutColumn4.setWidth(null);
        cnhField.setLabel("CNH");
        cnhField.setWidthFull();
        layoutRow3.addClassName(Gap.MEDIUM);
        layoutRow3.setWidthFull();
        telefoneField.setLabel("Telefone");
        layoutRow3.setFlexGrow(1.0, telefoneField);
        layoutRow4.addClassName(Gap.MEDIUM);

        buttonPrimary.addClickListener(event -> saveCliente());
        buttonSecondary.addClickListener(event -> {
            UI.getCurrent().navigate("clientes/listar");
        });

        getContent().add(layoutRow);
        layoutRow.add(layoutColumn5);
        layoutRow.add(layoutColumn2);
        layoutColumn2.add(h3);
        layoutColumn2.add(layoutRow2);
        layoutRow2.add(layoutColumn3);
        layoutColumn3.add(nomeField);
        layoutColumn3.add(cpfField);
        layoutColumn3.add(emailField);
        layoutRow2.add(layoutColumn4);
        layoutColumn4.add(cnhField);
        layoutColumn4.add(layoutRow3);
        layoutRow3.add(telefoneField);
        layoutRow2.add(layoutRow4);
        layoutRow4.add(buttonPrimary);
        layoutRow4.add(buttonSecondary);

    }

    private void saveCliente() {
        String nome = nomeField.getValue();
        String cpf = cpfField.getValue().toString();
        String email = emailField.getValue();
        String cnh = cnhField.getValue().toString();
        String telefone = telefoneField.getValue();

        Cliente cliente = new Cliente(nome, telefone, Long.parseLong(cpf), email, Long.parseLong(cnh));

        if (clienteToEdit == null) {
            clienteController.save(cliente);
        } else {
            cliente.setId(clienteToEdit.getId());
            clienteController.update(cliente);
        }
    }
}
