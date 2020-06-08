package modelo.filtrado.prioridad;

import modelo.Tarea;
import modelo.filtrado.prioridad.FiltrarPrioridad;

import java.util.List;

public class FiltroPrioridadTodas implements FiltrarPrioridad {
    @Override
    public List<Tarea> listaTareasPrioridad(List<Tarea> listaTareas) {
        return listaTareas;
    }
}
