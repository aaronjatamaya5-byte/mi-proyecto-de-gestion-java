/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectogestion.modelo;

/**
 *
 * @author aaron
 */
public class Estudiante {
    private String nombre;
    private int edad;
    private String rut;
    private String sexo;
    private String direccionHogar;
    private String direccionEstadia;
    private double porcentajeRSH;
    private int nivelDeportividad;
    private int ingresosFamiliares;
    private double promedioNotas;
    
    public Estudiante(String nombre, int edad, String rut,String sexo ,String direccionHogar, String direccionEstadia, double porcentajeRSH, int nivelDeportividad, int ingresosFamiliares, double promedioNotas)
    {
        this.nombre=nombre;
        this.edad=edad;
        this.rut=rut;
        this.sexo=sexo;
        this.direccionHogar=direccionHogar;
        this.direccionEstadia=direccionEstadia;
        this.porcentajeRSH=porcentajeRSH;
        this.nivelDeportividad=nivelDeportividad;
        this.ingresosFamiliares=ingresosFamiliares;
        this.promedioNotas=promedioNotas;
    }
    
    public void setNombre(String nombre)
    {
        this.nombre=nombre;
    }
    
    public String getNombre()
    {
        return nombre;
    }
    
    public void setEdad(int edad)
    {
        this.edad=edad;
    }
    
    public int getEdad()
    {
        return edad;
    }
    
    public void setRut(String rut)
    {
        this.rut=rut;
    }
    
    public String getRut()
    {
        return rut;
    }
    
        public void setSexo(String sexo)
    {
        this.sexo=sexo;
    }
    
    public String getSexo()
    {
        return sexo;
    }
    
        public void setDireccionHogar(String direccionHogar)
    {
        this.direccionHogar=direccionHogar;
    }
    
    public String getDireccionHogar()
    {
        return direccionHogar;
    }
    
        public void setDireccionEstadia(String direccionEstadia)
    {
        this.direccionEstadia=direccionEstadia;
    }
    
    public String getDireccionEstadia()
    {
        return direccionEstadia;
    }
    
    public void setPorcentajeRSH(double porcentajeRSH)
    {
        this.porcentajeRSH=porcentajeRSH;
    }
    
    public double getPorcentajeRSH()
    {
        return porcentajeRSH;
    }
    
    public void setNivelDeportividad(int nivelDeportividad)
    {
        this.nivelDeportividad=nivelDeportividad;
    }
    
    public int getNivelDeportividad()
    {
        return nivelDeportividad;
    }
    
    public void setIngresosFamiliares(int ingresosFamiliares)
    {
        this.ingresosFamiliares=ingresosFamiliares;
    }
    
    public int getIngresosFamiliares()
    {
        return ingresosFamiliares;
    }
    
    public void setPromedioNotas(double promedioNotas)
    {
        this.promedioNotas=promedioNotas;
    }
    
    public double getPromedioNotas()
    {
        return promedioNotas;
    }
}
