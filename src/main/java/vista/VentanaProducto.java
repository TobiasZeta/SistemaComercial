/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

/**
 *
 * @author tobia
 */

import modelo.Producto;
import servicio.ProductoServicio;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;

public class VentanaProducto extends JFrame {

    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JTextField txtStock;
    private DefaultTableModel modeloTabla;
    private ProductoServicio productoServicio;

    public VentanaProducto() {
        productoServicio = new ProductoServicio();

        setTitle("Sistema Comercial - Productos");
        setSize(750, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel titulo = new JLabel(
                "Sistema Comercial - Productos",
                SwingConstants.CENTER
        );
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        add(titulo, BorderLayout.NORTH);

        JPanel formulario = new JPanel(new GridLayout(4, 2, 8, 8));
        formulario.setBorder(
                BorderFactory.createTitledBorder("Datos del producto")
        );

        txtNombre = new JTextField();
        txtPrecio = new JTextField();
        txtStock = new JTextField();

        formulario.add(new JLabel("Nombre:"));
        formulario.add(txtNombre);

        formulario.add(new JLabel("Precio:"));
        formulario.add(txtPrecio);

        formulario.add(new JLabel("Stock:"));
        formulario.add(txtStock);

        JButton btnGuardar = new JButton("Guardar");
        JButton btnLimpiar = new JButton("Limpiar");

        formulario.add(btnGuardar);
        formulario.add(btnLimpiar);

        modeloTabla = new DefaultTableModel(
                new Object[]{"ID", "Nombre", "Precio", "Stock"}, 0
        ) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        JTable tabla = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(
                BorderFactory.createTitledBorder("Lista de productos")
        );

        JPanel panelCentral = new JPanel(new BorderLayout(10, 10));
        panelCentral.setBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        );

        panelCentral.add(formulario, BorderLayout.NORTH);
        panelCentral.add(scroll, BorderLayout.CENTER);

        add(panelCentral, BorderLayout.CENTER);

        btnGuardar.addActionListener(e -> guardarProducto());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        cargarProductos();
    }

    private void guardarProducto() {
        try {
            String nombre = txtNombre.getText().trim();
            String precioTexto = txtPrecio.getText()
                    .trim().replace(',', '.');
            String stockTexto = txtStock.getText().trim();

            if (nombre.isEmpty()
                    || precioTexto.isEmpty()
                    || stockTexto.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Complete todos los campos."
                );
                return;
            }

            double precio = Double.parseDouble(precioTexto);
            int stock = Integer.parseInt(stockTexto);

            Producto producto = new Producto(
                    nombre, precio, stock
            );

            productoServicio.guardarProducto(producto);

            JOptionPane.showMessageDialog(
                    this,
                    "Producto guardado correctamente."
            );

            limpiarCampos();
            cargarProductos();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "El precio o el stock tienen un formato inválido."
            );

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error al guardar el producto: " + e.getMessage()
            );
        }
    }

    private void cargarProductos() {
        try {
            modeloTabla.setRowCount(0);

            for (Producto producto
                    : productoServicio.listarProductos()) {

                modeloTabla.addRow(new Object[]{
                    producto.getId(),
                    producto.getNombre(),
                    producto.getPrecio(),
                    producto.getStock()
                });
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error al listar productos: " + e.getMessage()
            );
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtPrecio.setText("");
        txtStock.setText("");
        txtNombre.requestFocus();
    }
}
