package com.tuempresa.gestiondatos.controller;

import com.tuempresa.gestiondatos.model.Categoria;
import com.tuempresa.gestiondatos.util.DataLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class CategoriaController {

    @FXML
    private TableView<Categoria> categoriaTable;
    @FXML
    private TableColumn<Categoria, Integer> colID;
    @FXML
    private TableColumn<Categoria, String> colNombre;

    private ObservableList<Categoria> categorias;

    @FXML
    private void initialize() {
        colID.setCellValueFactory(new PropertyValueFactory<>("idCategoria"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreCategoria"));

        try {
            categorias = FXCollections.observableArrayList(DataLoader.loadCategorias());
            categoriaTable.setItems(categorias);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
