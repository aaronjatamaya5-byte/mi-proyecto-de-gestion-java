/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectogestion.peristencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author curruk
 */
public class GestorBD {

    private static final String URL = "jdbc:sqlite:sistema_becas.db";

    // Método para obtener una conexión abierta
    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    // Método que crea el schema si es la primera vez que corre el programa
    public static void inicializarSchema() {
        String sqlEstudiante = "CREATE TABLE IF NOT EXISTS estudiante ("
                + "rut TEXT PRIMARY KEY, "
                + "nombre TEXT NOT NULL, "
                + "edad INTEGER, "
                + "sexo TEXT, "
                + "direccionHogar TEXT, "
                + "direccionEstadia TEXT, "
                + "porcentajeRSH REAL, "
                + "nivelDeportividad INTEGER, "
                + "ingresosFamiliares INTEGER, "
                + "promedioNotas REAL);";

        String sqlBeca = "CREATE TABLE IF NOT EXISTS beca ("
                + "codigo INTEGER PRIMARY KEY, "
                + "nombre TEXT NOT NULL, "
                + "puntajeMinimo INTEGER, "  // Cambiado a INTEGER
                + "cuposTotales INTEGER, "
                + "tipo TEXT NOT NULL);";

        String sqlPostulacion = "CREATE TABLE IF NOT EXISTS postulacion ("
                + "idPostulacion INTEGER PRIMARY KEY, "
                + "estado TEXT NOT NULL, "
                + "rutEstudiante TEXT NOT NULL, "
                + "codigoBeca INTEGER NOT NULL, "
                + "FOREIGN KEY (rutEstudiante) REFERENCES estudiante(rut), "
                + "FOREIGN KEY (codigoBeca) REFERENCES beca(codigo));";

        try (Connection con = conectar(); Statement stmt = con.createStatement()) {
            stmt.execute(sqlEstudiante);
            stmt.execute(sqlBeca);
            stmt.execute(sqlPostulacion);
            System.out.println("Schema verificado y listo en SQLite.");
        } catch (SQLException e) {
            System.err.println("Error al inicializar el schema: " + e.getMessage());
        }
    }
}