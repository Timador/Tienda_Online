package com.tuempresa.gestiondatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.tuempresa.gestiondatos.model.Categoria;
import com.tuempresa.gestiondatos.model.Cliente;
import com.tuempresa.gestiondatos.model.DetallePedido;
import com.tuempresa.gestiondatos.model.Pedido;
import com.tuempresa.gestiondatos.model.Producto;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {

    private static final String URL = "jdbc:postgresql://localhost:2003/tienda_online";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgresql.2003";

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Gestión de Datos");

        BorderPane root = new BorderPane();

        TabPane tabPane = new TabPane();

        Tab tabClientes = new Tab("Clientes");
        Tab tabCategorias = new Tab("Categorías");
        Tab tabProductos = new Tab("Productos");
        Tab tabPedidos = new Tab("Pedidos");
        Tab tabDetallesPedidos = new Tab("Detalles de Pedidos");

        tabClientes.setContent(createClientesTab());
        tabCategorias.setContent(createCategoriasTab());
        tabProductos.setContent(createProductosTab());
        tabPedidos.setContent(createPedidosTab());
        tabDetallesPedidos.setContent(createDetallesPedidosTab());

        tabPane.getTabs().addAll(tabClientes, tabCategorias, tabProductos, tabPedidos, tabDetallesPedidos);

        root.setCenter(tabPane);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private BorderPane createClientesTab() {
        TableView<Cliente> tableView = new TableView<>();

        TableColumn<Cliente, Integer> colID = new TableColumn<>("ID Cliente");
        colID.setCellValueFactory(new PropertyValueFactory<>("idCliente"));

        TableColumn<Cliente, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreCliente"));

        TableColumn<Cliente, String> colEmail = new TableColumn<>("Email");
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

        tableView.getColumns().addAll(colID, colNombre, colEmail);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        ObservableList<Cliente> clientes = loadClientesData();
        tableView.setItems(clientes);

        TextField searchField = new TextField();
        searchField.setPromptText("Buscar por nombre...");

        searchField.textProperty().addListener((obs, oldText, newText) -> {
            tableView.setItems(clientes.filtered(cliente -> 
                cliente.getNombreCliente().toLowerCase().contains(newText.toLowerCase())
            ));
        });

        Button refreshButton = new Button("Actualizar");
        refreshButton.setOnAction(e -> tableView.setItems(loadClientesData()));

        HBox searchBox = new HBox(10, searchField, refreshButton);
        searchBox.setPadding(new Insets(10));

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(searchBox);
        borderPane.setCenter(tableView);
        borderPane.setPadding(new Insets(10));

        return borderPane;
    }

    private BorderPane createCategoriasTab() {
        TableView<Categoria> tableView = new TableView<>();

        TableColumn<Categoria, Integer> colID = new TableColumn<>("ID Categoría");
        colID.setCellValueFactory(new PropertyValueFactory<>("idCategoria"));

        TableColumn<Categoria, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreCategoria"));

        tableView.getColumns().addAll(colID, colNombre);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        ObservableList<Categoria> categorias = loadCategoriasData();
        tableView.setItems(categorias);

        TextField searchField = new TextField();
        searchField.setPromptText("Buscar por nombre...");

        searchField.textProperty().addListener((obs, oldText, newText) -> {
            tableView.setItems(categorias.filtered(categoria -> 
                categoria.getNombreCategoria().toLowerCase().contains(newText.toLowerCase())
            ));
        });

        Button refreshButton = new Button("Actualizar");
        refreshButton.setOnAction(e -> tableView.setItems(loadCategoriasData()));

        HBox searchBox = new HBox(10, searchField, refreshButton);
        searchBox.setPadding(new Insets(10));

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(searchBox);
        borderPane.setCenter(tableView);
        borderPane.setPadding(new Insets(10));

        return borderPane;
    }


    private BorderPane createProductosTab() {
        TableView<Producto> tableView = new TableView<>();

        TableColumn<Producto, Integer> colID = new TableColumn<>("ID Producto");
        colID.setCellValueFactory(new PropertyValueFactory<>("idProducto"));

        TableColumn<Producto, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));

        TableColumn<Producto, Double> colPrecio = new TableColumn<>("Precio");
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        TableColumn<Producto, Integer> colIDCategoria = new TableColumn<>("ID Categoría");
        colIDCategoria.setCellValueFactory(new PropertyValueFactory<>("idCategoria"));

        tableView.getColumns().addAll(colID, colNombre, colPrecio, colIDCategoria);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        ObservableList<Producto> productos = loadProductosData();
        tableView.setItems(productos);

        TextField searchField = new TextField();
        searchField.setPromptText("Buscar por nombre...");

        searchField.textProperty().addListener((obs, oldText, newText) -> {
            tableView.setItems(productos.filtered(producto -> 
                producto.getNombreProducto().toLowerCase().contains(newText.toLowerCase())
            ));
        });

        Button refreshButton = new Button("Actualizar");
        refreshButton.setOnAction(e -> tableView.setItems(loadProductosData()));

        HBox searchBox = new HBox(10, searchField, refreshButton);
        searchBox.setPadding(new Insets(10));

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(searchBox);
        borderPane.setCenter(tableView);
        borderPane.setPadding(new Insets(10));

        return borderPane;
    }


    private BorderPane createPedidosTab() {
        TableView<Pedido> tableView = new TableView<>();

        TableColumn<Pedido, Integer> colID = new TableColumn<>("ID Pedido");
        colID.setCellValueFactory(new PropertyValueFactory<>("idPedido"));

        TableColumn<Pedido, Integer> colCliente = new TableColumn<>("ID Cliente");
        colCliente.setCellValueFactory(new PropertyValueFactory<>("idCliente"));

        TableColumn<Pedido, String> colFecha = new TableColumn<>("Fecha");
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaPedido"));

        tableView.getColumns().addAll(colID, colCliente, colFecha);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        ObservableList<Pedido> pedidos = loadPedidosData();
        tableView.setItems(pedidos);

        TextField searchField = new TextField();
        searchField.setPromptText("Buscar por fecha...");

        searchField.textProperty().addListener((obs, oldText, newText) -> {
            tableView.setItems(pedidos.filtered(pedido -> 
                pedido.getFechaPedido().toLowerCase().contains(newText.toLowerCase())
            ));
        });

        Button refreshButton = new Button("Actualizar");
        refreshButton.setOnAction(e -> tableView.setItems(loadPedidosData()));

        HBox searchBox = new HBox(10, searchField, refreshButton);
        searchBox.setPadding(new Insets(10));

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(searchBox);
        borderPane.setCenter(tableView);
        borderPane.setPadding(new Insets(10));

        return borderPane;
    }


    private BorderPane createDetallesPedidosTab() {
        TableView<DetallePedido> tableView = new TableView<>();

        TableColumn<DetallePedido, Integer> colIDPedido = new TableColumn<>("ID Pedido");
        colIDPedido.setCellValueFactory(new PropertyValueFactory<>("idPedido"));

        TableColumn<DetallePedido, Integer> colIDProducto = new TableColumn<>("ID Producto");
        colIDProducto.setCellValueFactory(new PropertyValueFactory<>("idProducto"));

        TableColumn<DetallePedido, Integer> colCantidad = new TableColumn<>("Cantidad");
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

        TableColumn<DetallePedido, Double> colPrecioUnitario = new TableColumn<>("Precio Unitario");
        colPrecioUnitario.setCellValueFactory(new PropertyValueFactory<>("precioUnitario"));

        tableView.getColumns().addAll(colIDPedido, colIDProducto, colCantidad, colPrecioUnitario);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        ObservableList<DetallePedido> detallesPedidos = loadDetallesPedidosData();
        tableView.setItems(detallesPedidos);

        TextField searchField = new TextField();
        searchField.setPromptText("Buscar por ID...");

        searchField.textProperty().addListener((obs, oldText, newText) -> {
            try {
                int searchID = Integer.parseInt(newText);
                tableView.setItems(detallesPedidos.filtered(detalle -> 
                    detalle.getIdPedido() == searchID
                ));
            } catch (NumberFormatException e) {
                // If the input is not a valid integer, show all items
                tableView.setItems(detallesPedidos);
            }
        });

        Button refreshButton = new Button("Actualizar");
        refreshButton.setOnAction(e -> tableView.setItems(loadDetallesPedidosData()));

        HBox searchBox = new HBox(10, searchField, refreshButton);
        searchBox.setPadding(new Insets(10));

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(searchBox);
        borderPane.setCenter(tableView);
        borderPane.setPadding(new Insets(10));

        return borderPane;
    }



    private ObservableList<Cliente> loadClientesData() {
        ObservableList<Cliente> clientes = FXCollections.observableArrayList();
        String query = "SELECT id_cliente, nombre_cliente, email FROM clientes ORDER BY id_cliente ASC";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_cliente");
                String nombre = rs.getString("nombre_cliente");
                String email = rs.getString("email");
                clientes.add(new Cliente(id, nombre, email));
            }
        } catch (SQLException e) {
            showErrorAlert("Error al cargar los datos de los clientes: " + e.getMessage());
        }

        return clientes;
    }

    private ObservableList<Categoria> loadCategoriasData() {
        ObservableList<Categoria> categorias = FXCollections.observableArrayList();
        String query = "SELECT id_categoria, nombre_categoria FROM categorias ORDER BY id_categoria ASC";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_categoria");
                String nombre = rs.getString("nombre_categoria");
                categorias.add(new Categoria(id, nombre));
            }
        } catch (SQLException e) {
            showErrorAlert("Error al cargar los datos de las categorías: " + e.getMessage());
        }

        return categorias;
    }

    private ObservableList<Producto> loadProductosData() {
        ObservableList<Producto> productos = FXCollections.observableArrayList();
        String query = "SELECT id_producto, nombre_producto, precio, id_categoria FROM productos ORDER BY id_producto ASC";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_producto");
                String nombre = rs.getString("nombre_producto");
                double precio = rs.getDouble("precio");
                int idCategoria = rs.getInt("id_categoria");
                productos.add(new Producto(id, nombre, precio, idCategoria));
            }
        } catch (SQLException e) {
            showErrorAlert("Error al cargar los datos de los productos: " + e.getMessage());
        }

        return productos;
    }

    private ObservableList<Pedido> loadPedidosData() {
        ObservableList<Pedido> pedidos = FXCollections.observableArrayList();
        String query = "SELECT id_pedido, id_cliente, fecha_pedido FROM pedidos ORDER BY id_pedido ASC";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_pedido");
                int cliente = rs.getInt("id_cliente");
                String fecha = rs.getString("fecha_pedido");
                pedidos.add(new Pedido(id, cliente, fecha));
            }
        } catch (SQLException e) {
            showErrorAlert("Error al cargar los datos de los pedidos: " + e.getMessage());
        }

        return pedidos;
    }

	private ObservableList<DetallePedido> loadDetallesPedidosData() {
	  ObservableList<DetallePedido> detallesPedidos = FXCollections.observableArrayList();
	  // Añadir la cláusula ORDER BY para ordenar por id_pedido y id_producto
	  String query = "SELECT id_pedido, id_producto, cantidad, precio_unitario FROM detalles_pedido ORDER BY id_pedido ASC, id_producto ASC";
	
	  try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
	       Statement stmt = conn.createStatement();
	       ResultSet rs = stmt.executeQuery(query)) {
	
	      while (rs.next()) {
	          int idPedido = rs.getInt("id_pedido");
	          int idProducto = rs.getInt("id_producto");
	          int cantidad = rs.getInt("cantidad");
	          double precioUnitario = rs.getDouble("precio_unitario");
	          detallesPedidos.add(new DetallePedido(idPedido, idProducto, cantidad, precioUnitario));
	      }
	  } catch (SQLException e) {
	      e.printStackTrace();
	  }
	
	  return detallesPedidos;
	}

    private void showErrorAlert(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

