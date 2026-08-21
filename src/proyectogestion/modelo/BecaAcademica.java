/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectogestion.modelo;

/**
 *
 * @author aaron
 */
public class BecaAcademica extends Beca {

    public BecaAcademica(int codigo, String nombreBeca, int puntajeMinimo, int cupos)
    {
        super(codigo, nombreBeca, puntajeMinimo, cupos);
    }

    @Override //siempre agrégala cuando sobreescribas un método. Si la firma no coincide con la del padre, el compilador te avisará de inmediato
    
    public double calcularPuntaje(Estudiante postulante) //calcula cuanto saco un estudiante en particular
    {
        
        double promedio = postulante.getPromedioNotas();
        double puntaje = (promedio / 7.0) * 100;
        return puntaje;
    }
}
