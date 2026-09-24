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
import proyectogestion.excepciones.EstudianteNoEncontradoException;
import proyectogestion.excepciones.BecaNoEncontradaException;

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
    public boolean postularEstudiante(String rut, int codigoBeca) throws EstudianteNoEncontradoException,BecaNoEncontradaException{
        Estudiante estudiante = buscarEstudiante(rut);
        Beca beca = buscarBeca(codigoBeca);

        if (estudiante == null)
        {
            throw new EstudianteNoEncontradoException("Error: No existe un estudiante con el rut " + rut);
        }
        
        if(beca==null)
        {
            throw new BecaNoEncontradaException("Error: No existe una beca con el codigo " + codigoBeca);
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
    
    //Eliminacion de estudiante
    
    public boolean eliminarEstudiante(String rut)
    {
        Estudiante estudiante=buscarEstudiante(rut);
        if(estudiante!=null)
        {
            listaEstudiantes.remove(estudiante);
            System.out.println("Estudiante con Rut" + rut + "eliminado correctamente.");
            return true;
        }
        System.out.println("No se encontro al estudiante que desea eliminar.");
        return false;
    }
    
    //Eliminacion de beca
    
    public boolean eliminarBeca(int codigo)
    {
        if(mapaBecas.containsKey(codigo))
        {
            mapaBecas.remove(codigo);
            System.out.println("Beca con codigo " + codigo + "eliminada");
            return true;
        }
        System.out.println("No se encontro una beca con ese codigo");
        return false;
    }
    
    
    //Modificacion de estudiante
    
    public boolean modificarEstudiante(String rut,String nuevaDireccion, int nuevosIngresos,double nuevoPromedio)
    {
        Estudiante estudiante=buscarEstudiante(rut);
        if(estudiante!=null)
        {
            estudiante.actualizarDatos(nuevaDireccion, nuevosIngresos, nuevoPromedio);
            System.out.println("Datos del estudiante actualizados correctamente.");
            return true;
        }
        System.out.println("No se encontro al estudiante con ese rut");
        return false;
    }
    
    //Sobrecarga de modificacion estudiante para cambiar todos los datos
    
    public boolean modificarEstudiante(String rut,int nuevaEdad,String nuevaDireccionHogar,String nuevaDireccionEstadia, double nuevoRSH,int nuevosIngresos,double nuevoPromedio,int nuevoNivelDeporte)
    {
        Estudiante estudiante=buscarEstudiante(rut);
        if(estudiante!=null)
        {
            estudiante.actualizarDatos(nuevaEdad,nuevaDireccionHogar,nuevaDireccionEstadia,nuevoRSH,nuevosIngresos,nuevoPromedio,nuevoNivelDeporte);
            System.out.println("Datos del estudiante actualizados correctamente.");
            return true;
        }
        System.out.println("No se encontro al estudiante con ese rut");
        return false;
    }
    
    
    //Modificacion de beca
    
    public boolean modificarBeca(int codigo, int nuevosCupos, int nuevoPuntajeMinimo)
    {
        Beca beca=buscarBeca(codigo);
        if(beca!=null)
        {
            beca.setCupos(nuevosCupos);
            beca.setPuntajeMinimo(nuevoPuntajeMinimo);
            System.out.println("Los datos de la beca buscada han sido actualizados");
            return true;
        }
        System.out.println("No se encontro la beca con ese codigo");
        return false;
    }
    
    //Metodo para asignar becas a los estudiantes
    
    public void asignarBecas()
    {
        for(Beca beca: mapaBecas.values())
        {
            int cuposDisponibles=beca.getCupos();
            int puntajeRequerido=beca.getPuntajeMinimo();
            
            System.out.println("Beca:" + beca.getNombreBeca() + "| Cupos iniciales " + cuposDisponibles);
            
            ArrayList<Postulacion> listaPostulaciones=beca.getListaPostulaciones();
            
            for(Postulacion p : listaPostulaciones)
            {
                if(p.getEstadoPostulacion().equalsIgnoreCase("Pendiente"))
                {
                    Estudiante alumno = p.getPostulante();
                    
                    double puntajeObtenido=beca.calcularPuntaje(alumno);
                    
                    if(puntajeObtenido>=puntajeRequerido)
                    {
                        if(cuposDisponibles>0)
                        {
                            p.setEstadoPostulacion("Aceptado");
                            cuposDisponibles--;
                            System.out.println("Beca asignada a:" + alumno.getNombre() + " Puntaje:" + puntajeObtenido);
                            
                        }
                        else
                        {
                            p.setEstadoPostulacion("Rechazado por falta de cupos");
                            System.out.println("Beca rechazada por falta de cupos");
                        }
                    }
                    else
                    {
                        p.setEstadoPostulacion("Rechazado por puntaje insuficiente");
                        System.out.println("Beca rechazada por bajo puntaje");
                    }
                }
            }
            
            
            beca.setCupos(cuposDisponibles);
        }
              
    }
    

}
