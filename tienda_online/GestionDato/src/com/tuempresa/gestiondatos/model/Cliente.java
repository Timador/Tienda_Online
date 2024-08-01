package com.tuempresa.gestiondatos.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Cliente {
    private final SimpleIntegerProperty idCliente;
    private final SimpleStringProperty nombreCliente;
    private final SimpleStringProperty email;

    public Cliente(int idCliente, String nombreCliente, String email) {
        this.idCliente = new SimpleIntegerProperty(idCliente);
        this.nombreCliente = new SimpleStringProperty(nombreCliente);
        this.email = new SimpleStringProperty(email);
    }

    public int getIdCliente() {
        return idCliente.get();
    }

    public String getNombreCliente() {
        return nombreCliente.get();
    }

    public String getEmail() {
        return email.get();
    }
}

