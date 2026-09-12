/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectogestion.logica;
import proyectogestion.modelo.Estudiante;
import proyectogestion.modelo.Beca;
import proyectogestion.modelo.Postulacion;
import java.util.HashMap;
import java.util.ArrayList;

/**
 *
 * @author curruk
 */
public class ProgramaGestion {
    
    private HashMap<Integer, Beca> mapaBecas;
    private ArrayList<Estudiante> listaEstudiantes;
    private int contadorIdPostulacion = 1; //Coontador que aumentará cada vez que se haga una postulacion satisfactoria a alguna beca
    
    public ProgramaGestion() {
        mapaBecas = new HashMap<>();
        listaEstudiantes = new ArrayList<>();
    }
    
    public ArrayList<Estudiante> getListaEstudiantes() {
        return listaEstudiantes;
    }
    
    public HashMap<Integer, Beca> getMapaBecas() {
        return mapaBecas;
    }
    
    public void setContadorIdPostulacion(int contador) {
        this.contadorIdPostulacion = contador;
    }

    // Para saber en qué número quedó el contador
    public int getContadorIdPostulacion() {
        return contadorIdPostulacion;
    }
    
    public void registrarEstudiante(Estudiante estudiante) {
        listaEstudiantes.add(estudiante);
    }
    
    public void registrarBeca(Beca beca) {
        mapaBecas.put(beca.getCodigo(), beca);
    }
    
    // Buscar beca por código
    public Beca buscarBeca(int codigo) {
        return mapaBecas.get(codigo);
    }
    
    //Buscar estudiante por rut
    public Estudiante buscarEstudiante(String rut) {
        for (Estudiante est :listaEstudiantes) {
            if (est.getRut().equals(rut))
                return est;
        }
        return null;
    }
    
    // Creamos la postulacion
    public boolean postularEstudiante(String rut, int codigoBeca) {
        Estudiante estudiante = buscarEstudiante(rut);
        Beca beca = buscarBeca(codigoBeca);

        if (estudiante == null || beca == null) {
            return false;
        }

        Postulacion nuevaPostulacion = new Postulacion(this.contadorIdPostulacion, "Pendiente", estudiante);
        this.contadorIdPostulacion++;
        beca.agregarPostulacion(nuevaPostulacion);
        return true;
    }
    
    //Obtener todas las becas a las que a postulado un estudiante
    public ArrayList<Beca> obtenerBecasEstudiante(String rutBuscado) {
        ArrayList<Beca> becasPostuladas = new ArrayList<>();
        
        //Recorremos las becas en busqueda del alumno
        for (Beca beca : mapaBecas.values()) {
            ArrayList<Postulacion> listaPostulaciones = beca.getListaPostulaciones();
            //bandera para ver si la postulacion existe y para controlar el flujo del while
            boolean encontrada = false;
            int i = 0;
            
            while (i < listaPostulaciones.size() && !encontrada) {
                if (listaPostulaciones.get(i).getPostulante().getRut().equals(rutBuscado)) {
                    becasPostuladas.add(beca);
                    encontrada = true;
                }
                i++;   
            }
        }
        return becasPostuladas;
    }
    
}
