package controlador;

import modelo.Modelo;
import vista.VistaGeneral;

public class Controlador implements InterfaceControlador{

    private VistaGeneral vista;
    private Modelo modelo;

    @Override
    public void setVista(VistaGeneral vista) {
        this.vista = vista;
    }

    @Override
    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    @Override
    public void añadirTarea() {
        String titulo = vista.getTituloAñadir();
        String descripcion = vista.getDescripcionAñadir();
        String prioridad = vista.getPrioridadAñadir();
        boolean completada = vista.geatCompletadaAñadir();

        modelo.añadeTarea(titulo,descripcion,prioridad,completada);
    }

    @Override
    public void actualizaTarea() {
        int cod_tare = vista.getCodTareaSeleccionada();
        String titulo = vista.getTituloListar();
        String descripcion = vista.getDescripcionListar();
        String prioridad = vista.getPrioridadModificar();
        boolean completada = vista.getCompletadaModificar();

        modelo.actualizaTarea(titulo,descripcion,prioridad,completada,cod_tare);
    }

    @Override
    public void borraTarea() {
        int cod_tarea = vista.getCodTareaSeleccionada();
        modelo.borraTarea(cod_tarea);
    }


}
