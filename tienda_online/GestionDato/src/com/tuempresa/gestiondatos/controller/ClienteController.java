package com.tuempresa.gestiondatos.controller;

import com.tuempresa.gestiondatos.model.Cliente;
import com.tuempresa.gestiondatos.util.DataLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class ClienteController {

    @FXML
    private TableView<Cliente> clienteTable;
    @FXML
    private TableColumn<Cliente, Integer> colID;
    @FXML
    private TableColumn<Cliente, String> colNombre;
    @FXML
    private TableColumn<Cliente, String> colEmail;

    private ObservableList<Cliente> clientes;

    @FXML
    private void initialize() {
        colID.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreCliente"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        try {
            clientes = FXCollections.observableArrayList(DataLoader.loadClientes());
            clienteTable.setItems(clientes);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
