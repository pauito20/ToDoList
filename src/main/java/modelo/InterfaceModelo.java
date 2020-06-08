package modelo;

import vista.InterfaceVista;
import vista.VistaGeneral;

import java.util.List;

public interface InterfaceModelo {


    void setVista(InterfaceVista vista);

    /* ------------------ METODOS GESTIÓN LISTA DE TAREAS --------------------  */

    //Añade nueva Tarea
    void añadeTarea(String titulo, String descripcion, String prioridad, boolean completada);

    //Borra una Tarea
    void borraTarea(int cod_tarea);

    //Actualiza Tarea
    void actualizaTarea(String titulo, String descripcion, String prioridad, boolean completada, int cod_tarea);

    //Genera Lista con las Tareas del mapa
    List<Tarea> generaListaTareas();

    //Serializa guardando los datos
    void guardaDatos();
    //Serializa cargando los datos
    void cargaDatos();








    String mostrarTituloTarea(int cod_tarea);

    String mostrarDescripcionTarea(int cod_tarea);

    boolean mostrarSeleccionada(int cod_tarea);

    String mostararPrioridadSeleccionada(int cod_tarea);

    int getSize();
}
