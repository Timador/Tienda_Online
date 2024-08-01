package com.tuempresa.gestiondatos.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Producto {
    private final SimpleIntegerProperty idProducto;
    private final SimpleStringProperty nombreProducto;
    private final SimpleDoubleProperty precio;
    private final SimpleIntegerProperty idCategoria;

    public Producto(int idProducto, String nombreProducto, double precio, int idCategoria) {
        this.idProducto = new SimpleIntegerProperty(idProducto);
        this.nombreProducto = new SimpleStringProperty(nombreProducto);
        this.precio = new SimpleDoubleProperty(precio);
        this.idCategoria = new SimpleIntegerProperty(idCategoria);
    }

    public int getIdProducto() {
        return idProducto.get();
    }

    public String getNombreProducto() {
        return nombreProducto.get();
    }

    public double getPrecio() {
        return precio.get();
    }

    public int getIdCategoria() {
        return idCategoria.get();
    }
}
