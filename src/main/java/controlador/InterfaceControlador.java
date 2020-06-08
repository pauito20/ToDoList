package controlador;

import modelo.ExcepcionCampoVacio;
import modelo.Modelo;
import vista.VistaGeneral;

public interface InterfaceControlador {
    void setVista(VistaGeneral vista);

    void setModelo(Modelo modelo);

    void añadirTarea() throws ExcepcionCampoVacio;


    //void actualizaTarea(int cod_tare);

    //void borraTarea(int cod_tarea);

    void actualizaTarea() throws ExcepcionCampoVacio;

    void borraTarea();

    void filtrarTareas();
}
