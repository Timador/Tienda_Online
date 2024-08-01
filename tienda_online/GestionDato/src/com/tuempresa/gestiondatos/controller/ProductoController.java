package com.tuempresa.gestiondatos.controller;

import com.tuempresa.gestiondatos.model.Producto;
import com.tuempresa.gestiondatos.util.DataLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class ProductoController {

    @FXML
    private TableView<Producto> productoTable;
    @FXML
    private TableColumn<Producto, Integer> colID;
    @FXML
    private TableColumn<Producto, String> colNombre;
    @FXML
    private TableColumn<Producto, Double> colPrecio;
    @FXML
    private TableColumn<Producto, Integer> colIDCategoria;

    private ObservableList<Producto> productos;

    @FXML
    private void initialize() {
        colID.setCellValueFactory(new PropertyValueFactory<>("idProducto"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colIDCategoria.setCellValueFactory(new PropertyValueFactory<>("idCategoria"));

        try {
            productos = FXCollections.observableArrayList(DataLoader.loadProductos());
            productoTable.setItems(productos);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
