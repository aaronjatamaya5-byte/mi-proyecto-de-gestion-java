/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectogestion.modelo;

/**
 *
 * @author aaron
 */
public class BecaAcademica extends Beca{
    
    public BecaAcademica(int codigo,String nombreBeca,int puntajeMinimo,int cupos)
    {
        super(codigo,nombreBeca,puntajeMinimo,cupos);
    }
    
    public double calcularPuntaje()
    {
        return 0.0;
    }
}
