/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectogestion.vista;

import java.util.Scanner;
import proyectogestion.logica.ProgramaGestion;
import proyectogestion.modelo.*;
import proyectogestion.excepciones.*;
/**
 *
 * @author aaron
 */
public class Menu {
    private Scanner entrada=new Scanner(System.in);
    
    public void iniciar(ProgramaGestion programa)
    {
        
        boolean salir=false;
        while(!salir)
        {
            System.out.println("\n=== Menu principal - Gestion de becas ===\n");
            System.out.println("1. Menu gestion de estudiantes");
            System.out.println("2. Menu gestion de becas");
            System.out.println("3. Menu operaciones de negocio");
            System.out.println("4. Salir");
            System.out.println("Ingrese una opcion: ");
            
            String opcion=entrada.nextLine();
            
            switch(opcion)
            {
                case "1":
                    menuEstudiantes(programa);
                    break;
                    
                case "2":
                    menuBecas(programa);
                    break;
                    
                case "3":
                    menuNegocio(programa);
                    break;
                    
                case "4":
                    salir=true;
                    System.out.println("Saliendo del menu...");
                    break;
                    
                default:
                    System.out.println("Opcion no valida. Ingrese una opcion nuevamente");
            }
            
        }
    }
    
    public void menuEstudiantes(ProgramaGestion programa)
    {
        boolean volver=false;
        while(!volver)
        {
            System.out.println("\n=== Gestion de estudiantes ===\n");
            System.out.println("1. Agregar estudiante");
            System.out.println("2. Mostrar todos los estudiantes");
            System.out.println("3. Buscar estudiante por rut");
            System.out.println("4. Modificar datos de estudiante");
            System.out.println("5. Eliminar estudiante");
            System.out.println("6. Volver al menu principal");
            System.out.println("Ingrese una opcion: ");
            
            String opcion=entrada.nextLine();
            
            switch(opcion)
            {
                case "1":
                    
                    System.out.println("Ingrese los datos necesarios");
                    try{
                        
                        System.out.println("Nombre: ");
                        String nombre=entrada.nextLine();
                    
                        System.out.println("Edad: ");
                        int edad=Integer.parseInt(entrada.nextLine());
                        
                        System.out.println("Rut: ");
                        String rut=entrada.nextLine();
                    
                        System.out.println("Sexo: ");
                        String sexo=entrada.nextLine();
                    
                        System.out.println("Direccion de hogar: ");
                        String direccionHogar=entrada.nextLine();

                        System.out.println("Direccion de estadia: ");
                        String direccionEstadia=entrada.nextLine();
                        
                        System.out.println("Porcentaje RSH: ");
                        double RSH=Double.parseDouble(entrada.nextLine());
                        
                        System.out.println("Nivel de deportividad(1-10): ");
                        int nivelDeportividad=Integer.parseInt(entrada.nextLine());   
                   
                        System.out.println("Ingresos familiares: ");
                        int ingresos=Integer.parseInt(entrada.nextLine());
                     
                        System.out.println("Promedio de notas: ");
                        double promedio=Double.parseDouble(entrada.nextLine());
                    
                        Estudiante est = new Estudiante(nombre,edad,rut,sexo,direccionHogar,direccionEstadia,RSH,nivelDeportividad,ingresos,promedio);
                    
                        programa.registrarEstudiante(est);
                        System.out.println("Estudiante registrado con exito");
                    
                        }catch(NumberFormatException e){
                        System.out.println("Error: Ingrese valores numericos validos en Edad, RSH, Deportividad, Ingresos o Promedio");
                        }
                              
                    break;
                    
                case "2":
                    System.out.println("\n--- Lista de estudiantes ---");
                    for(Estudiante estudiante : programa.getListaEstudiantes())
                    {
                        System.out.println("- Rut: " + estudiante.getRut() + " | Nombre: " + estudiante.getNombre() + " | Promedio: " + estudiante.getPromedioNotas());
                    }
                    break;
                    
                case "3":
                    System.out.println("Ingrese rut a buscar: ");
                    Estudiante estudianteEncontrado=programa.buscarEstudiante(entrada.nextLine());
                    
                    if(estudianteEncontrado!=null)
                    {
                        System.out.println("Encontrado: " + estudianteEncontrado.getNombre() + " | RSH: " + estudianteEncontrado.getPorcentajeRSH() + "%");
                    }
                    else
                    {
                        System.out.println("Estudiante no encontrado");
                    }
                    
                    break;
                    
                case "4":
                    System.out.println("Indique el rut del estudiante a modificar los datos: ");
                    String rutModificacion=entrada.nextLine();
                    
                    System.out.println("¿De que forma desea modificar los datos del estudiante?");
                    System.out.println("1. Modificacion parcial (Direccion estadia, Ingresos y Promedio)");
                    System.out.println("2. Modificacion total");
                    System.out.println("Indique opcion: ");
                    
                    String opcionModificacion=entrada.nextLine();
                    
                    if(opcionModificacion.equals("1"))
                    {
                        try
                        {
                            System.out.println("Nueva direccion de estadia: ");
                            String nuevaDireccion=entrada.nextLine();
                        
                            System.out.println("Nuevos ingresos familiares: ");
                            int nuevosIngresos=Integer.parseInt(entrada.nextLine());
                        
                            System.out.println("Nuevo promedio de notas: ");
                            double nuevoPromedio=Double.parseDouble(entrada.nextLine());
                        
                            programa.modificarEstudiante(rutModificacion, nuevaDireccion, nuevosIngresos, nuevoPromedio); 
                        }catch(NumberFormatException e){
                            System.out.println("Error: ingresos y promedio deben ser numeros");
                        }
                    }
                    else if(opcionModificacion.equals("2"))
                    {
                        try{
                        
                            System.out.println("Nueva edad: ");
                            int nuevaEdad=Integer.parseInt(entrada.nextLine());
                        
                            System.out.println("Nueva direccion de hogar: ");
                            String nuevaDireccionHogar=entrada.nextLine();

                            System.out.println("Nueva direccion de estadia: ");
                            String nuevaDireccionEstadia=entrada.nextLine();
                        
                            System.out.println("Nuevo porcentaje RSH: ");
                            double nuevoRSH=Double.parseDouble(entrada.nextLine());
                        
                            System.out.println("Nuevos ingresos familiares: ");
                            int nuevosIngresos=Integer.parseInt(entrada.nextLine());
                        
                            System.out.println("Nuevo promedio de notas: ");
                            double nuevoPromedio=Double.parseDouble(entrada.nextLine());
                        
                            System.out.println("Nuevo nivel de deportividad(1-10): ");
                            int nuevaDeportividad=Integer.parseInt(entrada.nextLine());
                        
                            programa.modificarEstudiante(rutModificacion, nuevaEdad, nuevaDireccionHogar, nuevaDireccionEstadia, nuevoRSH, nuevosIngresos, nuevoPromedio, nuevaDeportividad);
                        }catch(NumberFormatException e){
                            System.out.println("Error: ingrese solo numeros en los campos correspondientes");
                        }
                    }
                    else
                    {
                        System.out.println("Opcion no valida");
                    }

                    break;
                    
                case "5":
                    System.out.println("Indique el rut del estudiante a eliminar: ");
                    programa.eliminarEstudiante(entrada.nextLine());
                    break;
                    
                case "6":
                    volver=true;
                    break;
                    
                default:
                    System.out.println("Opcion no valida");
            }
        }
    }
    
    public void menuBecas(ProgramaGestion programa)
    {
        boolean volver=false;
        while(!volver)
        {
            System.out.println("\n=== Gestion de estudiantes ===\n");
            System.out.println("1. Agregar beca");
            System.out.println("2. Mostrar todos las becas");
            System.out.println("3. Buscar beca por codigo");
            System.out.println("4. Modificar datos de una beca");
            System.out.println("5. Eliminar beca");
            System.out.println("6. Volver al menu principal");
            System.out.println("Ingrese una opcion: ");   
            
            String opcion=entrada.nextLine();
            
            switch(opcion)
            {
                case "1":
                    try{
                    
                        System.out.println("Seleccione el tipo de beca a crear");
                        System.out.println("1. Academica | 2. Deportiva | 3.Socioeconomica");
                        String tipoBeca=entrada.nextLine();
                    
                        System.out.println("Codigo: ");
                        int codigo=Integer.parseInt(entrada.nextLine());
                  
                        System.out.println("Nombre: ");
                        String nombreBeca= entrada.nextLine();
                    
                        System.out.println("Puntaje Minimo: ");
                        int puntajeMinimo=Integer.parseInt(entrada.nextLine());
                    
                        System.out.println("Cupos: ");
                        int cupos= Integer.parseInt(entrada.nextLine());
                    
                        Beca nuevaBeca=null;
                        if(tipoBeca.equals("1"))
                        {
                            nuevaBeca=new BecaAcademica(codigo,nombreBeca,puntajeMinimo,cupos);
                        }
                        else if(tipoBeca.equals("2"))
                        {
                            nuevaBeca=new BecaDeportiva(codigo,nombreBeca,puntajeMinimo,cupos);
                        }
                        else if(tipoBeca.equals("3"))
                        {
                            nuevaBeca=new BecaSocioeconomica(codigo,nombreBeca,puntajeMinimo,cupos);
                        }
                    
                        if(nuevaBeca!=null)
                        {
                            programa.registrarBeca(nuevaBeca);
                            System.out.println("La beca fue registrada exitosamente");
                        }
                        else
                        {
                            System.out.println("Tipo de beca no valida");
                        }
                    }catch(NumberFormatException e){
                        System.out.println("Error: codigo, puntaje y cupos deben ser numeros enteros");
                    }
                           
                    break;
                    
                case "2":
                    System.out.println("\n--- Listado de becas ---");
                    for(Beca beca : programa.getMapaBecas().values())
                    {
                        System.out.println("- Codigo: "+ beca.getCodigo() + "| Nombre: "+ beca.getNombreBeca()+" | Cupos: " + beca.getCupos());
                    }
                    break;
                    
                case "3":
                    try{
                        
                    
                        System.out.println("Indique el codigo de beca a buscar: ");
                        Beca becaEncontrada=programa.buscarBeca(Integer.parseInt(entrada.nextLine()));
                    
                        if(becaEncontrada!=null)
                        {
                            System.out.println("Beca encontrada: \n"+becaEncontrada.getNombreBeca()+"| Puntaje Minimo: "+becaEncontrada.getPuntajeMinimo());
                        }
                        else
                        {
                            System.out.println("Beca no encontrada");
                        }        
                    }catch(NumberFormatException e){
                        System.out.println("Error: el codigo debe ser numerico");
                    }
                    break;
                    
                case "4":
                    try{
                        System.out.println("Indique el codigo de la beca a modificar: ");
                        int codigoModificar=Integer.parseInt(entrada.nextLine());
                    
                        System.out.println("Nuevos cupos: ");
                        int nuevosCupos=Integer.parseInt(entrada.nextLine());
                    
                        System.out.println("Nuevo puntaje minimo: ");
                        int nuevoPuntaje=Integer.parseInt(entrada.nextLine());
                    
                        programa.modificarBeca(codigoModificar, nuevosCupos, nuevoPuntaje);
                    
                    }catch(NumberFormatException e){
                        System.out.println("Error: los cupos y puntajes deben ser valores numeros");
                    }
                    
                    break;
                    
                case "5":
                    try{
                        System.out.println("Indique el codigo de la beca a eliminar: ");
                        programa.eliminarBeca(Integer.parseInt(entrada.nextLine()));
                    }catch(NumberFormatException e){
                        System.out.println("Error: el codigo debe ser numerico");
                    }
                    break;
                    
                case "6":
                    volver=true;
                    break;
                    
                default:
                    System.out.println("Opcion no valida");
            }
        }
    }
    
    public void menuNegocio(ProgramaGestion programa)
    {
        boolean volver=false;
        
        while(!volver)
        {
            System.out.println("\n--- Operaciones del negocio ---\n");
            System.out.println("1. Postular estudiante a beca");
            System.out.println("2. Asignar becas");
            System.out.println("3. Volver al menu principal");
            System.out.println("Ingrese una opcion");
            
            String opcion=entrada.nextLine();
            
            switch(opcion)
            {
                case "1":
                    System.out.println("Rut del estudiante: ");
                    String rutPostulante=entrada.nextLine();
                    System.out.println("Codigo de beca: ");
                    
                    try
                    {
                        int codigoBecaPostular=Integer.parseInt(entrada.nextLine());
                        
                        programa.postularEstudiante(rutPostulante, codigoBecaPostular);
                        System.out.println("Postulacion registrada, en estado Pendiente");
                        
                    }catch(NumberFormatException e){
                        System.out.println("Error: el codigo de beca debe ser un numero");
                        
                    }catch(EstudianteNoEncontradoException | BecaNoEncontradaException e){
                        System.out.println(e.getMessage());
                    }
                    
                    break;
                    
                case "2":
                    System.out.println("Asignando becas...");
                    programa.asignarBecas();
                    break;
                    
                case "3":
                    volver=true;
                    break;
                    
                default:
                    System.out.println("Opcion no valida");
            }
             
        }
    }
    
    
}
