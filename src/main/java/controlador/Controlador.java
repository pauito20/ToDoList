package controlador;

import modelo.ExcepcionCampoVacio;
import modelo.ExcepcionFilaNoSeleccionada;
import modelo.Modelo;
import vista.VistaGeneral;

import javax.swing.*;

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
    public void añadirTarea() throws ExcepcionCampoVacio {
        String titulo = vista.getTituloAñadir();
        if (titulo.equals("")) {
            throw new ExcepcionCampoVacio();
        }
        String descripcion = vista.getDescripcionAñadir();
        String prioridad = vista.getPrioridadAñadir();
        boolean completada = vista.geatCompletadaAñadir();

        modelo.añadeTarea(titulo,descripcion,prioridad,completada);
    }

    @Override
    public void actualizaTarea() throws ExcepcionCampoVacio {
        int cod_tare = 0;
        try {
            cod_tare = vista.getCodTareaSeleccionada();

        } catch (ExcepcionFilaNoSeleccionada excepcionFilaNoSeleccionada) {
            JOptionPane.showMessageDialog(new JFrame(),
                    "No se ha seleccionado ninguna Tarea para actualizar. ",
                    "Tarea no seleccionada",
                    JOptionPane.WARNING_MESSAGE);
        }

        String titulo = vista.getTituloListar();
        if (titulo.equals("")) {
            throw new ExcepcionCampoVacio();
        }
        String descripcion = vista.getDescripcionListar();
        String prioridad = vista.getPrioridadModificar();
        boolean completada = vista.getCompletadaModificar();

        modelo.actualizaTarea(titulo,descripcion,prioridad,completada,cod_tare);
    }

    @Override
    public void borraTarea() {
        int cod_tarea = 0;
        try {
            cod_tarea = vista.getCodTareaSeleccionada();
        } catch (ExcepcionFilaNoSeleccionada excepcionFilaNoSeleccionada) {
            JOptionPane.showMessageDialog(new JFrame(),
                    "No se ha seleccionado ninguna Tarea para borrar. ",
                    "Tarea no seleccionada",
                    JOptionPane.WARNING_MESSAGE);
        }
        modelo.borraTarea(cod_tarea);
    }

    @Override
    public void filtrarTareas() {
        String completada = vista.isListarCompletadas();
        String prioridad = vista.listarTipoPrioridad();

        modelo.filtrar(completada,prioridad);
    }


}
