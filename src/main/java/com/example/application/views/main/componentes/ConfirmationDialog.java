package com.example.application.views.main.componentes;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ConfirmationDialog extends Dialog {

    public ConfirmationDialog(String title, String message) {
        setCloseOnOutsideClick(false);
        setCloseOnEsc(false);
        setWidth("300px");

        VerticalLayout layout = new VerticalLayout();
        layout.setPadding(true);
        layout.setSpacing(true);

        layout.add(new Text(message));

        Button confirmButton = new Button("Confirmar");
        confirmButton.addClickListener(event -> {
            close();
            fireEvent(new ConfirmationEvent(this, true));
        });

        Button cancelButton = new Button("Cancelar");
        cancelButton.addClickListener(event -> {
            close();
            fireEvent(new ConfirmationEvent(this, false));
        });

        layout.add(confirmButton, cancelButton);
        add(layout);
    }

    public void addConfirmationListener(ComponentEventListener<ConfirmationEvent> listener) {
        addListener(ConfirmationEvent.class, listener);
    }

    public static class ConfirmationEvent extends ComponentEvent<ConfirmationDialog> {
        private final boolean confirmed;

        public ConfirmationEvent(ConfirmationDialog source, boolean confirmed) {
            super(source, false);
            this.confirmed = confirmed;
        }

        public boolean isConfirmed() {
            return confirmed;
        }
    }
}
