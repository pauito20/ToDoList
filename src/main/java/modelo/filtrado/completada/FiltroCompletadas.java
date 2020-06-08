package modelo.filtrado.completada;

import modelo.*;

import java.util.LinkedList;
import java.util.List;

public class FiltroCompletadas implements FiltrarCompletadas {



    @Override
    public List<Tarea> listaTareasCompletadas(List<Tarea> listaTareas) {
        List<Tarea> devuelve = new LinkedList<Tarea>();

        if (!listaTareas.isEmpty()){
            for (Tarea ta : listaTareas)
                if (ta.isCompletada()){
                    devuelve.add(ta);
                }
        }
        return devuelve;
    }

}
