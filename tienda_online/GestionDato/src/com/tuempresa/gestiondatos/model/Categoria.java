package com.tuempresa.gestiondatos.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Categoria {
    private final SimpleIntegerProperty idCategoria;
    private final SimpleStringProperty nombreCategoria;

    public Categoria(int idCategoria, String nombreCategoria) {
        this.idCategoria = new SimpleIntegerProperty(idCategoria);
        this.nombreCategoria = new SimpleStringProperty(nombreCategoria);
    }

    public int getIdCategoria() {
        return idCategoria.get();
    }

    public String getNombreCategoria() {
        return nombreCategoria.get();
    }
}
