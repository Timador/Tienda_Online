package com.tuempresa.gestiondatos.controller;

import com.tuempresa.gestiondatos.model.DetallePedido;
import com.tuempresa.gestiondatos.util.DataLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class DetallePedidoController {

    @FXML
    private TableView<DetallePedido> detallePedidoTable;
    @FXML
    private TableColumn<DetallePedido, Integer> colIDPedido;
    @FXML
    private TableColumn<DetallePedido, Integer> colIDProducto;
    @FXML
    private TableColumn<DetallePedido, Integer> colCantidad;
    @FXML
    private TableColumn<DetallePedido, Double> colPrecioUnitario;

    private ObservableList<DetallePedido> detalles;

    @FXML
    private void initialize() {
        colIDPedido.setCellValueFactory(new PropertyValueFactory<>("idPedido"));
        colIDProducto.setCellValueFactory(new PropertyValueFactory<>("idProducto"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colPrecioUnitario.setCellValueFactory(new PropertyValueFactory<>("precioUnitario"));

        try {
            detalles = FXCollections.observableArrayList(DataLoader.loadDetalles());
            detallePedidoTable.setItems(detalles);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
