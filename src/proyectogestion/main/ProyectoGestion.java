/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectogestion.main;
import proyectogestion.logica.ProgramaGestion;
import proyectogestion.vista.Interfaz;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author aaron
 */
public class ProyectoGestion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ProgramaGestion programa = new ProgramaGestion();

        Interfaz ventana = new Interfaz(programa);

        ventana.setVisible(true);
        
        //TEST DE BASE DE DATOS
        /*String url = "jdbc:sqlite:becas_test.db";
        
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("¡Conexión exitosa a SQLite!");
                System.out.println("Base de datos creada/conectada correctamente.");
            }
        } catch (SQLException e) {
        System.err.println("Error al conectar con SQLite: " + e.getMessage());
        }*/
        
    }
}
