package modelo.filtrado.prioridad;

import modelo.Tarea;
import modelo.filtrado.prioridad.FiltrarPrioridad;

import java.util.LinkedList;
import java.util.List;

public class FiltroPrioridadNormal implements FiltrarPrioridad {
    @Override
    public List<Tarea> listaTareasPrioridad(List<Tarea> listaTareas) {
        List<Tarea> devuelve = new LinkedList<Tarea>();

        if (!listaTareas.isEmpty()){
            for (Tarea ta : listaTareas)
                if (ta.getPrioridad().equals("Normal")){
                    devuelve.add(ta);
                }
        }
        return devuelve;
    }
}
