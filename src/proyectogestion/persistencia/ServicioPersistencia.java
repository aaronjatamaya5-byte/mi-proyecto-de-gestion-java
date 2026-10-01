/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectogestion.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import proyectogestion.logica.ProgramaGestion;
import proyectogestion.modelo.Beca;
import proyectogestion.modelo.BecaAcademica;
import proyectogestion.modelo.BecaDeportiva;
import proyectogestion.modelo.BecaSocioeconomica;
import proyectogestion.modelo.Estudiante;
import proyectogestion.modelo.Postulacion;

/**
 *
 * @author curruk
 */


public class ServicioPersistencia {

    
    
public static void cargarDatosPrueba(ProgramaGestion programa) {
        System.out.println("Base de datos vacía. Agregando datos base...");
        
        programa.registrarEstudiante(new Estudiante("Camila Rojas", 21, "20.123.456-7", "F", "Av. Libertad 123, Viña del Mar", "Cerro Alegre 45, Valparaiso", 40.0, 7, 450000, 6.2));
        programa.registrarEstudiante(new Estudiante("Matias Soto", 19, "21.345.678-9", "M", "Calle Condell 500, Quilpue", "Calle Condell 500, Quilpue", 60.0, 4, 600000, 5.5));
        programa.registrarEstudiante(new Estudiante("Valentina Muñoz", 23, "19.876.543-K", "F", "Av. Argentina 800, Valparaiso", "Plaza Echaurren 12, Valparaiso", 80.0, 9, 850000, 4.8));
        programa.registrarEstudiante(new Estudiante("Diego Tapia", 20, "20.987.654-3", "M", "San Martin 432, Viña del Mar", "San Martin 432, Viña del Mar", 50.0, 2, 500000, 6.8));
        programa.registrarEstudiante(new Estudiante("Sofia Vergara", 22, "18.111.222-3", "F", "Los Carrera 100, Quilpue", "Av. Brasil 200, Valparaiso", 30.0, 8, 350000, 6.0));
        programa.registrarEstudiante(new Estudiante("Lucas Pinto", 18, "22.444.555-6", "M", "Alvarez 999, Viña del Mar", "Alvarez 999, Viña del Mar", 90.0, 10, 1200000, 4.2));
        programa.registrarEstudiante(new Estudiante("Isidora Blanco", 24, "17.999.888-7", "F", "Blanco Encalada 30, Quilpue", "Blanco Encalada 30, Quilpue", 40.0, 5, 400000, 5.9));
        
        programa.registrarBeca(new BecaAcademica(101, "Beca Excelencia Academica PUCV", 85, 3));
        programa.registrarBeca(new BecaDeportiva(102, "Beca Deportista Destacado Regional", 70, 2));
        programa.registrarBeca(new BecaSocioeconomica(103, "Beca de Residencia y Alimentacion", 60, 4));
        programa.registrarBeca(new BecaAcademica(104, "Beca de Honor en Ciencias", 95, 1));
        programa.registrarBeca(new BecaSocioeconomica(105, "Beca de Apoyo Estudiantil Junaeb", 50, 5));
    }
    
    //Para cuando abrimos el sistema
    public static void cargarDatos(ProgramaGestion gestor) {
        GestorBD.inicializarSchema();

        try (Connection conn = GestorBD.conectar()) {
            
            //Cargamos estudiante
            String sqlEst = "SELECT * FROM estudiante;";
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sqlEst)) {
                while (rs.next()) {
                    Estudiante est = new Estudiante(
                            rs.getString("nombre"),
                            rs.getInt("edad"),
                            rs.getString("rut"),
                            rs.getString("sexo"),
                            rs.getString("direccionHogar"),
                            rs.getString("direccionEstadia"),
                            rs.getDouble("porcentajeRSH"),
                            rs.getInt("nivelDeportividad"),
                            rs.getInt("ingresosFamiliares"),
                            rs.getDouble("promedioNotas")
                    );
                    //Lo insertamos al ArrayList
                    gestor.registrarEstudiante(est);
                }
            }

            // B. Cargamos Becas y nos sercioramos del tipo de beca por le polimorfismo
            String sqlBecas = "SELECT * FROM beca;";
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sqlBecas)) {
                while (rs.next()) {
                    int codigo = rs.getInt("codigo");
                    String nombre = rs.getString("nombre");
                    int puntajeMin = rs.getInt("puntajeMinimo");
                    int cupos = rs.getInt("cuposTotales");
                    String tipo = rs.getString("tipo");

                    Beca beca;
                    if ("BecaDeportiva".equalsIgnoreCase(tipo) || "Deportiva".equalsIgnoreCase(tipo)) {
                        beca = new BecaDeportiva(codigo, nombre, puntajeMin, cupos);
                    } else if ("BecaAcademica".equalsIgnoreCase(tipo) || "Academica".equalsIgnoreCase(tipo)) {
                        beca = new BecaAcademica(codigo, nombre, puntajeMin, cupos);
                    } else {
                        beca = new BecaSocioeconomica(codigo, nombre, puntajeMin, cupos);
                    }
                    gestor.registrarBeca(beca);
                }
            }

            //Cargamos Postulaciones
            String sqlPost = "SELECT * FROM postulacion;";
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sqlPost)) {
                while (rs.next()) {
                    int id = rs.getInt("idPostulacion");
                    String estado = rs.getString("estado");
                    String rut = rs.getString("rutEstudiante");
                    int codBeca = rs.getInt("codigoBeca");

                    Estudiante alumno = gestor.buscarEstudiante(rut);
                    Beca beca = gestor.buscarBeca(codBeca);

                    //Enlazamos la postulación con su beca
                    if (alumno != null && beca != null) {
                        Postulacion p = new Postulacion(id, estado, alumno);
                        beca.agregarPostulacion(p);
                    }
                }
            }
            System.out.println("Datos cargados correctamente desde SQLite a memoria.");
            
            if (gestor.getListaEstudiantes().isEmpty() && gestor.getMapaBecas().isEmpty()) {
                cargarDatosPrueba(gestor);
            }

        } catch (SQLException e) {
            System.err.println("Error al cargar datos desde SQLite: " + e.getMessage());
        }
    }

    //Al cerrar se ejecuta este metodo pára guardar los datos
    public static void guardarDatos(ProgramaGestion gestor) {
        String sqlEst = "INSERT OR REPLACE INTO estudiante (rut, nombre, edad, sexo, direccionHogar, direccionEstadia, "
                + "porcentajeRSH, nivelDeportividad, ingresosFamiliares, promedioNotas) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        String sqlBeca = "INSERT OR REPLACE INTO beca (codigo, nombre, puntajeMinimo, cuposTotales, tipo) "
                + "VALUES (?, ?, ?, ?, ?);";

        String sqlPost = "INSERT OR REPLACE INTO postulacion (idPostulacion, estado, rutEstudiante, codigoBeca) "
                + "VALUES (?, ?, ?, ?);";

        try (Connection con = GestorBD.conectar()) {
            //Apagamos el autoguardado
            con.setAutoCommit(false);

            
            //LIMPIAMOS LA BASE DE DATOS ANTES DE VOLVER A AGREGAR PARA SOLUCIONAR EL PROBLEMA CON LA ELIMINACION
            try (Statement stmtDelete = con.createStatement()) {
                stmtDelete.execute("DELETE FROM postulacion;");
                stmtDelete.execute("DELETE FROM beca;");
                stmtDelete.execute("DELETE FROM estudiante;");
            }
            

            // Guardar estudiantes
            try (PreparedStatement psEst = con.prepareStatement(sqlEst)) {
                for (Estudiante e : gestor.getListaEstudiantes()) {
                    psEst.setString(1, e.getRut());
                    psEst.setString(2, e.getNombre());
                    psEst.setInt(3, e.getEdad());
                    psEst.setString(4, e.getSexo());
                    psEst.setString(5, e.getDireccionHogar());
                    psEst.setString(6, e.getDireccionEstadia());
                    psEst.setDouble(7, e.getPorcentajeRSH());
                    psEst.setInt(8, e.getNivelDeportividad());
                    psEst.setInt(9, e.getIngresosFamiliares());
                    psEst.setDouble(10, e.getPromedioNotas());
                    psEst.addBatch();
                }
                psEst.executeBatch();
            }

            // Guardar becas y postulaciones
            try (PreparedStatement psBeca = con.prepareStatement(sqlBeca);
                 PreparedStatement psPost = con.prepareStatement(sqlPost)) {

                for (Beca b : gestor.getMapaBecas().values()) {
                    psBeca.setInt(1, b.getCodigo());
                    psBeca.setString(2, b.getNombreBeca());
                    psBeca.setInt(3, b.getPuntajeMinimo()); 
                    psBeca.setInt(4, b.getCupos());
                    psBeca.setString(5, b.getClass().getSimpleName());
                    psBeca.addBatch();

                    for (Postulacion p : b.getListaPostulaciones()) {
                        psPost.setInt(1, p.getIdPostulacion());
                        psPost.setString(2, p.getEstadoPostulacion());
                        psPost.setString(3, p.getPostulante().getRut());
                        psPost.setInt(4, b.getCodigo());
                        psPost.addBatch();
                    }
                }
                psBeca.executeBatch();
                psPost.executeBatch();
            }

            //Escribimos en la bd
            con.commit();
            System.out.println("Base de datos actualizada con éxito.");

        } catch (SQLException e) {
            System.err.println("Error al guardar datos en SQLite: " + e.getMessage());
        }
    }
}