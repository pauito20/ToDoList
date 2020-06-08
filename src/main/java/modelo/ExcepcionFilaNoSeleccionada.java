package modelo;

public class ExcepcionFilaNoSeleccionada extends Exception {
    public ExcepcionFilaNoSeleccionada(){
        super("ERROR: No se ha seleccionado la fila");
    }
}
