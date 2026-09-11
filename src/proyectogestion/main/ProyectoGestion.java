/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectogestion.main;
import proyectogestion.logica.ProgramaGestion;
import proyectogestion.vista.Interfaz;

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
    
    }
    
}
