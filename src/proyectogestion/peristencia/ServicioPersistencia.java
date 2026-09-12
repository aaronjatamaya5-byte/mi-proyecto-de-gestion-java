/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectogestion.peristencia;

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

                    if (alumno != null && beca != null) {
                        Postulacion p = new Postulacion(id, estado, alumno);
                        beca.agregarPostulacion(p);
                    }
                }
            }
            System.out.println("Datos cargados correctamente desde SQLite a memoria.");

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
            con.setAutoCommit(false);

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

            con.commit();
            System.out.println("Base de datos actualizada con éxito.");

        } catch (SQLException e) {
            System.err.println("Error al guardar datos en SQLite: " + e.getMessage());
        }
    }
}