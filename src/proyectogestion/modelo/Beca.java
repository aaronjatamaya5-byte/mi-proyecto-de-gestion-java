/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectogestion.modelo;

import java.util.ArrayList;

/**
 *
 * @author aaron
*/
public class Beca {
    
    private int codigo;
    private String nombreBeca;
    private int puntajeMinimo;
    private int cupos;
    private ArrayList<Postulacion> listaPostulaciones;
    
    public Beca(int codigo,String nombreBeca,int puntajeMinimo,int cupos)
    {
        this.codigo=codigo;
        this.nombreBeca=nombreBeca;
        this.puntajeMinimo=puntajeMinimo;
        this.cupos=cupos;
        
        listaPostulaciones=new ArrayList<>();
    }
    
    public void setCodigo(int codigo)
    {
        this.codigo=codigo;
    }
    
    public int getCodigo()
    {
        return codigo;
    }
    
    public void setNombreBeca(String nombreBeca)
    {
        this.nombreBeca=nombreBeca;
    }
    
    public String getNombreBeca()
    {
        return nombreBeca;
    }
    
    public void setPuntajeMinimo(int puntajeMinimo)
    {
        this.puntajeMinimo=puntajeMinimo;
    }
    
    public int getPuntajeMinimo()
    {
        return puntajeMinimo;
    }
    
    public void setCupos(int cupos)
    {
        this.cupos=cupos;
    }
    
    public int getCupos()
    {
        return cupos;
    }
    
    public void setListaPostulaciones(ArrayList<Postulacion> listaPostulaciones)
    {
        this.listaPostulaciones=listaPostulaciones;
    }
    
    public ArrayList<Postulacion> getListaPostulaciones()
    {
        return listaPostulaciones;
    }
    
    public void agregarPostulacion(Postulacion postulacion)
    {
        this.listaPostulaciones.add(postulacion);
    }
            
    public double calcularPuntaje(Estudiante postulante)
    {
        return 0.0;
    }
}
