package com.tuempresa.gestiondatos.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tuempresa.gestiondatos.model.Categoria;
import com.tuempresa.gestiondatos.model.Cliente;
import com.tuempresa.gestiondatos.model.DetallePedido;
import com.tuempresa.gestiondatos.model.Pedido;
import com.tuempresa.gestiondatos.model.Producto;

public class DataLoader {

    public static List<Cliente> loadClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM clientes");
        
        while (rs.next()) {
            Cliente cliente = new Cliente(rs.getInt("idCliente"), rs.getString("nombre"), rs.getString("email"));
            clientes.add(cliente);
        }
        return clientes;
    }

    public static List<Categoria> loadCategorias() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM categorias");
        
        while (rs.next()) {
            Categoria categoria = new Categoria(rs.getInt("idCategoria"), rs.getString("nombre"));
            categorias.add(categoria);
        }
        return categorias;
    }

    public static List<Producto> loadProductos() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM productos");
        
        while (rs.next()) {
            Producto producto = new Producto(rs.getInt("idProducto"), rs.getString("nombre"), rs.getDouble("precio"), rs.getInt("idCategoria"));
            productos.add(producto);
        }
        return productos;
    }

    public static List<Pedido> loadPedidos() throws SQLException {
        List<Pedido> pedidos = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM pedidos");
        
        while (rs.next()) {
            Pedido pedido = new Pedido(rs.getInt("idPedido"), rs.getInt("idCliente"), rs.getString("fecha"));
            pedidos.add(pedido);
        }
        return pedidos;
    }

    public static List<DetallePedido> loadDetalles() throws SQLException {
        List<DetallePedido> detalles = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM detalles_pedido");
        
        while (rs.next()) {
            DetallePedido detalle = new DetallePedido(rs.getInt("idPedido"), rs.getInt("idProducto"), rs.getInt("cantidad"), rs.getDouble("precioUnitario"));
            detalles.add(detalle);
        }
        return detalles;
    }
}
