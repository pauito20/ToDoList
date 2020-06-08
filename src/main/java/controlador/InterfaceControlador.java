package controlador;

import modelo.Modelo;
import vista.VistaGeneral;

public interface InterfaceControlador {
    void setVista(VistaGeneral vista);

    void setModelo(Modelo modelo);

    void añadirTarea();


    //void actualizaTarea(int cod_tare);

    //void borraTarea(int cod_tarea);

    void actualizaTarea();

    void borraTarea();

    void filtrarTareas();
}
