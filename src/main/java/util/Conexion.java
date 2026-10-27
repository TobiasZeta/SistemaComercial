/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author tobia
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL =
            "jdbc:sqlserver://TZETAPC:1433;"
            + "databaseName=sistema_comercial;"
            + "encrypt=true;"
            + "trustServerCertificate=true;";

    public static Connection obtenerConexion() throws SQLException {
        String usuario = System.getenv("SISTEMA_COMERCIAL_DB_USER");
        String clave = System.getenv("SISTEMA_COMERCIAL_DB_PASSWORD");

        if (usuario == null || usuario.isBlank()
                || clave == null || clave.isBlank()) {
            throw new SQLException(
                    "Faltan las variables de entorno de la base de datos."
            );
        }

        return DriverManager.getConnection(URL, usuario, clave);
    }
}
