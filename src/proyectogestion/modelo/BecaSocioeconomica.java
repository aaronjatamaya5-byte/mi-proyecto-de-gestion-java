/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectogestion.modelo;

/**
 *
 * @author aaron
 */
public class BecaSocioeconomica extends Beca{
    
    public BecaSocioeconomica(int codigo,String nombreBeca,int puntajeMinimo,int cupos)
    {
        super(codigo,nombreBeca,puntajeMinimo,cupos);
    }
    
    @Override //Sobreescitura de metodo
    public double calcularPuntaje(Estudiante postulante)
    {
        //Si el estudiante tiene menor porcentaje y menores ingresos familiares, su puntaje sera mayor
        double rsh=postulante.getPorcentajeRSH();
        int ingresos=postulante.getIngresosFamiliares();
        double puntaje = ((100-rsh)*10)+1000000.0/(ingresos+1);
        return puntaje;
    }
}
