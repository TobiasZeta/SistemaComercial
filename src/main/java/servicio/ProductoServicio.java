/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

/**
 *
 * @author tobia
 */

import dao.ProductoDAO;
import modelo.Producto;
import java.sql.SQLException;
import java.util.List;

public class ProductoServicio {

    private final ProductoDAO productoDAO;

    public ProductoServicio() {
        productoDAO = new ProductoDAO();
    }

    public void guardarProducto(Producto producto)
            throws SQLException {

        if (producto == null) {
            throw new IllegalArgumentException(
                    "El producto no puede ser nulo.");
        }

        if (producto.getNombre() == null
                || producto.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El nombre es obligatorio.");
        }

        if (producto.getPrecio() <= 0) {
            throw new IllegalArgumentException(
                    "El precio debe ser mayor que cero.");
        }

        if (producto.getStock() < 0) {
            throw new IllegalArgumentException(
                    "El stock no puede ser negativo.");
        }

        producto.setNombre(producto.getNombre().trim());
        productoDAO.insertar(producto);
    }

    public List<Producto> listarProductos() throws SQLException {
        return productoDAO.listar();
    }
}
