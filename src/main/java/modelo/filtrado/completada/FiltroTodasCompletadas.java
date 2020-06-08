package modelo.filtrado.completada;

import modelo.Tarea;

import java.util.List;

public class FiltroTodasCompletadas implements FiltrarCompletadas {


    @Override
    public List<Tarea> listaTareasCompletadas(List<Tarea> listaTareas) {
        return listaTareas;
    }
}
