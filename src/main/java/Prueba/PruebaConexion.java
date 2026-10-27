/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Prueba;

/**
 *
 * @author tobia
 */

import java.sql.Connection;
import util.Conexion;

public class PruebaConexion {

    public static void main(String[] args) {
        try (Connection con = Conexion.obtenerConexion()) {
            System.out.println("Conexion exitosa con SQL Server.");
            System.out.println("Base de datos: " + con.getCatalog());
        } catch (Exception e) {
            System.out.println("Error al conectar con SQL Server:");
            e.printStackTrace();
        }
    }
}

