package vista;

        import controlador.Controlador;
        import controlador.InterfaceControlador;
        import modelo.InterfaceModelo;
        import modelo.Modelo;
        import modelo.Tarea;

        import java.util.List;

public interface InterfaceVista {
    void setControlador(InterfaceControlador controlador);

    void setModelo(InterfaceModelo modelo);

    void creaInterfazVisual();

    //Getters y actualizadores de Campos GUI
    String getDescripcionAñadir();

    String getTituloAñadir();

    String getPrioridadAñadir();

    boolean geatCompletadaAñadir();

    void setTareaAñadida();

    //LISTAR TAREA
    String getDescripcionListar();

    String getTituloListar();

    boolean getCompletadaListar();

    boolean getCompletadaModificar();

    String getPrioridadListar();


    String getPrioridadModificar();

    int getCodTareaSeleccionada();

    List<Tarea> getListaTareas();

    void actualizaTabla();


    void actualizaFiltrado(List<Tarea> filtradas);

    void setDatosTarea(int cod_tarea);

    //Metodos Para Filtrar Tareas
    String isListarCompletadas();

    String listarTipoPrioridad();
}
