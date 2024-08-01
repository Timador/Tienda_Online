package com.tuempresa.gestiondatos.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class DetallePedido {
    private final SimpleIntegerProperty idPedido;
    private final SimpleIntegerProperty idProducto;
    private final SimpleIntegerProperty cantidad;
    private final SimpleDoubleProperty precioUnitario;

    public DetallePedido(int idPedido, int idProducto, int cantidad, double precioUnitario) {
        this.idPedido = new SimpleIntegerProperty(idPedido);
        this.idProducto = new SimpleIntegerProperty(idProducto);
        this.cantidad = new SimpleIntegerProperty(cantidad);
        this.precioUnitario = new SimpleDoubleProperty(precioUnitario);
    }

    public int getIdPedido() {
        return idPedido.get();
    }

    public int getIdProducto() {
        return idProducto.get();
    }

    public int getCantidad() {
        return cantidad.get();
    }

    public double getPrecioUnitario() {
        return precioUnitario.get();
    }
}

