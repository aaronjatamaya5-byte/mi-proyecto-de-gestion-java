/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectogestion.main;

import java.util.Scanner;
import proyectogestion.logica.ProgramaGestion;
import proyectogestion.vista.Interfaz;
import proyectogestion.persistencia.ServicioPersistencia;
import proyectogestion.vista.Menu;

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
        
        //carga inicial
        System.out.println("Iniciando sistema de base de datos...");
        ServicioPersistencia.cargarDatos(programa);

        //Guardado automatico
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nCerrando sistema... Sincronizando datos con SQLite.");
            ServicioPersistencia.guardarDatos(programa);
        }));

        Scanner entrada = new Scanner(System.in);
        
        System.out.println("--- BIENVENIDO AL SISTEMA DE GESTION DE BECAS ---");
        System.out.println("Seleccione el modo de ejecución:");
        System.out.println("1. Modo Consola");
        System.out.println("2. Modo Ventana (Interfaz Gráfica)");
        System.out.print("Ingrese su opción (1 o 2): ");
        
        String opcion= entrada.nextLine();
        
        if(opcion.equals("1"))
        {
            System.out.println("Iniciando el modo consola..");
            Menu menu= new Menu();
            menu.iniciar(programa);
        }
        else if(opcion.equals("2"))
        {
            System.out.println("Iniciando el modo ventana..");
            Interfaz ventana = new Interfaz(programa);
            ventana.setVisible(true);
        }
        else
        {
            System.out.println("Opcion no valida, Ejecute nuevamente");
        }

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