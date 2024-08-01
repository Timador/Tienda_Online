package com.tuempresa.gestiondatos.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Pedido {
    private final SimpleIntegerProperty idPedido;
    private final SimpleIntegerProperty idCliente;
    private final SimpleStringProperty fechaPedido;

    public Pedido(int idPedido, int idCliente, String fechaPedido) {
        this.idPedido = new SimpleIntegerProperty(idPedido);
        this.idCliente = new SimpleIntegerProperty(idCliente);
        this.fechaPedido = new SimpleStringProperty(fechaPedido);
    }

    public int getIdPedido() {
        return idPedido.get();
    }

    public int getIdCliente() {
        return idCliente.get();
    }

    public String getFechaPedido() {
        return fechaPedido.get();
    }
}

