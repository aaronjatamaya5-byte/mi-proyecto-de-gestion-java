/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectogestion.modelo;

/**
 *
 * @author aaron
 */
public class BecaDeportiva extends Beca{
    
    public BecaDeportiva(int codigo,String nombreBeca,int puntajeMinimo,int cupos)
    {
        super(codigo,nombreBeca,puntajeMinimo,cupos);
    }
    
    @Override //Sobreescritura de metodo
    public double calcularPuntaje(Estudiante postulante)
    {
        //Conseguimos el nivel deportividad y para el calculo del puntaje sumamos un poco del promedio de notas
        int nivelDeportividad=postulante.getNivelDeportividad();
        
        double promedio = postulante.getPromedioNotas();
        
        double puntaje = (nivelDeportividad*50)+(promedio * 10);
        return puntaje;
    }
}
