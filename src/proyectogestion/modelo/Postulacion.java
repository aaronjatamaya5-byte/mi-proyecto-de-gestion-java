/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectogestion.modelo;

/**
 *
 * @author curruk
 */
public class Postulacion {
    private int idPostulacion;
    private String estadoPostulacion;
    private Estudiante postulante;
    
    public Postulacion(int idPostulacion,String estadoPostulacion,Estudiante postulante)
    {
        this.idPostulacion=idPostulacion;
        this.estadoPostulacion=estadoPostulacion;
        this.postulante=postulante;
    }
    
    public void setIdPostulacion(int idPostulacion)
    {
        this.idPostulacion=idPostulacion;
    }
    
    public int getIdPostulacion()
    {
        return idPostulacion;
    }
    
    public void setEstadoPostulacion(String estadoPostulacion)
    {
        this.estadoPostulacion=estadoPostulacion;
    }
    
    public String getEstadoPostulacion()
    {
        return estadoPostulacion;
    }
    
    public void setPostulante(Estudiante postulante)
    {
        this.postulante=postulante;
    }
    
    public Estudiante getPostulante()
    {
        return postulante;
    }
}
