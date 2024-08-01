package com.tuempresa.gestiondatos.controller;

import com.tuempresa.gestiondatos.model.Pedido;
import com.tuempresa.gestiondatos.util.DataLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class PedidoController {

    @FXML
    private TableView<Pedido> pedidoTable;
    @FXML
    private TableColumn<Pedido, Integer> colID;
    @FXML
    private TableColumn<Pedido, Integer> colClienteID;
    @FXML
    private TableColumn<Pedido, String> colFecha;

    private ObservableList<Pedido> pedidos;

    @FXML
    private void initialize() {
        colID.setCellValueFactory(new PropertyValueFactory<>("idPedido"));
        colClienteID.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaPedido"));

        try {
            pedidos = FXCollections.observableArrayList(DataLoader.loadPedidos());
            pedidoTable.setItems(pedidos);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
