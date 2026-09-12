/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectogestion.main;
import proyectogestion.logica.ProgramaGestion;
import proyectogestion.vista.Interfaz;
import proyectogestion.modelo.Estudiante;
import proyectogestion.modelo.BecaAcademica;
import proyectogestion.modelo.BecaDeportiva;
import proyectogestion.modelo.BecaSocioeconomica;



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
        
        cargarDatosPrueba(programa);

        Interfaz ventana = new Interfaz(programa);

        ventana.setVisible(true);
    }
    
    private static void cargarDatosPrueba(ProgramaGestion programa)
    {
        System.out.println("-Datos de prueba iniciales-");
        
        Estudiante est1 = new Estudiante("Juan Soto", 21, "20111222-3", "Masculino", "Avenida Central 123", "Avenida Central 123", 40.0, 8, 350000, 6.5);
        Estudiante est2 = new Estudiante("María Gómez", 23, "19222333-4", "Femenino", "Calle Sur 456", "Calle Sur 456", 60.0, 3, 550000, 5.8);
        Estudiante est3 = new Estudiante("Carlos Díaz", 20, "21333444-5", "Masculino", "Pasaje Norte 789", "Pasaje Norte 789", 30.0, 10, 250000, 6.1);
                
        programa.registrarEstudiante(est1);
        programa.registrarEstudiante(est2);
        programa.registrarEstudiante(est3);
        
        BecaAcademica becaAcade = new BecaAcademica(1,"Beca Excelencia Academica", 85,2);
        BecaDeportiva becaDepor = new BecaDeportiva(2,"Beca Deportista Destacado",400,1);
        BecaSocioeconomica becaSocio = new BecaSocioeconomica(3,"Beca Apoyo Estudiantil",70000,5);
        
        programa.registrarBeca(becaAcade);
        programa.registrarBeca(becaDepor);
        programa.registrarBeca(becaSocio);
        
        System.out.println("Se cargaron 3 becas y estudiantes");
    }
    
}
