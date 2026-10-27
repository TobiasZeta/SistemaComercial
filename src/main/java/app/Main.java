/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

/**
 *
 * @author tobia
 */

import javax.swing.SwingUtilities;
import vista.VentanaProducto;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaProducto ventana = new VentanaProducto();
            ventana.setVisible(true);
        });
    }
}

